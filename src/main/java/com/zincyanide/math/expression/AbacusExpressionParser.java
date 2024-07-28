package com.zincyanide.math.expression;

import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.Nullable;
import java.lang.reflect.Field;

public class AbacusExpressionParser extends SpelExpressionParser
{
    @Override
    protected SpelExpression doParseExpression(String expressionString, @Nullable ParserContext context) throws ParseException
    {
        SpelParserConfiguration configuration = null;
        try {
            Field field = this.getClass().getSuperclass().getDeclaredField("configuration");
            field.setAccessible(true);
            configuration = (SpelParserConfiguration) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new AbacusInternalSpelExpressionParser(configuration).doParseExpression(expressionString, context);
    }
}
