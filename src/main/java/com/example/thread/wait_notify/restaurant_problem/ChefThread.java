package com.example.thread.wait_notify.restaurant_problem;


public class ChefThread extends Thread{
    private final Object lock;

    public ChefThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Chef - Preparing order");
        try {
            Thread.sleep(5000);
            synchronized (lock) {
                System.out.println("Chef - Order Prepared");
                lock.notify();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
