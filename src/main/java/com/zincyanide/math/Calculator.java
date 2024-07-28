package com.zincyanide.math;

import com.zincyanide.math.expression.AbacusExpressionParser;
import com.zincyanide.math.expression.AbacusStandardEvaluationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Calculator
{
    public int runningPrecision;

    public int finalPrecision;

    public RoundingMode rounding;

    private static ExpressionParser parser = new AbacusExpressionParser();

    private EvaluationContext context = new AbacusStandardEvaluationContext();

    public Calculator()
    {
        this(6, CalcRule.DEFAULT_FRACTION_SCALE, CalcRule.DEFAULT_ROUND_STRATEGY);
    }
    public Calculator(int runningPrecision, int finalPrecision, RoundingMode rounding)
    {
        this.runningPrecision = runningPrecision;
        this.finalPrecision = finalPrecision;
        this.rounding = rounding;
    }

    public static BigDecimal calc(String func, Map<String, Number> variables)
    {
        Calculator calculator = new Calculator();
        calculator.setVariables(variables);
        return calculator.calc(func);
    }

    public BigDecimal calc(String func)
    {
        return parser.parseExpression(func).getValue(context, BigDecimal.class);
    }

    public Calculator variable(String variable, Number number)
    {
        context.setVariable(variable, number);
        return this;
    }

    public Calculator setVariables(Map<String, Number> variables)
    {
        if(variables != null)
            variables.forEach(context::setVariable);
        return this;
    }

}
