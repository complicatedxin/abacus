package com.zincyanide.calculator.expression;

import com.zincyanide.calculator.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import java.math.BigDecimal;

public class OpMultiply extends org.springframework.expression.spel.ast.OpMultiply
{
    public OpMultiply(int startPos, int endPos, SpelNodeImpl... operands)
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

            return new TypedValue(Abacus.multiply(leftBigDecimal, rightBigDecimal));
        }

        if (leftOperand instanceof String && rightOperand instanceof Integer) {
            int repeats = (Integer) rightOperand;
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < repeats; i++) {
                result.append(leftOperand);
            }
            return new TypedValue(result.toString());
        }

        return state.operate(Operation.MULTIPLY, leftOperand, rightOperand);
    }
}
