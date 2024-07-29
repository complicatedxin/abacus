package com.zincyanide.math;

import java.math.RoundingMode;

public class CalcPrecision
{
    private int runningPrecision;

    private int finalPrecision;

    private RoundingMode rounding;

    public CalcPrecision(int runningPrecision, int finalPrecision, RoundingMode rounding)
    {
        this.runningPrecision = runningPrecision;
        this.finalPrecision = finalPrecision;
        this.rounding = rounding;
    }

    @Override
    public String toString()
    {
        return "AbacusParserContext{" +
                "runningPrecision=" + runningPrecision +
                ", finalPrecision=" + finalPrecision +
                ", rounding=" + rounding +
                '}';
    }

    public int getRunningPrecision()
    {
        return runningPrecision;
    }

    public void setRunningPrecision(int runningPrecision)
    {
        this.runningPrecision = runningPrecision;
    }

    public int getFinalPrecision()
    {
        return finalPrecision;
    }

    public void setFinalPrecision(int finalPrecision)
    {
        this.finalPrecision = finalPrecision;
    }

    public RoundingMode getRounding()
    {
        return rounding;
    }

    public void setRounding(RoundingMode rounding)
    {
        this.rounding = rounding;
    }

}
