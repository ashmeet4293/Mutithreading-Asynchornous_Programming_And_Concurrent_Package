package com.company.asynchornous_programming.transforming_completable_future;

import java.util.concurrent.CompletableFuture;

public class TransformingCompletableFuture {
    private static  int celciusToFarenheit(int celcius){
        return (int) (celcius*1.8)+32;
    }
    
    public static void main(String[] args) {
        CompletableFuture completableFuture= CompletableFuture.supplyAsync(()-> {
            try {
                 Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 200;
        });
        completableFuture.thenApply((celcius)-> celciusToFarenheit((int)celcius))
                .thenAccept(System.out::println);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
