package com.artyom.calculator;

import java.util.Stack;

import static com.artyom.calculator.CalculatorUtils.*;

class Calculator {
    private final Stack<Integer> stackOfNumbs = new Stack<>();
    private final String value;

    public Calculator(String input) {
        value = calculation(input);
    }

    public String getValue() {
        return value;
    }

    public Stack<Integer> getStackOfNumbs() {
        return stackOfNumbs;
    }

    private String calculation(String input) {
        boolean isRoman = false;

        PostfixNote postfixNotation = new PostfixNote(input);

        String[] values = postfixNotation.getPostfixLine().split(" ");

        for (String value : values) {
            if (value.isBlank())
                continue;

            if (value.matches(PatternUtil.digitPattern)) {
                int digit = Integer.parseInt(value);
                stackOfNumbs.push(digit);
            }
            else if (value.matches(PatternUtil.romanPattern)) {
                isRoman = true;
                stackOfNumbs.push(toDigit(value));
            }
            else if (value.matches(PatternUtil.operationPattern)) {
                doOperation(value);
            }
        }

        if (isRoman) {
            int digit = stackOfNumbs.pop();
            return CalculatorUtils.toRoman(digit);
        }

        return String.valueOf(stackOfNumbs.pop());
    }

    private void doOperation(String operationFromStack) {
        int result = 0;
        if (stackOfNumbs.size() > 1) {
            int b = stackOfNumbs.pop();
            int a = stackOfNumbs.pop();

            switch (operationFromStack) {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "*" -> result = a * b;
                case "/" -> {
                    try {
                        result = a / b;
                    } catch (ArithmeticException exception) {
                        System.out.println("Деление на ноль запрещено");
                        throw new ArithmeticException(exception.getMessage());
                    }
                }
            }
            stackOfNumbs.push(result);
        }
    }
}
