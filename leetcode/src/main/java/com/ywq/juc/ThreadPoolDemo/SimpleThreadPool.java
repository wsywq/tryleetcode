package com.ywq.juc.ThreadPoolDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SimpleThreadPool implements DeThreadPool {
    private final int initialSize;
    private int maxSize;
    private int coreSize;
    private int queueSize;
    private BlockingQueue<Runnable> taskQueue;
    private List<DeWorkerThread> threads;
    private volatile boolean isShutdown = false;
    private final static RejectExecutionHandler DEFAULT_REJECT_HANDLER = new AbortPolicy();
    private final RejectExecutionHandler rejectHandler;
    private long keepAliveTime;

    public SimpleThreadPool(int initialSize, int maxSize, int coreSize, int queueSize, long keepAliveTime) {
        this(initialSize, maxSize, coreSize, queueSize, keepAliveTime, DEFAULT_REJECT_HANDLER);
    }

    public SimpleThreadPool(int initialSize, int maxSize, int coreSize, int queueSize, long keepAliveTime, RejectExecutionHandler rejectHandler) {
        System.out.printf("初始化线程池: initialSize: %d, maxSize: %d, coreSize: %d%n", initialSize, maxSize, coreSize);
        this.initialSize = initialSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.taskQueue = new LinkedBlockingDeque<>(queueSize);
        this.threads = new ArrayList<>(initialSize);
        this.rejectHandler = rejectHandler;
        this.keepAliveTime = keepAliveTime;
        for (int i = 0; i < initialSize; i++) {
            DeWorkerThread deWorkerThread = new DeWorkerThread(taskQueue, keepAliveTime, threads);
            deWorkerThread.start();
            threads.add(deWorkerThread);
        }
    }

    @Override
    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        RunnableWrapper runnableWrapper = (RunnableWrapper) task;
        System.out.println("put task: " + runnableWrapper.getTaskId());
        if (threads.size() < coreSize) {
            addWorkerThread(task);
            System.out.printf("小于核心线程数，创建新的线程: thread count: %d, queue remainingCapacity : %d%n", threads.size(), taskQueue.remainingCapacity());
        } else if (!taskQueue.offer(task)) {
            // 队列满了
            if (threads.size() < maxSize) {
                addWorkerThread(task);
                System.out.printf("队列已满, 创建新的线程: thread count: %d, queue remainingCapacity : %d%n", threads.size(), taskQueue.remainingCapacity());
            } else {
//                throw new IllegalStateException("ThreadPool is full");
                rejectHandler.rejectedExecution(task, this);
            }
        } else {
            System.out.printf("任务放入队列: thread count: %d, queue remainingCapacity : %d%n", threads.size(), taskQueue.remainingCapacity());
        }
    }

    private void addWorkerThread(Runnable task) {
        DeWorkerThread deWorkerThread = new DeWorkerThread(taskQueue, keepAliveTime, threads);
        deWorkerThread.start();
        threads.add(deWorkerThread);
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        System.out.printf("shutdown thread, count: %d, queue remainingCapacity : %d%n", threads.size(), taskQueue.remainingCapacity());
        isShutdown = true;
        for (DeWorkerThread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("shutdown thread now, count: %d, queue remainingCapacity : %d%n", threads.size(), taskQueue.remainingCapacity());
        isShutdown = true;
        List<Runnable> remainingTasks = new ArrayList<>();
        taskQueue.drainTo(remainingTasks);

        for (DeWorkerThread thread : threads) {
            thread.interrupt();
        }

        return remainingTasks;
    }
}
