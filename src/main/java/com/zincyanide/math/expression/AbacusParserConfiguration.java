package com.zincyanide.math.expression;

import com.zincyanide.math.CalcPrecision;
import com.zincyanide.math.CalcRule;
import org.springframework.expression.spel.SpelParserConfiguration;

public class AbacusParserConfiguration extends SpelParserConfiguration
{
    private CalcPrecision calcPrecision;

    public AbacusParserConfiguration()
    {
        this.calcPrecision = CalcRule.DEFAULT_CALC_PRECISION;
    }

    public CalcPrecision getCalcPrecision()
    {
        return calcPrecision;
    }

    public void setCalcPrecision(CalcPrecision calcPrecision)
    {
        this.calcPrecision = calcPrecision;
    }

}
