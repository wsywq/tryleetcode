package com.ywq.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrint100 {
    private static final int WORKER_COUNT = 3;
    private static int countIndex = 0;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        final List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < WORKER_COUNT; i++) {
            Condition condition = LOCK.newCondition();
            conditions.add(condition);
            new Worker(i, conditions).start();
        }

//        new ThreadPoolExecutor(10,20,100,100).execute();
    }

    static class Worker extends Thread {
        int index;
        List<Condition> conditions;

        public Worker(int index, List<Condition> conditions) {
            super("Thread-" + index);
            this.index = index;
            this.conditions = conditions;
        }

        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    if (countIndex % 3 != index) {
                        conditions.get(index).await();
                    }
                    if (countIndex > 100) {
                        signNext();
                        return;
                    }
                    System.out.println(this.getName() + " " + countIndex);
                    countIndex++;
                    signNext();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }

        private void signNext() {
            conditions.get((index + 1) % conditions.size()).signal();
        }
    }
}
