package com.ywq.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest2 {

    public static AtomicInteger race = new AtomicInteger();

    public static final int MAX_THREADS = 20;
    public static void main(String[] args) {
        Thread[] threads = new Thread[MAX_THREADS];
        for (int i = 0; i <MAX_THREADS ; i++) {
            threads[i] =new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        race.incrementAndGet();
                    }
                }
            });
            threads[i].start();
        }

        while(Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(race);
    }
}
