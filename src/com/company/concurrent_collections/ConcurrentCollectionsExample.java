package com.company.concurrent_collections;

import com.company.common.ThreadColor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollectionsExample {
    public static void main(String[] args) {

        Map<Integer, List<String>> userNameAndHobbies = new ConcurrentHashMap<>();
        List<String> hobbies = Arrays.asList("Singing", "Guitar", "Cycling", "Trekking", "Hiking", "Riding", "Swimming",
                "Music", "Dancing");

        Thread addOnlyOneUserAndHobbiesThread = new Thread(() -> {
            userNameAndHobbies.put(1, Arrays.asList(hobbies.get(0)));
            System.out.println(ThreadColor.ANSI_RED + "Completed Adding Single User and Hobbies Information by :  " + Thread.currentThread().getName());
        }, "Add Only One User And Hobbies Thread");

        Thread addMultipleUserAndHobbiesThread = new Thread(() -> {
            for (int i = 2; i <= 6; i++) {
                userNameAndHobbies.put(i, Arrays.asList(hobbies.get(i), hobbies.get(i + 1)));
            }
            System.out.println(ThreadColor.ANSI_BLUE + "Completed Adding Multiple User and Hobbies Information by :  " + Thread.currentThread().getName());
        }, "Add Multiple User And Hobbies Thread");

        Thread removeUserAndHobbiesThread = new Thread(() -> {
            userNameAndHobbies.remove(2);
            System.out.println(ThreadColor.ANSI_CYAN + "Completed Removing Single  User and Hobbies Information by :  " + Thread.currentThread().getName());
        }, "Remove User And Hobbies Thread");

        addOnlyOneUserAndHobbiesThread.start();
        addMultipleUserAndHobbiesThread.start();
        removeUserAndHobbiesThread.start();

        try {
            addOnlyOneUserAndHobbiesThread.join();
            addMultipleUserAndHobbiesThread.join();
            removeUserAndHobbiesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ThreadColor.ANSI_BACKGROUND_BRIGHT_BLACK+userNameAndHobbies);

    }
}
