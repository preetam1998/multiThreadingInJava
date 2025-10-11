package com.example.thread;

public class ThreadInterruption implements Runnable {

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " thread running");
                Thread.sleep(5000);
            }

        } catch (InterruptedException ex) {
            System.out.println("Thread interrupted, shutting down gracefully.");
        }
    }

    public static void main(String args[]) {
        try {
            Thread thread = new Thread(new ThreadInterruption());
            thread.start();
            System.out.println("Main thread running");
            Thread.sleep(8000);
            thread.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
