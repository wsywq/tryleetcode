package com.ywq.juc.ThreadPoolDemo;

public class SimpleThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(1, 4, 2, 3, 2000, new DiscardPolicy());
        for (int i = 0; i <100 ; i++) {
            simpleThreadPool.execute(new RunnableWrapper(i));
        }
        Thread.sleep(10000);
        simpleThreadPool.shutdown();
    }
}
