package com.artyom.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class PostfixNote {
    private String postfixLine;

    public PostfixNote(String infixLine) {
        postfixLine = fromInfixToPostfix(infixLine);
    }

    public String getPostfixLine() {
        return postfixLine;
    }

    private String fromInfixToPostfix(String infixLine) {
        requireValidInfixLine(infixLine);

        Stack<String> operations = new Stack<>();

        String[] values = infixLine.split(" ");
        StringBuilder output = new StringBuilder();

        for (String value : values) {
            if (value.matches(CalculatorUtils.digitPattern))
                output.append(value);

            else if (value.matches(CalculatorUtils.romanPattern))
                output.append(value);


            else if (value.matches(CalculatorUtils.operationPattern))
                refreshOperationsStack(operations, value, output);
            output.append(" ");
        }

        while (!operations.isEmpty())
            output.append(operations.pop()).append(" ");

        output.deleteCharAt(output.length() - 1);

        return output.toString();
    }

    private void refreshOperationsStack(Stack<String> operations, String value, StringBuilder output) {
        if (operations.isEmpty())
            operations.push(value);
        else {
            int priorityCurrent = CalculatorUtils.priorityOperation.get(value);
            int priorityBefore;

            while (priorityCurrent <= (priorityBefore = CalculatorUtils.priorityOperation.get(operations.peek()))) {
                output.append(priorityBefore);
                operations.pop();

                if (operations.isEmpty())
                    break;
            }
            operations.push(value);
        }
    }

    private void requireValidInfixLine(String infixLine) {
        List<String> digitNums = new ArrayList<>();
        List<String> romanNums = new ArrayList<>();
        String[] values = infixLine.split(" ");

        for (String value : values) {
            if (value.isBlank())
                continue;

            if (value.matches(CalculatorUtils.digitPattern))
                digitNums.add(value);
            else if (value.matches(CalculatorUtils.romanPattern))
                romanNums.add(value);
            else if (!value.matches(CalculatorUtils.operationPattern)) {
                throw new RuntimeException("Используйте следующий ряд операций(+, -, /, *), данная операция " + value + " недоступна.");
            }
        }

        if (!digitNums.isEmpty() && !romanNums.isEmpty())
            throw new RuntimeException("используются одновременно разные системы счисления");

        if (digitNums.size() > 2 || romanNums.size() > 2)
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if (!romanNums.isEmpty()) {
            if (CalculatorUtils.toDigit(romanNums.get(0)) < CalculatorUtils.toDigit(romanNums.get(1)))
                throw new RuntimeException("в римской системе нет отрицательных чисел");
        }

        if (infixLine.isEmpty() || infixLine.isBlank() || infixLine.length() == 1)
            throw new RuntimeException("строка не является математической операцией");
    }
}
