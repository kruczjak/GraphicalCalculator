package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double result;
        if (args.length==0)
            result = processFromScanner();
        else if (args.length==1)
            result = Calculator.calculate(args[0]);
        else
            result = Calculator.calculate(args);

        System.out.println(result);
    }

    private static double processFromScanner() {
        System.out.println("Podaj wyrażenie:");
        Scanner scanner = new Scanner(System.in);
        return Calculator.calculate(scanner.nextLine());
    }
}