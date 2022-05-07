package benchmarks;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Executor<T extends Task<List<Integer>, Integer>, G extends Generator<List<Integer>>> {
    private final String testName;
    private T task;
    private G generator;

    public Executor(String testName, T task, G generator) {
        this.testName = testName;
        this.task = task;
        this.generator = generator;
    }

    public static class Summary {
        @Override
        public String toString() {
            return "Summary{" +
                    "title='" + title + '\'' +
                    ", best=" + best +
                    ", total=" + total +
                    '}';
        }

        private String title;

        private Duration best;

        private Duration total;

        private List<Object> data = new ArrayList<>();

        public List<Object> getData() {
            return data;
        }

        void push(Object o) {
            data.add(o);
        }

    }

    public Summary execute() {
        int executions = 2;
        Summary s = new Summary();
        s.title = testName;

        Instant start = Instant.now();
        System.out.print("gen data ");
        final List<Integer> data = generator.genData();
        System.out.println("data generated");

        List<Integer> trash = new ArrayList<>();
        List<Duration> times = new ArrayList<>();
        for (int i = 0; i < executions; i++) {
            Instant b = Instant.now();
            trash.add(task.process(data, i));
            times.add(Duration.between(b, Instant.now()));
        }
        s.push(trash.size());
        Instant end = Instant.now();
        s.total = Duration.between(start, end);

        Collections.sort(times);

        s.best = times.get(0);
        return s;
    }

}
