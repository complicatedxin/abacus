package com.zincyanide.math;

import java.util.LinkedList;
import java.util.Queue;

public interface CalcProcess
{
    void recordStep(String op, Object res, Number left, Number right);

    public class ProcessStep
    {
        private String op;
        private Object res;
        private Number left;
        private Number right;

        public ProcessStep(String op, Object res, Number left, Number right)
        {
            this.op = op;
            this.res = res;
            this.left = left;
            this.right = right;
        }
    }

    public class ProcessStepQueue implements CalcProcess
    {
        private String name;

        private String funcExpr;

        private final Queue<ProcessStep> steps = new LinkedList<>();

        public ProcessStepQueue(String funcExpr)
        {
            this("-", funcExpr);
        }
        public ProcessStepQueue(String name, String funcExpr)
        {
            this.name = name;
            this.funcExpr = funcExpr;
        }

        @Override
        public void recordStep(String op, Object res, Number left, Number right)
        {
            add(new CalcProcess.ProcessStep(op, res, left, right));
        }

        public void add(ProcessStep step)
        {
            steps.add(step);
        }

        public void resetProcess()
        {
            steps.clear();
        }

        public String generateProcessReport()
        {
            StringBuilder sb = new StringBuilder("CalcProcess[").append(name)
                    .append("]: ").append(funcExpr).append("\n");

            steps.forEach((step) -> sb.append("\t- ")
                    .append(step.left).append(" ").append(step.op).append(" ").append(step.right)
                    .append(" = ").append(step.res).append("\n"));

            return sb.toString();
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getFuncExpr()
        {
            return funcExpr;
        }

        public void setFuncExpr(String funcExpr)
        {
            this.funcExpr = funcExpr;
        }

    }

}
