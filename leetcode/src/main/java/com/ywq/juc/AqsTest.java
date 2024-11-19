package com.ywq.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AqsTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();
        try{
            System.out.println("begin wait");
            condition.wait();
            System.out.println("end wait");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

        reentrantLock.lock();
        try{
            System.out.println("begin wait");
            condition.signal();
            System.out.println("end signal");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
