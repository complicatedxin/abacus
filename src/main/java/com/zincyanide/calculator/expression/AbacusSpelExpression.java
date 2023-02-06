package com.zincyanide.calculator.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.*;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.standard.SpelExpression;

public class AbacusSpelExpression extends SpelExpression
{
    public AbacusSpelExpression(String expression, SpelNodeImpl ast, SpelParserConfiguration configuration)
    {
        super(expression, ast, configuration);
    }

    @Override
    public EvaluationContext getEvaluationContext()
    {
        return new AbacusStandardEvaluationContext();
    }
}
