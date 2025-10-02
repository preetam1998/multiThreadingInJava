package com.example.thread.wait_notify;

class SharedResource {

    public synchronized void doWait() {
        try {
            System.out.println("Thread is waiting.");
            wait();
            System.out.println("Thread is completing its work now.");
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized void doNotify() {
        try {
            System.out.println("Thread is calling notify.");
            notify();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class WaitNotifyExample {

    public static void main(String []arg) {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(resource::doWait, "Thread_1");
        Thread t2 = new Thread(resource::doNotify, "Thread_2");

        t1.start();
        t2.start();


    }
}

