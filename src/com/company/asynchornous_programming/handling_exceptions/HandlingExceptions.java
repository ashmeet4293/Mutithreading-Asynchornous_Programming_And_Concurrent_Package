package com.company.asynchornous_programming.handling_exceptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandlingExceptions {
    public static void main(String[] args) {

        CompletableFuture completableFuture= CompletableFuture.supplyAsync(()->{
            System.out.println("Getting the current Weather");
            throw  new IllegalStateException();
        });

        try {
            int temperature = (int)  completableFuture.exceptionally((result)->20).get();
            System.out.println(temperature);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




    }
}
