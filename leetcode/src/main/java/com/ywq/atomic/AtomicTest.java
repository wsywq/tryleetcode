package com.ywq.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicTest {
    private static final AtomicLong atomicLong = new AtomicLong();
    private static final Integer[] array1 = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static final Integer[] array2 = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            int size = array1.length;
            for (Integer integer : array1) {
                if (integer == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread threadTwo = new Thread(() -> {
            int size = array2.length;
            for (Integer integer : array2) {
                if (integer == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("count 0:"+atomicLong.get());
    }
}
