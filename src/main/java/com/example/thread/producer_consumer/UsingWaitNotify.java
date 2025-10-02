package com.example.thread.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class UsingWaitNotify {
    private Queue<Integer> queue;
    private int capacity;

    public UsingWaitNotify(int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void producer() {
        try {

            int value = 0;

            while(true) {
                synchronized (this) {
                    while(queue.size() == capacity) {
                        System.out.println("Make Producer Wait");
                        wait();
                    }
                    System.out.println("Producer produce = " + value);
                    queue.add(value++);
                    notifyAll();
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void consumer() {
        try {
            while(true) {
                synchronized (this) {
                    while (queue.isEmpty()) {
                        System.out.println("Make Consumer Wait");
                        wait();
                    }

                    int num = queue.poll();
                    System.out.println("Consumer conusmer = " + num);
                    notifyAll();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String args[]) {
        UsingWaitNotify shared = new UsingWaitNotify(5);

        Thread producer = new Thread(shared :: producer, "Producer");
        Thread consumer = new Thread(shared :: consumer, "Consumer");

        producer.start();
        consumer.start();
    }
}
