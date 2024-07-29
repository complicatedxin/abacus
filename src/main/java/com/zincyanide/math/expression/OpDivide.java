package com.zincyanide.math.expression;

import com.zincyanide.math.op.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import java.math.BigDecimal;

public class OpDivide extends org.springframework.expression.spel.ast.OpDivide
{
    private int precision;

    public OpDivide(int precision, int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
        this.precision = precision;
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

            return new TypedValue(Abacus.divide(leftNumber, rightNumber, precision));
        }

        return state.operate(Operation.DIVIDE, leftOperand, rightOperand);
    }


}
