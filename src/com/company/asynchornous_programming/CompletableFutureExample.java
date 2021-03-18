package com.company.asynchornous_programming;

import com.company.common.ThreadColor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) {

        CompletableFuture completableFuture=CompletableFuture.runAsync(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println(ThreadColor.ANSI_RED+i);
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
