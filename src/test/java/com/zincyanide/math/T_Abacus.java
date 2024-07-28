package com.zincyanide.math;

import com.zincyanide.math.op.Abacus;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class T_Abacus
{
    @Test
    public void t_01_abacus()
    {
        Number b = (byte) 1;
        Number d = 8.08D;
        Assert.assertEquals(new BigDecimal("9.08"), Abacus.add(b, d));
        Assert.assertEquals(new BigDecimal("-7.08"), Abacus.subtract(b, d));
        Assert.assertEquals(new BigDecimal("8.08"), Abacus.multiply(b, d));
        Assert.assertEquals(new BigDecimal("0.12"), Abacus.divide(b, d));
    }

    @Test
    public void t_02_expression()
    {
        Assert.assertEquals(new BigDecimal("0.04"),
                Calculator.calc("4 % 1 ", null));
        Assert.assertEquals(new BigDecimal("0.00"),
                Calculator.calc("0 / 1", null));
    }

    @Test
    public void t_03_perform()
    {
        for (int i = 0; i < 500; i++)
        {
            Number number = Calculator.calc("4 * ((5 + 2) / 3 + 1 )", null);
        }

        long start = System.nanoTime();
        System.out.println(Calculator.calc("4 * ((5 + 2^3) / 3 + 1 )", null));
        System.out.println("time elapsed: " + (System.nanoTime() - start) + " ns"); // 0.45 ms
        start = System.nanoTime();
        System.out.println(Calculator.calc("4 * ((5 + 2^3.1) / 3 + 1 )", null));
        System.out.println("time elapsed: " + (System.nanoTime() - start) + " ns"); // 1.54 ms
    }

    @Test
    public void t_04_interpolation()
    {
        Map<String, Number> variables = new HashMap<String, Number>() {{
            put("a", 5);
            put("b", 12);
            put("p", 1_0000);
            put("ir", 0.7);
        }};

        Assert.assertEquals(new BigDecimal("13.00"),
                Calculator.calc("(#a^2 + #b^2)^0.5", variables));

        Assert.assertEquals(new BigDecimal("10070.00"),
                Calculator.calc("#p + #p % #ir", variables));
    }

    @Test
    public void t_05_sqrt()
    {
        BigDecimal value = BigDecimal.valueOf(2);
        int scale = 2;

        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 6;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision)
        {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
//        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        System.out.println(deviation);

        System.out.println(Math.pow(2, 0.5));
    }

}
