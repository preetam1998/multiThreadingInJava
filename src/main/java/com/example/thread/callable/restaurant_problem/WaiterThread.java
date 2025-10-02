package com.example.thread.callable.restaurant_problem;

import java.util.Objects;

public class WaiterThread extends Thread{
    private final Object lock;

    public WaiterThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Waiter - Taking order from Customer");
        try {
            synchronized (lock) {
                System.out.println("Waiter - Telling order to chef");
                lock.wait();
                System.out.println("Waiter - Serving order to Customer");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
