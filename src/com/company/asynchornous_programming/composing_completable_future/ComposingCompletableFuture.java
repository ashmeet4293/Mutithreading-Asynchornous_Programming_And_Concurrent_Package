package com.company.asynchornous_programming.composing_completable_future;

import com.company.common.ThreadColor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/** Start the task on Completion of another task
 * Task 1 : get the userEmail using getUserEmailAsync() method from database (Mock)
 * Task 2 : get the playlist using getPlaylistFromUserEmailAsync() providing user email as argument
 * First complete Task 1 and pass its result to Task 2 then process the result
 * i.e generate userEmail from Task 1 then pass it to Task 2 and then get the playlist from the provided email
 **/

class MusicStreamingApp{
    private  CompletableFuture<String> getUserEmailAsync(){
       return CompletableFuture.supplyAsync(()->{
           System.out.println(ThreadColor.ANSI_PURPLE +"Fetching User Mail from database ");
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           /*Mocked data*/
           return "ashmeet4293@gmail.com";
       });
    }

    private  CompletableFuture<List<String>> getPlaylistFromUserEmailAsync(String email){
        return CompletableFuture.supplyAsync(()->{
            System.out.println(ThreadColor.ANSI_CYAN +"Fetching playlist for "+email+" from database ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*Mocked data*/
             return Arrays.asList("Sweet Child o Mine", "Sweet home Chicago", "Thrill is Gone ", "One Last Breath");
        });
    }

    public void showPlaylistBasedOnUserEmail(){
        getUserEmailAsync().thenCompose(this::getPlaylistFromUserEmailAsync)
                .thenAccept(playlist -> System.out.println(playlist));
    }

}

public class ComposingCompletableFuture {
    public static void main(String[] args) {

        MusicStreamingApp musicStreamingApp=new MusicStreamingApp();
        musicStreamingApp.showPlaylistBasedOnUserEmail();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
