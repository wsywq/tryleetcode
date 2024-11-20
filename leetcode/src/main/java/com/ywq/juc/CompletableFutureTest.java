package com.ywq.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<CompletableFuture<Integer>> futureList = list.stream().map(value -> CompletableFuture.supplyAsync(() ->
                value
        )).toList();

        CompletableFuture<Integer> sumFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v ->
                        futureList.stream().mapToInt(CompletableFuture::join).sum()
                );

        int sum = sumFuture.join();
        System.out.println(sum);

        AtomicInteger atomicInteger = new AtomicInteger();
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("work done" + atomicInteger.incrementAndGet());
        };

        Callable<String> callAble = () -> {
            System.out.println("enter callable");
            Thread.sleep(2000);
            System.out.println("exit callable");
            return "hello from callable";
        };

        FutureTask<String> task = new FutureTask<>(callAble);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Do something else");
        System.out.println("received result from callable" + task.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(callAble);
        System.out.println("received result from callable" + submit.get());
        executorService.shutdown();

    }
}
