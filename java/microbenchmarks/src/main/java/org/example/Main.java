package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import benchmarks.DefaultExecutor;
import benchmarks.Executor;

public class Main {
    List<DefaultExecutor> tests = new ArrayList<>();

    public static void main(String[] args) {
        final Main main = new Main();
        main.create();
        main.execute();
    }

    public void create() {
        final DefaultExecutor streamSort = DefaultExecutor.newDefaultExecutor("t1 stream with sort",
                (numbers, index) -> {
                    final Stream<Integer> sorted = numbers.stream().sorted();

                    return sorted.findFirst().get();
                });

        final DefaultExecutor streamFilter = DefaultExecutor.newDefaultExecutor("t2 stream filter",
                (numbers, index) -> {
                    List<Integer> l = numbers.stream().filter(n -> n > 10000 && n < 10005).collect(Collectors.toList());
                    return l.size();
                });

        final DefaultExecutor normalFilter = DefaultExecutor.newDefaultExecutor("t3 normal filter",
                (numbers, index) -> {
                    List<Integer> l = new ArrayList<>();
                    for (Integer n : numbers) {
                        if (n > 10000 && n < 10005) {
                            l.add(n);
                        }
                    }
                    return l.size();
                });

                
        tests.add(streamFilter);
        tests.add(normalFilter);
    
    }

    public void execute() {
        for (DefaultExecutor ex : tests) {
            final Executor.Summary summary = ex.execute();
            System.out.println(summary);
        }
    }

}