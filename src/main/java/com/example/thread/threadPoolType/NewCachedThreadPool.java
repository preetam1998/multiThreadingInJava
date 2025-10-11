package com.example.thread.threadPoolType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        for(int i=0; i<100; i++) {
            service.execute(() -> {
                int result = 0;
                try {
                    result = NewCachedThreadPool.computation();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Result : " + result + " for taskId : " + Thread.currentThread().getName());
            }) ;
            Thread.sleep(300);
        }

        service.shutdown();
    }

    static int computation() throws InterruptedException {
        Thread.sleep(400);
        int result = 1;
        for(int i=1; i<100; i++) {
            result *= i;
        }
        return result;
    }
}
