package com.ywq.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Producer producer1 = new Producer(queue, "生产者 1");
        Producer producer2 = new Producer(queue, "生产者 2");
        Thread produceThread1 = new Thread(producer1);
        Thread produceThread2 = new Thread(producer2);
        produceThread1.start();
        produceThread2.start();

        Consumer consumer1 = new Consumer(queue, "消费者 1");
        Consumer consumer2 = new Consumer(queue, "消费者 2");
        Consumer consumer3 = new Consumer(queue, "消费者 3");
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);
        Thread consumerThread3 = new Thread(consumer3);
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();

        Thread.sleep(10000);
        produceThread1.interrupt();
        produceThread2.interrupt();
        consumerThread1.interrupt();
        consumerThread2.interrupt();
        consumerThread3.interrupt();

        System.out.println("主线程退出");

    }

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final String name;
        private int taskId = 0;

        public Producer(BlockingQueue<Integer> queue, String name) {
            this.queue = queue;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int task = this.taskId++;
                    queue.put(task);
                    System.out.printf("[%s] 生产任务 ID: %d，当前队列大小: %d%n",
                            name, taskId, queue.size());
                    Thread.sleep((long) (Math.random() * 500 + 300));

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] 被中断，停止生产。%n", name);
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final String name;

        public Consumer(BlockingQueue<Integer> queue, String name) {
            this.queue = queue;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Integer task = queue.take();
                    System.out.printf("[%s] 消费任务 ID: %d，当前队列大小: %d%n",
                            name, task, queue.size());
                    Thread.sleep((long) (Math.random() * 800 + 300));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("[%s] 被中断，停止消费。%n", name);
            }
        }
    }

}
