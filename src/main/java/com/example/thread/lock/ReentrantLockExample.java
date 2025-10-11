package com.example.thread.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/*
    Note -> synchronised work on same object,
    where when we have multi object try to access same code then we use LOCK.

    ReentrantLock is a class in java.util.concurrent.locks that provides an explicit
    locking mechanism — similar to synchronise, but with more flexibility and control.

    It is called reentrant because a thread that already holds the lock can acquire
    it again without getting blocked.
*/

class ReentrantLockExample {

    public static void main(String[] ags) {

        for(int i=0; i<10; i++) {
            final int threadId = i + 1;
            new Thread(() -> new SharedResource().run(), "Thread" + threadId).start();
        }
    }
}

class SharedResource implements Runnable{

    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("Lock acquired by thread" + Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("Work completed by thread" + Thread.currentThread().getName());
        } catch (Exception ex) {
            System.out.println("Exception occurred by thread" + Thread.currentThread().getName());
        } finally {
          lock.unlock();
        }
    }
}
