package com.ywq;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayCodeTest {

    @Test
    public void testRemoveDuplicates(){
        // Test case 1: Normal array with duplicates
        int[] nums1 = {1, 1, 2, 2, 3, 3};
        int expected1 = 3;
        int actual1 = ArrayCode.removeDuplicates(nums1);
        assertEquals(expected1, actual1);

        // Test case 2: Array with no duplicates
        int[] nums2 = {1, 2, 3, 4, 5};
        int expected2 = 5;
        int actual2 = ArrayCode.removeDuplicates(nums2);
        assertEquals(expected2, actual2);

        // Test case 4: Array with only one element
        int[] nums4 = {1};
        int expected4 = 1;
        int actual4 = ArrayCode.removeDuplicates(nums4);
        assertEquals(expected4, actual4);
    }

    @Test
    public void testAddString() {
        // Test case 1: Normal addition
        String num1 = "123";
        String num2 = "456";
        String expected = "579";
        String result = ArrayCode.addString(num1, num2);
        assertEquals(expected, result);

        // Test case 2: With carry
        num1 = "99";
        num2 = "1";
        expected = "100";
        result = ArrayCode.addString(num1, num2);
        assertEquals(expected, result);

        // Test case 3: One number is empty
        num1 = "123";
        num2 = "";
        expected = "123";
        result = ArrayCode.addString(num1, num2);
        assertEquals(expected, result);

        // Test case 4: Both numbers are empty
        num1 = "";
        num2 = "";
        expected = "";
        result = ArrayCode.addString(num1, num2);
        assertEquals(expected, result);
    }
}
