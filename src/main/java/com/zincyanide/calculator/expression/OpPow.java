package com.zincyanide.calculator.expression;

import com.zincyanide.calculator.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.OperatorPower;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import java.math.BigDecimal;

public class OpPow extends OperatorPower
{
    public OpPow(int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
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
            if(rightNumber instanceof Integer
                    || rightNumber instanceof Byte
                    || rightNumber instanceof Short)
            {
                Integer rightInteger = rightNumber.intValue();
                return new TypedValue(Abacus.pow(leftNumber, rightInteger));
            }
            else
            {
                Double rightDouble = rightNumber.doubleValue();
                return new TypedValue(Abacus.pow(leftNumber, rightDouble));
            }
        }

        return state.operate(Operation.POWER, leftOperand, rightOperand);
    }
}
