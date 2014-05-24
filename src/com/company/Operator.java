package com.company;

/**
 * Created by kruczjak on 14.04.14.
 */
public enum Operator {
    ADD(0,'+') {
        @Override
        public double eval(double a, double b) {
            return a+b;
        }
    },
    SUBSTRACT(0,'-') {
        @Override
        public double eval(double a, double b) {
            return a-b;
        }
    },
    MULTIPLY(1,'*') {
        @Override
        public double eval(double a, double b) {
            return a*b;
        }
    }, DIVIDE(1,'/') {
        @Override
        public double eval(double a, double b) {
            if (b==0)
                throw new IllegalArgumentException("Cannot divide by 0");
            return a/b;
        }
    };

    int privilege;
    char sign;

    Operator(int privilege, char sign) {
        this.privilege = privilege;
        this.sign = sign;
    }

    public abstract double eval(double a, double b);
}
