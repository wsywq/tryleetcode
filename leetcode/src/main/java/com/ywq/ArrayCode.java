package com.ywq;

import java.util.HashMap;

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
}
