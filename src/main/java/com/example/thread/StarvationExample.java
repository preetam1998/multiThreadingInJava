package com.example.thread;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StarvationExample {

    private static AtomicInteger[] completedTask = new AtomicInteger[3];

    static {
        for (int i=0; i<3; i++) {
            completedTask[i] = new AtomicInteger(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {



        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateTime  = LocalDateTime.now();

        LocalDate lastDATE = LocalDate.parse("30-10-2025");




        System.out.println("Without Using Thread Pool");
        withoutThreadpool();



        for(int i=0; i<3; i++) {
            completedTask[i] = new AtomicInteger(0);
        }


        System.out.println("With Using Thread Pool");
        //withThreadpool();
    }

    public static void withoutThreadpool() throws InterruptedException {

        final Object object = new Object();
        for(int i=0; i<10; i++) {
            Thread thread = new Thread(new PriorityTask(i%3, object));
            thread.setPriority(Thread.MIN_PRIORITY + (i%2*2));
            thread.start();
        }

        // wait to see result
        Thread.sleep(9000);


        // Display results
        System.out.println("Tasks completed by priority:");
        System.out.println("Low priority: " + completedTask[0].get());
        System.out.println("Medium priority: " + completedTask[1].get());
        System.out.println("High priority: " + completedTask[2].get());
    }


    static class PriorityTask implements Runnable {
        private final int priority;
        private final Object resource;

        public PriorityTask(int priority, Object resource) {
            this.priority = priority;
            this.resource = resource;
        }

        @Override
        public void run() {
            for(int i=0; i<10; i++) {

                try {
                    synchronized (resource) {
                        Thread.sleep(10 + (priority * 10L));
                        completedTask[priority].incrementAndGet();
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}