package org.example;

import benchmarks.Executor;
import benchmarks.Generator;
import benchmarks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Main {
    Random r = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        final Executor.Summary e1 = new Executor<>("collections 1 ", collectionsSort, randomIntGen).execute();
        final Executor.Summary e2 = new Executor<>("stream 1", streamSort, randomIntGen).execute();
        final Executor.Summary e4 = new Executor<>("stream 2", streamSort, randomIntGen).execute();
        final Executor.Summary e3 = new Executor<>("collections 2 ", collectionsSort, randomIntGen).execute();

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);
    }

    Generator<List<Integer>> randomIntGen = () -> {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i < 10000000; i++) {
            l.add(r.nextInt(i));
        }

        return l;
    };

    Task<List<Integer>, Integer> collectionsSort = (data, it) -> {
        Collections.sort(data);
        return data.get(0);
    };

    Task<List<Integer>, Integer> streamSort = (data, it) -> {
        final Optional<Integer> first = data.stream().sorted().findFirst();
        return first.orElse(null);
    };
}