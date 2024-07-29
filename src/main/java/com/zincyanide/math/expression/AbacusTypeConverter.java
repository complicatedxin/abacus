package com.zincyanide.math.expression;

import com.zincyanide.math.CalcPrecision;
import com.zincyanide.math.op.Abacus;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.TypeConverter;
import java.math.BigDecimal;

public class AbacusTypeConverter implements TypeConverter
{
    private CalcPrecision calcPrecision;

    public AbacusTypeConverter(CalcPrecision calcPrecision)
    {
        this.calcPrecision = calcPrecision;
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType)
    {
        return true;
    }

    @Override
    public Object convertValue(Object value, TypeDescriptor sourceType, TypeDescriptor targetType)
    {
        BigDecimal val = (BigDecimal) value;
        return Abacus.divide(val, 1, calcPrecision.getFinalPrecision());
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
