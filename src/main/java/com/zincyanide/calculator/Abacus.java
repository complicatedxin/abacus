package com.zincyanide.calculator;

import com.zincyanide.calculator.expression.AbacusExpressionParser;
import com.zincyanide.calculator.expression.AbacusStandardEvaluationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class Abacus
{
    private static final int DEFAULT_FRACTION_SCALE = 2;

    private static final RoundingMode DEFAULT_ROUND_STRATEGY = RoundingMode.HALF_UP;

    private static ExpressionParser parser = new AbacusExpressionParser();

    public static String strize(Number n)
    {
        if(n == null)
            throw new NullPointerException("number is null");
        return String.valueOf(n);
    }

    public static BigDecimal decimalize(Number n)
    {
        if(n instanceof BigDecimal)
            return (BigDecimal) n;
        return new BigDecimal(strize(n));
    }

    public static BigDecimal decimalize(String amount)
    {
        if(amount == null || amount.length() == 0)
            throw new NullPointerException("amount is empty");
        return new BigDecimal(amount);
    }

    public static BigDecimal decimalizeOrDefault(String amount, Number defaultNumber,
                                                 Supplier<Boolean> when)
    {
        return (when != null && when.get()) ?
            decimalize(defaultNumber) : decimalize(amount);
    }

    public static Number add(Number n1, Number n2)
    {
        return decimalize(n1).add(decimalize(n2));
    }

    public static Number subtract(Number n1, Number n2)
    {
        return decimalize(n1).subtract(decimalize(n2));
    }

    public static Number multiply(Number n1, Number n2)
    {
        return decimalize(n1).multiply(decimalize(n2));
    }

    public static Number divide(Number n1, Number n2)
    {
        return divide(n1, n2, DEFAULT_FRACTION_SCALE, DEFAULT_ROUND_STRATEGY);
    }
    public static Number divide(Number n1, Number n2, int fractionScale)
    {
        return divide(n1, n2, fractionScale, DEFAULT_ROUND_STRATEGY);
    }
    public static Number divide(Number n1, Number n2, int fractionScale, RoundingMode mode)
    {
        return decimalize(n1).divide(decimalize(n2), fractionScale, mode);
    }

    public static Number pow(Number n1, Integer n2)
    {
        return decimalize(n1).pow(Objects.requireNonNull(n2));
    }

    public static Number pow(Number n1, Double n2)
    {
        return decimalize(Math.pow(n1.doubleValue(), Objects.requireNonNull(n2)));
    }

    public static Number calc(String func, Map<String, Number> variables)
    {
        EvaluationContext context = new AbacusStandardEvaluationContext();
        if(variables != null)
            variables.forEach(context::setVariable);
        return parser.parseExpression(func).getValue(context, BigDecimal.class);
    }

}
