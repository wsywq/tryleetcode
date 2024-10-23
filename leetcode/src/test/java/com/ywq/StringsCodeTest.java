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
}
