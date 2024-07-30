package com.zincyanide.math.expression;

import com.zincyanide.math.CalcProcess;
import com.zincyanide.math.op.Abacus;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import java.math.BigDecimal;

public class OpPlus extends org.springframework.expression.spel.ast.OpPlus
{
    private CalcProcess.ProcessStepQueue processStepQueue;

    public OpPlus(CalcProcess.ProcessStepQueue processStepQueue, int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
        this.processStepQueue = processStepQueue;
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException
    {
        SpelNodeImpl leftOp = getLeftOperand();

        if (this.children.length < 2) {  // if only one operand, then this is unary plus
            Object operandOne = leftOp.getValueInternal(state).getValue();
            if (operandOne instanceof Number) {
                if (operandOne instanceof Double) {
                    this.exitTypeDescriptor = "D";
                } else if (operandOne instanceof Float) {
                    this.exitTypeDescriptor = "F";
                } else if (operandOne instanceof Long) {
                    this.exitTypeDescriptor = "J";
                } else if (operandOne instanceof Integer) {
                    this.exitTypeDescriptor = "I";
                }
                return new TypedValue(operandOne);
            }
            return state.operate(Operation.ADD, operandOne, null);
        }

        TypedValue operandOneValue = leftOp.getValueInternal(state);
        Object leftOperand = operandOneValue.getValue();
        TypedValue operandTwoValue = getRightOperand().getValueInternal(state);
        Object rightOperand = operandTwoValue.getValue();

        if (leftOperand instanceof Number && rightOperand instanceof Number)
        {
            Number leftNumber = (Number) leftOperand;
            Number rightNumber = (Number) rightOperand;

            TypedValue typedValue = new TypedValue(Abacus.add(leftNumber, rightNumber));

            processStepQueue.recordStep(this.getOperatorName(), typedValue.getValue(), leftNumber, rightNumber);
            return typedValue;
        }

        if (leftOperand instanceof String && rightOperand instanceof String) {
            this.exitTypeDescriptor = "Ljava/lang/String";
            return new TypedValue((String) leftOperand + rightOperand);
        }

        if (leftOperand instanceof String) {
            return new TypedValue(
                    leftOperand + (rightOperand == null ? "null" : convertTypedValueToString(operandTwoValue, state)));
        }

        if (rightOperand instanceof String) {
            return new TypedValue(
                    (leftOperand == null ? "null" : convertTypedValueToString(operandOneValue, state)) + rightOperand);
        }

        return state.operate(Operation.ADD, leftOperand, rightOperand);
    }

    private static String convertTypedValueToString(TypedValue value, ExpressionState state)
    {
        TypeConverter typeConverter = state.getEvaluationContext().getTypeConverter();
        TypeDescriptor typeDescriptor = TypeDescriptor.valueOf(String.class);
        if (typeConverter.canConvert(value.getTypeDescriptor(), typeDescriptor)) {
            return String.valueOf(typeConverter.convertValue(value.getValue(),
                    value.getTypeDescriptor(), typeDescriptor));
        }
        return String.valueOf(value.getValue());
    }
}
