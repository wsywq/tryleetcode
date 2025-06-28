package com.ywq.juc.ThreadPoolDemo;

public class DiscardPolicy implements RejectExecutionHandler{
    @Override
    public void rejectedExecution(Runnable task, DeThreadPool executor) {
        // do nothing
        RunnableWrapper runnableWrapper = (RunnableWrapper) task;
        System.out.println("task reject:"+ runnableWrapper.getTaskId());
    }
}
