package com.ywq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadsCode {

    static void print(String msg){
        System.out.println(msg+"; "+threadLocal.get());
    }

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("threadOne 123");
                print("threadOne");
                threadLocal.remove();
                System.out.println("threadOne remove after: "+threadLocal.get());
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("threadTwo 123");
                print("threadTwo");
                threadLocal.remove();
                System.out.println("threadTwo remove after: "+threadLocal.get());
            }
        });

        thread.start();
        threadTwo.start();
    }
}
