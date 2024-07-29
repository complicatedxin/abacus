package com.zincyanide.math.expression;

import com.zincyanide.math.CalcPrecision;
import com.zincyanide.math.CalcRule;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class AbacusStandardEvaluationContext extends StandardEvaluationContext
{
    private CalcPrecision calcPrecision;

    public AbacusStandardEvaluationContext()
    {
        calcPrecision = CalcRule.DEFAULT_CALC_PRECISION;
    }

    @Override
    public TypeConverter getTypeConverter()
    {
        return new AbacusTypeConverter(calcPrecision);
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
