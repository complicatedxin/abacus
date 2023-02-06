package com.zincyanide.calculator.expression;

import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class AbacusStandardEvaluationContext extends StandardEvaluationContext
{
    @Override
    public TypeConverter getTypeConverter()
    {
        return new AbacusTypeConverter();
    }
}
