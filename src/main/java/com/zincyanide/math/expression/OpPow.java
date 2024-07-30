package com.zincyanide.math.expression;

import com.zincyanide.math.CalcProcess;
import com.zincyanide.math.op.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.OperatorPower;
import org.springframework.expression.spel.ast.SpelNodeImpl;

public class OpPow extends OperatorPower
{
    private CalcProcess.ProcessStepQueue processStepQueue;

    public OpPow(CalcProcess.ProcessStepQueue processStepQueue, int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
        this.processStepQueue = processStepQueue;
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException
    {
        SpelNodeImpl leftOp = getLeftOperand();
        SpelNodeImpl rightOp = getRightOperand();

        Object leftOperand = leftOp.getValueInternal(state).getValue();
        Object rightOperand = rightOp.getValueInternal(state).getValue();

        if (leftOperand instanceof Number && rightOperand instanceof Number)
        {
            Number leftNumber = (Number) leftOperand;
            Number rightNumber = (Number) rightOperand;
            TypedValue res;
            if(rightNumber instanceof Integer
                    || rightNumber instanceof Byte
                    || rightNumber instanceof Short)
            {
                Integer rightInteger = rightNumber.intValue();
                res = new TypedValue(Abacus.pow(leftNumber, rightInteger));
            }
            else
            {
                Double rightDouble = rightNumber.doubleValue();
                res = new TypedValue(Abacus.pow(leftNumber, rightDouble));
            }
            processStepQueue.recordStep(this.getOperatorName(), res.getValue(), leftNumber, rightNumber);
            return res;
        }

        return state.operate(Operation.POWER, leftOperand, rightOperand);
    }
}
