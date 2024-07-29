package com.zincyanide.math.expression;

import com.zincyanide.math.CalcPrecision;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateAwareExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class AbacusExpressionParser extends TemplateAwareExpressionParser
{
    private final AbacusParserConfiguration configuration;

    public AbacusExpressionParser()
    {
        this.configuration = new AbacusParserConfiguration();
    }

    public AbacusExpressionParser(AbacusParserConfiguration configuration)
    {
        Assert.notNull(configuration, "AbacusParserConfiguration must not be null");
        this.configuration = configuration;
    }

    public SpelExpression parseRaw(String expressionString) throws ParseException
    {
        return doParseExpression(expressionString, null);
    }

    @Override
    protected SpelExpression doParseExpression(String expressionString, @Nullable ParserContext context) throws ParseException
    {
        return new AbacusInternalSpelExpressionParser(this.configuration).doParseExpression(expressionString, context);
    }

    public void configPrecision(CalcPrecision calcPrecision)
    {
        configuration.setCalcPrecision(calcPrecision);
    }

}
