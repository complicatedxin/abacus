package com.zincyanide.math.op;

import com.zincyanide.math.CalcRule;
import com.zincyanide.math.number.NumberUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Abacus
{
    private static BigDecimal decimalize(Number n)
    {
        return NumberUtil.decimalize(n);
    }

    public static BigDecimal add(Number n1, Number n2)
    {
        return decimalize(n1).add(decimalize(n2));
    }

    public static BigDecimal subtract(Number n1, Number n2)
    {
        return decimalize(n1).subtract(decimalize(n2));
    }

    public static BigDecimal multiply(Number n1, Number n2)
    {
        return decimalize(n1).multiply(decimalize(n2));
    }

    public static BigDecimal divide(Number n1, Number n2)
    {
        return divide(n1, n2, CalcRule.DEFAULT_FRACTION_SCALE, CalcRule.DEFAULT_ROUND_STRATEGY);
    }
    public static BigDecimal divide(Number n1, Number n2, int fractionScale)
    {
        return divide(n1, n2, fractionScale, CalcRule.DEFAULT_ROUND_STRATEGY);
    }
    public static BigDecimal divide(Number n1, Number n2, int fractionScale, RoundingMode mode)
    {
        return decimalize(n1).divide(decimalize(n2), fractionScale, mode);
    }

    public static BigDecimal pow(Number n1, Integer n2)
    {
        return decimalize(n1).pow(Objects.requireNonNull(n2));
    }

    public static BigDecimal pow(Number n1, Double n2)
    {
        return decimalize(Math.pow(n1.doubleValue(), Objects.requireNonNull(n2)));
    }

}
