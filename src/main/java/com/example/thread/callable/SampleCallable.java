package com.example.thread.callable;


 import java.util.concurrent.*;

public class SampleCallable {
    public static void main(String[] args) {
        MyCallable callable1 = new MyCallable("Preetam ");
        MyCallable callable2 = new MyCallable("Preeti ");

        // Create Executor
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("call submit on callable1 -> ");
        Future<String> f1 = executorService.submit(callable1);

        System.out.println("call submit on callable2 -> ");
        Future<String> f2 = executorService.submit(callable2);

        try {

            System.out.println("Result for Callable One -> ");
            System.out.println(f1.get());


            System.out.println("Result for Callable Two -> ");
            System.out.println(f2.get());


        } catch (InterruptedException | ExecutionException ex ) {
            System.out.println(ex.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}


class MyCallable implements Callable<String> {

    String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() {
        try {
            System.out.println("Inside call");
            return name.repeat(10);
        } catch(Exception ex) {
            throw  ex;
        }
    }
}
