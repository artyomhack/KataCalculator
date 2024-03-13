package com.artyom.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    public static final String romanPattern = Pattern.compile("^(X|IX|V?I{0,3})").pattern();
    public static final String digitPattern = Pattern.compile("^(10|[0-9])").pattern();
    public static final String operationPattern = Pattern.compile("[+\\-*/]").pattern();
}
