package com.example.thread.wait_notify.restaurant_problem;

public class Restaurant {
    public static void main(String[] args) {
        Object lock = new Object();

        WaiterThread waiter = new WaiterThread(lock);
        ChefThread chef = new ChefThread(lock);

        waiter.start();
        chef.start();

    }
}
