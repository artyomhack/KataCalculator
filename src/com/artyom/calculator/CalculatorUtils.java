package com.artyom.calculator;

import java.util.*;
import java.util.regex.Pattern;

public class CalculatorUtils {
    public static final TreeMap<Integer, String> romanDictionary = new TreeMap<>() {
        {
            put(1, "I");
            put(4, "IV");
            put(5, "V");
            put(9, "IX");
            put(10, "X");
            put(40, "XL");
            put(50, "L");
            put(90, "XC");
            put(100, "C");
            put(400, "CD");
            put(500, "D");
            put(900, "CM");
            put(1000, "M");
        }
    };
    public static final Map<String, Integer> priorityOperation = new HashMap<>() {
        {
            put("/", 2);
            put("*", 2);
            put("-", 1);
            put("+", 1);
        }
    };

    private static Optional<Integer> getKeyByValue(String value) {
        for (Map.Entry<Integer, String> entry : romanDictionary.entrySet()) {
            if (entry.getValue().equals(value))
                return Optional.of(entry.getKey());
        }
        return Optional.empty();
    }

    static Integer toDigit(String romanNumber) {
        if (romanNumber.length() == 1)
            return getKeyByValue(romanNumber).orElseThrow(() -> new RuntimeException("Такого значения нет."));

        int resultDigit = 0;
        for (int i = 0; i < romanNumber.length(); i++) {
            int digitFromRomanNumb_1 = getKeyByValue(String.valueOf(romanNumber.charAt(i)))
                    .orElseThrow(() -> new RuntimeException("Такого значения нет."));

            if (i + 1 < romanNumber.length()) {
                int digitFromRomanNumb_2 = getKeyByValue(String.valueOf(romanNumber.charAt(i+1)))
                        .orElseThrow(() -> new RuntimeException("Такого значения нет"));

                if (digitFromRomanNumb_1 >= digitFromRomanNumb_2)
                    resultDigit += digitFromRomanNumb_1;
                else
                    resultDigit -= digitFromRomanNumb_1;
            }
            else
                resultDigit += digitFromRomanNumb_1;
        }
        return resultDigit;
    }

    public static String toRoman(int digit) {
        StringBuilder output = new StringBuilder();

        while (digit > 0) {
            int key = romanDictionary.floorKey(digit);
            if (digit == key) {
                output.append(romanDictionary.get(key));
                break;
            }
            output.append(romanDictionary.get(key));
            digit -= key;
        }
        return output.toString();
    }
}
