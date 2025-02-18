package com.ywq;

import java.util.HashSet;

public class StringsCode {
    /**
     * 13. Roman to Integer
     *
     * @param s Easy
     */
    public static int romanToInt(String s) {
        int result = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int value = getValue(s.charAt(i));
            if (i < length - 1 && value < getValue(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }
        return result;
    }

    private static int getValue(char c) {
        switch (c) {
            case 'I' -> {
                return 1;
            }
            case 'V' -> {
                return 5;
            }
            case 'X' -> {
                return 10;
            }
            case 'L' -> {
                return 50;
            }
            case 'C' -> {
                return 100;
            }
            case 'D' -> {
                return 500;
            }
            case 'M' -> {
                return 1000;
            }
        }
        return 0;
    }

    /**
     * 14. Longest Common Prefix
     *
     * @param strs Easy
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        String prefix = strs[0];
        int length = prefix.length();

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, --length);
                if (length == 0) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     *
     * @param s Medium
     *          Pay attention to the substring not subsequences
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int k = 0;
        int length = s.length();
        int max = 0;
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (hashSet.contains(s.charAt(i))) {
                while (k < i && hashSet.contains(s.charAt(i))) {
                    hashSet.remove(s.charAt(k));
                    k++;
                }
            }
            hashSet.add(s.charAt(i));
            max = Math.max(max, hashSet.size());
        }

        return max;
    }

    /**
     * @param str character and blank
     * @return rts
     * JDK11 or later, new StringBuilder(str).reverse().toString();
     */
    public static String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.toString();
    }

    /**
     * @param str character array
     * @return without new array
     * leetcode 344 easy
     */
    public static char[] reverseString2(char[] str) {
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
        return str;
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.append(c);
            } else {
                if (stack.length() != 0) {
                    char p = stack.charAt(stack.length() - 1);
                    if (p == '(' && c == ')' || p == '[' && c == ']' || p == '{' && c == '}') {
                        stack.deleteCharAt(stack.length() - 1);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return stack.length() == 0;
    }

    public static int myAtoi(String s) {
        int length = s.length();
        boolean sign = true;
        int index = 0;
        int result = 0;

        // 跳过前导空格
        while (index < length && s.charAt(index) == ' ') {
            index++;
        }
        if (index == length) {
            return 0;
        }

        // 处理符号
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '+';
            index++;
        }

        // 处理数字
        for (int i = index; i < length; i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                break;
            }
            int digit = c - '0';
            // 检查是否会溢出
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
        }

        return sign ? result : -result;
    }

    public static int lengthOfLastWord(String s) {
        int length = s.length();
        int index = length - 1;

        while (index > 0 && s.charAt(index) == ' ') {
            index--;
        }

        int result = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            result++;
            index--;
        }
        return result;
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        if (s.length() == 1) {
            return true;
        }

        while (left <= right) {
            if (!isLetterOrDigitCheck(s.charAt(left))) {
                left++;
                continue;
            }

            if (!isLetterOrDigitCheck(s.charAt(right))) {
                right--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;

//        String result = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
//        StringBuilder stringBuilder = new StringBuilder(result);
//        return result.contentEquals(stringBuilder.reverse());
    }

    private static boolean isLetterOrDigitCheck(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
