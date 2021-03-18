package com.company.thread_pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrintJobs extends Thread{
    private String printValue;

    public  PrintJobs(String printValue){
        this.printValue= printValue;
    }

    @Override
    public void run() {
        System.out.println("Printing : "+printValue+" BY THREAD : "+Thread.currentThread().getName());
    }
}

public class ThreadPoolsExample {

    public static void main(String[] args) {

        PrintJobs printJobs[]= {new PrintJobs("Ashmeet"),
                new PrintJobs("Pawan "),
                new PrintJobs("Tiwari "),
                new PrintJobs("Java"),
                new PrintJobs("JVM"),};

        ExecutorService executorService= Executors.newFixedThreadPool(2);
        for(PrintJobs printJob : printJobs){
            executorService.submit(printJob);
        }

        executorService.shutdown();

    }
}
