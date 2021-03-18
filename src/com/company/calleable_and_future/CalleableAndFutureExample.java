package com.company.calleable_and_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class LongTask {
    public static void simulate(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CalleableAndFutureExample {
    public static void main(String[] args) {

        ExecutorService executorService= Executors.newFixedThreadPool(2);
        try{
            Future future = executorService.submit(() -> {
                LongTask.simulate();
                return 10;
            });

            /*This statement will print immediately */
            System.out.println("DO MORE WORK");

            /*This statement will wait on completion on submit method and then print the returned value */
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

    }
}
