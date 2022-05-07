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
                    ", worst=" + worst +
                    ", best=" + best +
                    ", total=" + total +
                    '}';
        }

        private String title;

        private Duration worst;
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
        int executions = 3;
        Summary s = new Summary();
        s.title = testName;
        Instant start = Instant.now();

        final List<Integer> data = generator.genData();
        Collections.shuffle(data);

        List<Integer> trash = new ArrayList<>();
        List<Duration> times = new ArrayList<>();
        for (int i = 0; i < executions; i++) {

            Instant b = Instant.now();
            trash.add(task.process(data, i));
            times.add(Duration.between(b, Instant.now()));
            System.out.println(trash.get(0));
        }
        s.push(trash.size());
        Instant end = Instant.now();
        s.total = Duration.between(start, end);

        Collections.sort(times);
        s.worst = times.get(times.size() - 1);
        s.best = times.get(0);


        return s;
    }


}
