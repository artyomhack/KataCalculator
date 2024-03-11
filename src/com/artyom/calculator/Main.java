package com.artyom.calculator;

public class Main {
    public static String calc(String input) {
        Calculator calculator = new Calculator(input);
        return calculator.getValue();
    }
}

