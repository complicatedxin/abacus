package com.zincyanide.math.expression;

import com.zincyanide.math.op.Abacus;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import java.math.BigDecimal;
import java.math.BigInteger;

public class OpMinus extends org.springframework.expression.spel.ast.OpMinus
{
    public OpMinus(int startPos, int endPos, SpelNodeImpl... operands)
    {
        super(startPos, endPos, operands);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException
    {
        SpelNodeImpl leftOp = getLeftOperand();

        if (this.children.length < 2) {  // if only one operand, then this is unary minus
            Object operand = leftOp.getValueInternal(state).getValue();
            if (operand instanceof Number) {
                if (operand instanceof BigDecimal) {
                    return new TypedValue(((BigDecimal) operand).negate());
                }
                else if (operand instanceof Double) {
                    this.exitTypeDescriptor = "D";
                    return new TypedValue(0 - ((Number) operand).doubleValue());
                }
                else if (operand instanceof Float) {
                    this.exitTypeDescriptor = "F";
                    return new TypedValue(0 - ((Number) operand).floatValue());
                }
                else if (operand instanceof BigInteger) {
                    return new TypedValue(((BigInteger) operand).negate());
                }
                else if (operand instanceof Long) {
                    this.exitTypeDescriptor = "J";
                    return new TypedValue(0 - ((Number) operand).longValue());
                }
                else if (operand instanceof Integer) {
                    this.exitTypeDescriptor = "I";
                    return new TypedValue(0 - ((Number) operand).intValue());
                }
                else if (operand instanceof Short) {
                    return new TypedValue(0 - ((Number) operand).shortValue());
                }
                else if (operand instanceof Byte) {
                    return new TypedValue(0 - ((Number) operand).byteValue());
                }
                else {
                    // Unknown Number subtypes -> best guess is double subtraction
                    return new TypedValue(0 - ((Number) operand).doubleValue());
                }
            }
            return state.operate(Operation.SUBTRACT, operand, null);
        }

        Object left = leftOp.getValueInternal(state).getValue();
        Object right = getRightOperand().getValueInternal(state).getValue();

        if (left instanceof Number && right instanceof Number)
        {
            Number leftNumber = (Number) left;
            Number rightNumber = (Number) right;

            return new TypedValue(Abacus.subtract(leftNumber, rightNumber));
        }

        if (left instanceof String && right instanceof Integer && ((String) left).length() == 1) {
            String theString = (String) left;
            Integer theInteger = (Integer) right;
            // Implements character - int (ie. b - 1 = a)
            return new TypedValue(Character.toString((char) (theString.charAt(0) - theInteger)));
        }

        return state.operate(Operation.SUBTRACT, left, right);
    }
}
