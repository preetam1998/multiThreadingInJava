package com.example.thread.callable.restaurant_problem;

import java.util.Objects;

public class Restaurant {
    public static void main(String[] args) {
        Object lock = new Object();

        WaiterThread waiter = new WaiterThread(lock);
        ChefThread chef = new ChefThread(lock);

        waiter.start();
        chef.start();

    }
}
