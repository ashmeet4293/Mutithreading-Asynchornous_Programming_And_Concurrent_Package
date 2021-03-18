package com.company.asynchornous_programming.AsynchronousAPIImplementation;

import java.util.concurrent.CompletableFuture;

class MailService{
    private void send(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Mail was Sent.. ");
    }

    public CompletableFuture<Void> sendAsync(){
        return CompletableFuture.runAsync(()-> send());
    }
}
/**
 * Simple Asynchronous API example
 * Task 1 : Send the main from Mail Service
 * Task 2 : Printing Hello world !
 * Here Both task run Asynchronously
 **/
public class AsynchronousAPIExample {
    public static void main(String[] args) {
        MailService mailService = new MailService();
        mailService.sendAsync();

        System.out.println("Hello World");
        try {
            /* Holding main Thread for 5 second so that other child Asynchronous thread will complete its execution*/
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
