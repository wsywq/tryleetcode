package com.ywq;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringsCodeTest {
    @Test
    public void testRomanToInt() {
        int result = StringsCode.romanToInt("III");
        assertEquals(3, result);

        result = StringsCode.romanToInt("IV");
        assertEquals(4, result);

        result = StringsCode.romanToInt("LVIII");
        assertEquals(58, result);
    }

    @Test
    public void testLongestCommonPrefix() {
        String[] strs = {"flower", "flow", "flight"};
        String result = StringsCode.longestCommonPrefix(strs);
        assertEquals("fl", result);

        strs = new String[]{"dog", "racecar", "car"};
        result = StringsCode.longestCommonPrefix(strs);
        assertEquals("", result);
    }

    @Test
    public void testLengthOfLongestSubstring() {
        // Test case 1: Normal case
        String s1 = "abcabcbb";
        int expected1 = 3;
        int actual1 = StringsCode.lengthOfLongestSubstring(s1);
        assertEquals(expected1, actual1);

        // Test case 2: All characters are the same
        String s2 = "aaaaa";
        int expected2 = 1;
        int actual2 = StringsCode.lengthOfLongestSubstring(s2);
        assertEquals(expected2, actual2);

        // Test case 3: Empty string
        String s3 = "pwwkew";
        int expected3 = 3;
        int actual3 = StringsCode.lengthOfLongestSubstring(s3);
        assertEquals(expected3, actual3);

        String s4 = "bwf";
        int expected4 = 3;
        int actual4 = StringsCode.lengthOfLongestSubstring(s4);
        assertEquals(expected4, actual4);


    }
}
