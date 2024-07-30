package com.zincyanide.math.expression;

import com.zincyanide.math.CalcProcess;
import com.zincyanide.math.op.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;

public class OpDivide extends org.springframework.expression.spel.ast.OpDivide
{
    private int precision;

    private CalcProcess.ProcessStepQueue processStepQueue;

    public OpDivide(CalcProcess.ProcessStepQueue processStepQueue, int precision, int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
        this.precision = precision;
        this.processStepQueue = processStepQueue;
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException
    {
        Object leftOperand = getLeftOperand().getValueInternal(state).getValue();
        Object rightOperand = getRightOperand().getValueInternal(state).getValue();

        if (leftOperand instanceof Number && rightOperand instanceof Number)
        {
            Number leftNumber = (Number) leftOperand;
            Number rightNumber = (Number) rightOperand;

            TypedValue typedValue = new TypedValue(Abacus.divide(leftNumber, rightNumber, precision));

            processStepQueue.recordStep(this.getOperatorName(), typedValue.getValue(), leftNumber, rightNumber);
            return typedValue;
        }

        return state.operate(Operation.DIVIDE, leftOperand, rightOperand);
    }


}
