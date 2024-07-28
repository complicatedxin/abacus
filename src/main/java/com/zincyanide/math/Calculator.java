package com.zincyanide.math;

import com.zincyanide.math.expression.AbacusExpressionParser;
import com.zincyanide.math.expression.AbacusStandardEvaluationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator
{
    private static ExpressionParser parser = new AbacusExpressionParser();

    public static BigDecimal calc(String func, Map<String, Number> variables)
    {
        EvaluationContext context = new AbacusStandardEvaluationContext();
        if(variables != null)
            variables.forEach(context::setVariable);
        return parser.parseExpression(func).getValue(context, BigDecimal.class);
    }

}
