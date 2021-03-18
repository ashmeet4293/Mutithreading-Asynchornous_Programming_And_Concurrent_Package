package com.company.asynchornous_programming.running_code_on_completion;

import com.company.common.ThreadColor;

import java.util.concurrent.CompletableFuture;

public class RunningCodeOnCompletionExample {
    public static void main(String[] args) {

       CompletableFuture completableFuture= CompletableFuture.supplyAsync(()-> 10);
       completableFuture.thenAcceptAsync((result)->{
           int loopValue =(int)result;
           for (int i = 0; i < loopValue; i++) {
               System.out.println(ThreadColor.ANSI_CYAN+"Running "+i);
           }

           System.out.println(ThreadColor.ANSI_GREEN+"Completed All Task ");
       });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
