package com.company.concurrent_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SynchronizedCollections {

    public static void main(String[] args) {
        Collection<Integer> collections = Collections.synchronizedCollection(new ArrayList<>());

        Thread thread1 = new Thread(() -> collections.addAll(Arrays.asList(1, 2, 3)));
        Thread thread2 = new Thread(() -> collections.addAll(Arrays.asList(4, 5, 6, 7)));
        Thread thread3 = new Thread(() -> collections.addAll(Arrays.asList(8, 9, 10)));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(collections);

    }
}
