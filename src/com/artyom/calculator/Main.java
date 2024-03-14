package com.artyom.calculator;

import java.util.Scanner;

public class Main {
    public static String calc(String input) {
        Calculator calculator = new Calculator(input);
        return calculator.getValue();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
}

