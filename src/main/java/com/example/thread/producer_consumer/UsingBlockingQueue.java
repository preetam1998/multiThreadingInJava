package com.example.thread.producer_consumer;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int value = 0;
            while (true) {
                System.out.println("Produced: " + value);
                queue.put(value++); // blocks if full
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int val = queue.take(); // blocks if empty
                System.out.println("Consumed: " + val);
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

public class UsingBlockingQueue {

    public static void main(String args[]) {
        try {
            BlockingQueue<Integer> shared = new ArrayBlockingQueue<>(5);

            ExecutorService service = Executors.newFixedThreadPool(2);

            service.execute(new Producer(shared));
            service.execute(new Consumer(shared));

            // make force shutdown
            if(!service.awaitTermination(10, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
