package com.zincyanide.math.number;

import java.math.BigDecimal;

public class NumberUtil
{
    private NumberUtil() {   }

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

}
