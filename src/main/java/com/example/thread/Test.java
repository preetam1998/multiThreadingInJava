package com.example.thread;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test implements Callable<String> {

    String name;

    public Test(String name) {
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        try {
            Thread.sleep(2000);
            return name;
        } catch (InterruptedException ex) {
            throw  ex;
        }
    }

    public static void main(String [] args) throws ExecutionException, InterruptedException {

        // Executor Serice
        ExecutorService service = Executors.newFixedThreadPool(1);

        // Test
        Test test = new Test("hii");

        Future<String> future = service.submit(test);

        System.out.println("Received Future  " + future.get());
    }

}