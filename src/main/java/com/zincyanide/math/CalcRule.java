package com.zincyanide.math;

import java.math.RoundingMode;

public class CalcRule
{
    public static final int DEFAULT_FRACTION_SCALE = 2;

    public static final RoundingMode DEFAULT_ROUND_STRATEGY = RoundingMode.HALF_UP;

    public static final CalcPrecision DEFAULT_CALC_PRECISION = new CalcPrecision(6, DEFAULT_FRACTION_SCALE, DEFAULT_ROUND_STRATEGY);

}
