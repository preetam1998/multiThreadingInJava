package com.example.thread.thread_creation;

public class UsingRunnable implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("start doing work by - " + Thread.currentThread().getName());
            Thread.sleep(10000);
            System.out.println("completed doing work - " + Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            System.out.println("Catched Exception");
        }
    }

    public static void main(String str[]) {
        // create runnable or task you to execute
        UsingRunnable runnable = new UsingRunnable();

        // create thread
        Thread thread = new Thread(runnable);

        // call start
        thread.start();

    }
}
