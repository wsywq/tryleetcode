package com.ywq.juc.ThreadPoolDemo;

public class AbortPolicy implements RejectExecutionHandler{
    @Override
    public void rejectedExecution(Runnable task, DeThreadPool executor) {
        throw new RuntimeException("The thread pool is full and the queue is full");
    }
}
