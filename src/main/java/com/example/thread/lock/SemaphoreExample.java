package com.example.thread.lock;

import java.util.concurrent.Semaphore;

/*
Use Cases :
    1.Database connection pool → limit number of concurrent DB connections.
    2.Rate limiting → allow only N users to access a resource concurrently.
    3.Resource-bound operations → e.g., file or socket access limits.
*/

public class SemaphoreExample {
    private static final Semaphore semaphore = new Semaphore(3, false);

    public static void main(String[]ags) {

        for (int i = 0; i < 10; i++) {
            final int threadId = i + 1;
            new Thread(() -> doWork(threadId)).start();
        }

    }

    public static void doWork(int threadId) {
        try {
            semaphore.acquire();
            System.out.println("Thread with id started working : " + threadId);
            Thread.sleep(3000);
            System.out.println("Thread with id completed working : " + threadId);

        } catch (Exception ex) {
            System.out.println("Exception occur : " + ex.getMessage());
        } finally {
            semaphore.release();
        }

    }
}
