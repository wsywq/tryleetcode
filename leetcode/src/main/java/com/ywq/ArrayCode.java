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
}
