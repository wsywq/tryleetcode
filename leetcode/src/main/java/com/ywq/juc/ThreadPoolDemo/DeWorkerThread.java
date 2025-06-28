package com.ywq.juc.ThreadPoolDemo;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class DeWorkerThread extends Thread {
    private BlockingQueue<Runnable> taskQueue;
    private List<DeWorkerThread> threads;
    private long keepAliveTime;

    public DeWorkerThread(BlockingQueue<Runnable> taskQueue, long keepAliveTime, List<DeWorkerThread> threads) {
        this.taskQueue = taskQueue;
        this.keepAliveTime = keepAliveTime;
        this.threads = threads;
    }

    @Override
    public void run() {
        long lastActiveTime = System.currentTimeMillis();
        Runnable task;
        while (!Thread.currentThread().isInterrupted() || !taskQueue.isEmpty()) {
            try {
                // 从任务队列中取出任务并执行, 如果队列为空，则阻塞等待
                task = taskQueue.poll(keepAliveTime, TimeUnit.MILLISECONDS);
                RunnableWrapper runnableWrapper = (RunnableWrapper)  task;
                if (task != null) {
                    System.out.printf("WorkThread %s, poll current task: %s%n", Thread.currentThread().getName(), runnableWrapper.getTaskId());
                    task.run();
                    lastActiveTime = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - lastActiveTime >= keepAliveTime) {
                    threads.remove(this);
                    System.out.printf("WorkThread %s exit %n", Thread.currentThread().getName());
                    break;
                }
            } catch (InterruptedException e) {
                System.out.printf("WorkThread %s exit %n", Thread.currentThread().getName());
                threads.remove(this);
                e.printStackTrace();
                break;
            }
        }
    }
}
