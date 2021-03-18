package com.company.asynchornous_programming.project_best_price_finder;

import com.company.common.ThreadColor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Suppose we have different price quotation from different sites (multiple)
 * We want a multiple threads to access the multiple sites Asynchronously
 * and get the price information (Elapsed time can be different based on site response time)
 * Now once we got all the price information from multiple sites
 * we need to print the Minimum Price value and also total elapsed time
 * */

class Quote{
    private String siteName;
    private double price;

    public Quote(String siteName, double price) {
        this.siteName = siteName;
        this.price = price;
    }

    public String getSiteName() {
        return siteName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "siteName='" + siteName + '\'' +
                ", price=" + price +
                '}';
    }
}

class FlightService{
    private CompletableFuture<Quote> getQuote(String site){
       return CompletableFuture.supplyAsync(()->{
           System.out.println(ThreadColor.ANSI_PURPLE+"Getting quote from "+site);

           Random random= new Random();
           try {
               Thread.sleep(1000+random.nextInt(3000));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           return new Quote(site, 1000+random.nextInt(2400));
       });
    }

    public Stream<CompletableFuture<Quote>> getQuotes(){
        List<String> sites= Arrays.asList("site1", "site2","site3","site4", "site5");
        return  sites.stream().map(this::getQuote);
    }


}

public class BestPriceFinder {
    public static void main(String[] args) {

        getBestPriceFromDifferentSitesAndElapsedTime(LocalTime.now(),  getCompletableFutureQuoteListAndApplyAsyncForEachTask(new FlightService()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static List<CompletableFuture<Quote>> getCompletableFutureQuoteListAndApplyAsyncForEachTask(FlightService flightService) {
        return flightService.getQuotes()
                .map(quoteCompletableFuture -> quoteCompletableFuture.thenApplyAsync((quoteObject) -> {
                    System.out.println(ThreadColor.ANSI_GREEN+"Retrieved Quote info from "+quoteObject.getSiteName()+" :  "+quoteObject);
                    return quoteObject;
                }))
                .collect(Collectors.toList());
    }

    private static void getBestPriceFromDifferentSitesAndElapsedTime(LocalTime startTime, List<CompletableFuture<Quote>> futureQuotes) {
        CompletableFuture.allOf(futureQuotes.toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    CompletableFuture<Quote> quote1 = futureQuotes.stream()
                            .min((x, y) -> {
                                int value = 0;
                                try {
                                    value = Double.compare(x.get().getPrice(), y.get().getPrice());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                return value;
                            })
                            .orElseThrow(NoSuchElementException::new);

                    try {
                       System.out.println(ThreadColor.ANSI_BACKGROUND_BRIGHT_BLACK+"BEST PRICE VALUE QUOTE : "+quote1.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    LocalTime endTime = LocalTime.now();
                    Duration duration = Duration.between(startTime, endTime);
                    System.out.println(ThreadColor.ANSI_BACKGROUND_BRIGHT_BLACK+"Retrieved all quotes in : "+duration.toMillis()+" ms ");
                });
    }
}

