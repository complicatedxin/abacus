package com.zincyanide.calculator.expression;

import com.zincyanide.calculator.Abacus;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.TypeConverter;
import java.math.BigDecimal;

public class AbacusTypeConverter implements TypeConverter
{
    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType)
    {
        return true;
    }

    @Override
    public Object convertValue(Object value, TypeDescriptor sourceType, TypeDescriptor targetType)
    {
        BigDecimal val = (BigDecimal) value;
        return Abacus.divide(val, 1, 2);
    }
}
