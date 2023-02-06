package com.zincyanide.calculator.expression;

import com.zincyanide.calculator.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.OpModulus;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OpMod extends OpModulus
{
    public OpMod(int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
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

            BigDecimal leftBigDecimal = Abacus.decimalize(leftNumber);
            BigDecimal rightBigDecimal = Abacus.decimalize(rightNumber);

            Number multiply = Abacus.multiply(leftBigDecimal, rightBigDecimal);
            Number percent = Abacus.divide(multiply, 100, 6);

            return new TypedValue(percent);
        }

        return state.operate(Operation.MODULUS, leftOperand, rightOperand);
    }
}
