package com.example.thread.callable.restaurant_problem;


public class ChefThread extends Thread{
    private final Object lock;

    public ChefThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Chef - Preparing order");
        try {
            synchronized (lock) {
                Thread.sleep(5000);
                System.out.println("Chef - Order Prepared");
                lock.notify();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
