package com.example.thread.threadPoolType;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) throws InterruptedException {

        // Create ThreadPoolExecutor manually with corePoolSize=2, maxPoolSize=4, keepAliveTime=10 seconds, and a bounded queue size 4
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            executor.submit(() -> {
                int result = 0;
                try {
                    result = computation();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Result: " + result + " for taskId: " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    static int computation() throws InterruptedException {
        Thread.sleep(4000); // Simulate work
        int result = 1;
        for (int i = 1; i < 100; i++) {
            result *= i;
        }
        return result;
    }
}
