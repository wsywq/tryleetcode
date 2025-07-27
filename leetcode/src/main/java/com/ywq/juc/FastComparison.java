package com.ywq.juc;

import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FastComparison {
    private static final int DATA_SIZE = 1000000;  // 100万条数据
    private static final int THRESHOLD = 1000;     // 任务拆分阈值
    private static HashSet<String> set1;           // 第一个数据集
    private static String[] set1Array;             // set1的数组形式
    private static HashSet<String> set2;           // 第二个数据集
    private static boolean isIdentical = true;     // 比较结果标志

    public static void main(String[] args) {
        // 1. 数据准备阶段
        set1 = createData(DATA_SIZE);
        set1Array = set1.toArray(new String[0]);
        set2 = createData(DATA_SIZE);

        // 2. ForkJoinPool初始化
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 3. 执行并行比较
        long startTime = System.currentTimeMillis();
        CompareTask task = new CompareTask(0, DATA_SIZE);
        forkJoinPool.invoke(task);

        // 4. 清理和结果输出
        forkJoinPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("是否相同: " + isIdentical);
        System.out.println("耗时: " + (endTime - startTime) + "毫秒");
    }

    private static HashSet<String> createData(long size) {
        HashSet<String> setData = new HashSet<>();
        for (int i = 0; i < size; i++) {
            setData.add("item" + i);
        }
        return setData;
    }

    static class CompareTask extends RecursiveAction {
        private final int start;
        private final int end;

        public CompareTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                // 顺序处理小任务
                for (int i = start; i < end; i++) {
                    if (!set2.contains(set1Array[i])) {
                        isIdentical = false;
                    }
                }
            } else {
                // 并行拆分大任务
                int mid = (start + end) / 2;
                CompareTask leftTask = new CompareTask(start, mid);
                CompareTask rightTask = new CompareTask(mid, end);
                invokeAll(leftTask, rightTask);
            }
        }
    }
}
