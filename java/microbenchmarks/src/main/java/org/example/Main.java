package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
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
                    List<Integer> l = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
                    return l.size();
                });

        final DefaultExecutor normalFilter = DefaultExecutor.newDefaultExecutor("t3 normal filter",
                (numbers, index) -> {
                    List<Integer> l = new ArrayList<>();
                    for (Integer n : numbers) {
                        if (n % 2 == 0) {
                            l.add(n);
                        }
                    }
                    return l.size();
                });

        final DefaultExecutor sumBigDecimalNormal = DefaultExecutor.newSmallNumbersDefaultExecutor("t1 sum normal",
                (intlist, index) -> {
                    BigDecimal sum = BigDecimal.ZERO;
                    for (Integer n : intlist) {
                        if (n % 2 == 0) {
                            sum = sum.add(BigDecimal.valueOf(n));
                        }
                    }

                    return sum.intValue();
                });
        final DefaultExecutor sumMapReduce = DefaultExecutor.newSmallNumbersDefaultExecutor("t2 sum map reduce",
                new BiFunction<List<Integer>, Integer, Integer>() {
                    @Override
                    public Integer apply(List<Integer> integers, Integer integer) {
                        return integers.stream()
                                .filter(n -> n % 2 == 0)
                                .map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add).intValue();
                    }
                });

        tests.add(sumMapReduce);
        tests.add(sumBigDecimalNormal);

    }

    public void execute() {
        for (DefaultExecutor ex : tests) {
            final Executor.Summary summary = ex.execute();
            System.out.println(summary);
        }
    }

}