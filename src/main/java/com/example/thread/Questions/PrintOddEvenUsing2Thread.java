package com.example.thread.Questions;

public class PrintOddEvenUsing2Thread {
    private final int MAX = 100;
    private int count = 1;

    synchronized public void printEven() throws InterruptedException {
        while(count<= MAX) {
            if (count % 2 != 0) {
                System.out.println("No is not even so waiting to gt it even");
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Number : " + count + " by thread : " + Thread.currentThread().getName());
                count++;
                notify();
            }
        }
    }

    synchronized public void printOdd() throws InterruptedException {
        while(count<= MAX) {
            if (count % 2 == 0) {
                System.out.println("No is not odd so waiting to gt it odd");
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Number : " + count + " by thread : " + Thread.currentThread().getName());
                count++;
                notify();
            }
        }
    }

    public static void main(String[]args) throws InterruptedException {
        PrintOddEvenUsing2Thread obj = new PrintOddEvenUsing2Thread();

        Thread odd = new Thread(() -> {try {obj.printOdd();}catch (Exception ex) {ex.printStackTrace();}}, "odd");
        Thread even = new Thread(() -> {try {obj.printEven();}catch (Exception ex) {ex.printStackTrace();}}, "even");
        odd.start();even.start();
    }
}
