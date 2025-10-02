package com.example.thread.thread_creation;

import java.util.concurrent.*;

public class UsingCallable implements Callable<String> {

    String name;

    public UsingCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() {
        try {
            StringBuilder sb = new StringBuilder(name);
            Thread.sleep(5000);
            for(int i=0; i<5; i++) {
                sb.append("Callable ").append(name)
                        .append(" is running: ").append(i).append("n");
            }
            return sb.toString();
        } catch (InterruptedException ex) {
            System.out.println("Catched Exception");
        }
        return "";
    }

    public static void main(String str[]) {
        // create thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // Create Callable instances
        Callable<String> callable1 = new UsingCallable("Task 1");
        Callable<String> callable2 = new UsingCallable("Task 2");

        try {
            Future<String> future1 = executorService.submit(callable1);
            Future<String> future2 = executorService.submit(callable2);

            // Get results from Future objects
            System.out.println("Result from first task:");
            System.out.println(future1.get()); // Blocks until the task completes

            System.out.println("Result from second task:");
            System.out.println(future2.get()); // Blocks until the task completes
        } catch (ExecutionException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            // gracefully shutdown
            executorService.shutdown();
        }
    }
}
