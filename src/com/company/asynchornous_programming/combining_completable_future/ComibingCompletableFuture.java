package com.company.asynchornous_programming.combining_completable_future;

import com.company.common.ThreadColor;

import java.util.concurrent.CompletableFuture;

/**
 * Get the price of service in USD from one API (asynchronously)
 * Also we get the exchange rate to Nepali from another API (asynchronously)
 * Once both value were generated
 * Then we combine both threads once each completes its task and generate the result
 *
 * So in dependent multiple case we use .thenCombine method to combine one result to another.
 * */
class ExchangeRate{
    private CompletableFuture<Integer> getPriceOfServiceInUSD(){

        return CompletableFuture.supplyAsync(()-> {
            /*Getting the Price of service from different API */
            System.out.println(ThreadColor.ANSI_CYAN+" Getting the Price Of SERVICE from different API it May Take some Time ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int servicePrice = 200;
            System.out.println(ThreadColor.ANSI_CYAN+" GOT SERICE VALUE IN USD :  "+ servicePrice);
            return  servicePrice;
        });
    }
    private CompletableFuture<Integer> getExchageRateOfUSDToNepali(){
        /*Getting the Exchange rate from different API */

        return CompletableFuture.supplyAsync(()-> {
            System.out.println(ThreadColor.ANSI_RED+" Getting the EXCHANGE RATE from different API it May Take some Time ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int exchangeRate= 120;
            System.out.println(ThreadColor.ANSI_RED+" GOT EXCHANGE RATE FOR USD TO NEPALI :  "+ exchangeRate);
            return exchangeRate;
        });
    }

    public void showServicePrice(){
        getPriceOfServiceInUSD().thenCombine(getExchageRateOfUSDToNepali(), (price, exchangeRate)-> price*exchangeRate)
        .thenAccept(result -> System.out.println(ThreadColor.ANSI_BACKGROUND_BRIGHT_BLACK+" YOUR SERVICE VALUE IN NEPALI CURRENCY IS RS "+result));
    }
}

public class ComibingCompletableFuture {
    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.showServicePrice();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
