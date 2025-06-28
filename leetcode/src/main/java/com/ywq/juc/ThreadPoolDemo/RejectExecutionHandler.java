package com.ywq.juc.ThreadPoolDemo;


public interface RejectExecutionHandler {
    void rejectedExecution(Runnable task, DeThreadPool executor);
}
