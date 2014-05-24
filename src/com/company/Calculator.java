package com.company;

import java.util.Stack;

/**
 * Created by kruczjak on 14.04.14.
 */
public class Calculator {
    private Stack<Operand> operands = new Stack<Operand>();
    private Stack<Operator> operators = new Stack<Operator>();
    private Operator[] operatorList = Operator.values();
    private String number="";

    public static double calculate(String input)   {
        Calculator calculator = new Calculator();

        for (int i=0;i<input.length();i++)  {
            calculator.processChar(input.charAt(i));
        }
        calculator.pushNumber();

        while (!calculator.operators.isEmpty()) {
            calculator.reduce();
        }

        return calculator.operands.pop().getNumber();
    }

    private void processChar(char c) {
        if (c==' ') return;
        for (Operator op : operatorList)    {
            if (op.sign==c) {
                pushNumber();
                operators.push(op);
                return;
            }
        }

        number = number + c;
    }

    private void pushNumber() {
        operands.push(new Operand(Double.valueOf(number)));
        number="";
        checkToReduce();
    }

    private void checkToReduce() {
        if (operators.isEmpty()) return;

        if (operators.peek().privilege>0)   {
            reduce();
        }
    }

    private void reduce() {
        Operator operator = operators.pop();
        Operand operand2 = operands.pop();
        Operand operand1 = operands.pop();
        double partial = operator.eval(operand1.getNumber(), operand2.getNumber());
        operands.push(new Operand(partial));
    }

    public static double calculate(String[] args) {
        Calculator calculator = new Calculator();

        for (String arg : args)  {
            calculator.processString(arg);
        }

        while (!calculator.operators.isEmpty()) {
            calculator.reduce();
        }

        return calculator.operands.pop().getNumber();
    }

    private void processString(String arg) {
        if (arg.length()==1) {
            for (Operator op : operatorList) {
                if (op.sign == arg.charAt(0)) {
                    pushNumber();
                    operators.push(op);
                    return;
                }
            }
        }
        operands.push(new Operand(Double.valueOf(arg)));
        checkToReduce();
    }
}
