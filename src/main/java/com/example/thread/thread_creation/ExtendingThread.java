package com.example.thread.thread_creation;

public class ExtendingThread extends Thread{

    @Override
    public void run() {
        try {
            System.out.println("start doing work by - " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("completed doing work - " + Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            System.out.println("Catched Exception");
        }
    }

    public static void main(String str[]) {
        // create thread which already know what to do
        ExtendingThread thread = new ExtendingThread();

        // call start
        thread.start();

    }
}
