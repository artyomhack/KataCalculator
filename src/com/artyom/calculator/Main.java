package com.artyom.calculator;

public class Main {
    public static String calc(String input) {
        Calculator calculator = new Calculator(input);
        return calculator.getValue();
    }

    public static void main(String[] args) {
        System.out.println(calc("1 + 3"));
        System.out.println(calc("VI / III"));
//        System.out.println(calc("I - II"));
//        System.out.println(calc("I + 1"));
        System.out.println(calc("1 + 2 + 3"));
    }
}

