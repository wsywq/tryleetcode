package com.ywq;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class ArrayCode {
    public static int[] twoSum(int[] sums, int target) {
        if (sums.length == 2) {
            return new int[]{0, 1};
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < sums.length; i++) {
            int targetMinus = target - sums[i];
            if (hashMap.containsKey(targetMinus)) {
                return new int[]{hashMap.get(targetMinus), i};
            } else {
                hashMap.put(sums[i], i);
            }
        }
        return new int[0];
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[k]) {
                k++;
                nums[k] = nums[i];
            }
        }

        return ++k;
    }

    public static int maxLengthOfArray(int[] nums) {
        HashSet<int[]> ints = new HashSet<>(Collections.singletonList(nums));
        return ints.size();
    }

    /**
     * 1. 有序数组去重
     *
     * @param nums easy
     * @return without set
     */
    public static int maxLengthOfArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int index = 0;

        for (int i = 1; i < len; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return ++index;
    }

    /**
     * remove element
     * @param nums easy
     * @param val value
     * @return int
     * leetcode 27
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                if (nums[right] != val) {
                    nums[left] = nums[right];
                    left++;
                }
                right--;
            } else {
                left++;
            }
        }

        return left;
    }


    public static String addString(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + carry;
            stringBuilder.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        if (carry == 1) {
            stringBuilder.append(1);
        }
        return stringBuilder.reverse().toString();
    }
}
