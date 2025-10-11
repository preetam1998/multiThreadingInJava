package com.example.thread.threadPoolType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool {

    public static void main(String[] args) {
        int CPU_CORE = Runtime.getRuntime().availableProcessors();
        System.out.println(CPU_CORE);

        ExecutorService service = Executors.newFixedThreadPool(CPU_CORE);

        for(int i=0; i<10; i++) {
            service.execute(() -> {
                int result = NewFixedThreadPool.computation();
                System.out.println("Result : " + result + " for taskId : " + Thread.currentThread().getName());
            }) ;
        }

        service.shutdown();
    }

    static int computation() {
        int result = 1;
        for(int i=1; i<100; i++) {
            result *= i;
        }
        return result;
    }
}
