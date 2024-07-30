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

    private CalcProcess.ProcessStepQueue processStepQueue;

    public static BigDecimal calc(String func, Map<String, Number> variables)
    {
        return new Calculator()
                .variables(variables)
                .calc(func);
    }

    public BigDecimal calc(String func)
    {
        if(processStepQueue != null)
        {
            processStepQueue.resetProcess();
            processStepQueue.setFuncExpr(func);
            parser.setProcessStepQueue(processStepQueue);
        }
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

    public Calculator variables(Map<String, Number> variables)
    {
        if(variables != null)
            variables.forEach(evalContext::setVariable);
        return this;
    }

    public Calculator recordProcess(String name)
    {
        this.processStepQueue = new CalcProcess.ProcessStepQueue(name, null);
        return this;
    }

    public CalcProcess.ProcessStepQueue getProcessStepQueue()
    {
        return processStepQueue;
    }

}
