package com.artyom.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String calc(String input) {
        PostfixLine postfixLine = new PostfixLine(input);
        return postfixLine.getValue();
    }

    static class Test {
        public static void main(String[] args) {
            System.out.println(calc("2 + 2"));
        }
    }

}

class PostfixLine {
    private String value;
    private Stack<Integer> stackOfNumbs;
    private Stack<String> stackOfOperations;

    private final Map<Character, Integer> romanDictionary = new HashMap<>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
        }
    };

    private final Map<String, Integer> priorityOperation = new HashMap<>() {
        {
            put("/", 2);
            put("*", 2);
            put("-", 1);
            put("+", 1);
        }
    }

    public PostfixLine(String input) {
        value = postfixHandler(input);
    }

    public String getValue() {
        return value;
    }

    private String postfixHandler(String input) {
        String romanPattern = Pattern.compile("^(?=.)I(IX|X|IV|V?I{0,3})").pattern();
        String digitPattern = Pattern.compile("[0-9]").pattern();
        String operationPattern = Pattern.compile("[+\\-*/]").pattern();

        String[] values = input.split(" ");

        for (String value : values) {
            if (value.matches(romanPattern)) {
                stackOfNumbs.push(romanToDigit(value));
            } else if (value.matches(digitPattern)) {

            } else if (value.matches(operationPattern)) {

            }
        }

    }

    private int romanToDigit(String romanNumber) {
        int resultDigit = 0;
        for (int i = 0; i < romanNumber.length(); i++) {
            int digitFromRomanNumb_1 = romanDictionary.get(romanNumber.charAt(i));
            int digitFromRomanNumb_2 = romanDictionary.get(romanNumber.charAt(i + 1));

            if (i + 1 < romanNumber.length()) {
                if (digitFromRomanNumb_1 >= digitFromRomanNumb_2)
                    resultDigit += digitFromRomanNumb_1;
                else
                    resultDigit -= digitFromRomanNumb_1;
            } else
                resultDigit += digitFromRomanNumb_1;
        }
        return resultDigit;
    }

    private void handlerPriorityOperations(String operation) {
        if (stackOfOperations.isEmpty())
            stackOfOperations.push(operation);
        else {
            int priorityNow = priorityOperation.get(operation);
            int priorityBefore = priorityOperation.get(stackOfOperations.peek());

            if (priorityBefore > priorityNow)

        }

    }

    private int calculate(String operationFromStack) {
        if (stackOfNumbs.size() > 1) {
            int b = stackOfNumbs.pop();
            int a = stackOfNumbs.pop();
            if (operationFromStack.equals("+"))
                return a + b;
            if (operationFromStack.equals("-"))
                return a - b;
            if (operationFromStack.equals("*"))
                return a * b;
            if (operationFromStack.equals("/")) {
                try {
                    return a / b;
                } catch (ArithmeticException exception) {
                    System.out.println("Деление на ноль запрещено");
                    throw new ArithmeticException(exception.getMessage());
                } finally {
                    System.out.println("Программа завершилась...");
                }
            }
        }
    }

}
