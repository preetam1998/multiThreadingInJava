package com.example.thread.threadLocalVariable;

public class ThreadLocalVariable {
    // This will be unique for every thread
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<>();

    public static void main(String[]args) {

        Runnable runnable = () -> {
            threadId.set((int)(Math.random()*100));
            System.out.println("Work done by thread - " + threadId.get());
        };

        for(int i=0; i<5; i++) {
            final int id = i+1;
            new Thread(runnable, "Thread" + id).start();
        }
    }
}
