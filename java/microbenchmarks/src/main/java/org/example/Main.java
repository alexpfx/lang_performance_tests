package org.example;

import benchmarks.DefaultExecutor;
import benchmarks.Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

        tests.add(streamSort);
    }

    public void execute(){
        for (DefaultExecutor ex : tests) {
            final Executor.Summary summary = ex.execute();
            System.out.println(summary);
        }
    }


}