package com.example.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    ReadWriteLock is an interface in java.util.concurrent.locks
    that maintains two types of locks:

    1. Read Lock → can be held by multiple readers simultaneously
        (as long as no writer holds the lock).

    2. Write Lock → is exclusive — only one thread can hold it,
        and no readers are allowed at that time.
*/

class ReadWriteLock {

    public static void main(String[] ags) {

        SharedResource1 resource1 = new SharedResource1();

        Runnable reader = resource1 :: read;
        Runnable write = resource1 :: write;

        for(int i=0; i<10; i++) {
            final int threadId = i + 1;
            if (i%2 != 0) {
                new Thread(reader, "Read-" + threadId).start();
            } else {
                new Thread(write, "Write-" + threadId).start();
            }
        }

    }
}

class SharedResource1{
    private int value = 0;
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Lock readLock = rwLock.readLock();
    private static final Lock writeLock = rwLock.writeLock();

    public void write() {
        try {
            writeLock.lock();
            System.out.println("Write Lock acquired by thread" + Thread.currentThread().getName());
            value++;
            Thread.sleep(3000);
            System.out.println("Write Work completed by thread" + Thread.currentThread().getName());
        } catch (Exception ex) {
            System.out.println("Exception occurred by thread" + Thread.currentThread().getName());
        } finally {
          writeLock.unlock();
        }
    }

    public void read() {
        try {
            readLock.lock();
            System.out.println("Read Lock acquired by thread" + Thread.currentThread().getName());
            System.out.printf("Value : %d read by Thread : %S \n", value, Thread.currentThread().getName());
            Thread.sleep(4000);
            System.out.println("Read Work completed by thread" + Thread.currentThread().getName());
        } catch (Exception ex) {
            System.out.println("Exception occurred by thread" + Thread.currentThread().getName());
        } finally {
            readLock.unlock();
        }
    }
}
