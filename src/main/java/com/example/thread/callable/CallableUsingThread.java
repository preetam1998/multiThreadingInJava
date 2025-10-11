package com.example.thread.callable;

import java.util.concurrent.Callable;

public class CallableUsingThread {
    public static void main(String args[]) {
        Runnable runnable = new MyRunnable(() -> {
            System.out.println("Callable is called by callable");
            return "Preetam";
        });

        Thread thread = new Thread(runnable);
        thread.start();

    }
}


class MyRunnable implements Runnable {
    private Callable<String> callable;
    public MyRunnable(Callable<String> callable) {
        this.callable = callable;
    }

    public void run() {
        try {
            System.out.println(callable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}