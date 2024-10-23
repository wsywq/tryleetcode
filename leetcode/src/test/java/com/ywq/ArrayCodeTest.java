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
}
