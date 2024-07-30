package com.zincyanide.math.expression;

import com.zincyanide.math.CalcPrecision;
import com.zincyanide.math.CalcProcess;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateAwareExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class AbacusExpressionParser extends TemplateAwareExpressionParser
{
    private final AbacusParserConfiguration configuration;

    private CalcProcess.ProcessStepQueue processStepQueue;

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
        AbacusInternalSpelExpressionParser internalParser = new AbacusInternalSpelExpressionParser(this.configuration);
        internalParser.setProcessStepQueue(processStepQueue);
        return internalParser.doParseExpression(expressionString, context);
    }

    public void configPrecision(CalcPrecision calcPrecision)
    {
        configuration.setCalcPrecision(calcPrecision);
    }

    public CalcProcess.ProcessStepQueue getProcessStepQueue()
    {
        return processStepQueue;
    }

    public void setProcessStepQueue(CalcProcess.ProcessStepQueue processStepQueue)
    {
        this.processStepQueue = processStepQueue;
    }
}
