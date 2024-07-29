package com.zincyanide.math;

import com.zincyanide.math.expression.AbacusExpressionParser;
import com.zincyanide.math.expression.AbacusStandardEvaluationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Calculator
{
    private AbacusExpressionParser parser = new AbacusExpressionParser();

    private AbacusStandardEvaluationContext evalContext = new AbacusStandardEvaluationContext();

    public static BigDecimal calc(String func, Map<String, Number> variables)
    {
        Calculator calculator = new Calculator();
        calculator.setVariables(variables);
        return calculator.calc(func);
    }

    public BigDecimal calc(String func)
    {
        return parser.parseExpression(func).getValue(evalContext, BigDecimal.class);
    }

    public Calculator precision(int runningPrecision, int finalPrecision)
    {
        return this.precision(runningPrecision, finalPrecision, CalcRule.DEFAULT_ROUND_STRATEGY);
    }

    public Calculator precision(int runningPrecision, int finalPrecision, RoundingMode rounding)
    {
        CalcPrecision calcPrecision = new CalcPrecision(runningPrecision, finalPrecision, rounding);
        parser.configPrecision(calcPrecision);
        evalContext.setCalcPrecision(calcPrecision);
        return this;
    }

    public Calculator variable(String variable, Number number)
    {
        evalContext.setVariable(variable, number);
        return this;
    }

    public Calculator setVariables(Map<String, Number> variables)
    {
        if(variables != null)
            variables.forEach(evalContext::setVariable);
        return this;
    }

}
