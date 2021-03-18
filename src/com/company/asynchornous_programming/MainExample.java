package com.company.asynchornous_programming;

import java.util.concurrent.CompletableFuture;

public class MainExample {
    public static void main(String[] args) {
         CompletableFuture.supplyAsync(()-> 20).thenCompose(value->{
            System.out.println("Before multiplying the value : "+value);
            return CompletableFuture.supplyAsync(()-> {
                System.out.println("After multiplying the value : "+(value*2));
                return value*2;
            });
        }).thenAccept(value -> System.out.println("NOW AFTER MULTIPLYING "+value));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
