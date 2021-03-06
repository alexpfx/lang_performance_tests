package benchmarks;

import java.util.List;
import java.util.function.BiFunction;

public class DefaultExecutor extends Executor<BaseTask, RandomIntGenerator> {

    public static DefaultExecutor newDefaultExecutor(String name, BiFunction<List<Integer>, Integer, Integer> runner) {
        return new DefaultExecutor(name, new BaseTask(runner), RandomIntGenerator.tenMillions());
    }

    public static DefaultExecutor newSmallNumbersDefaultExecutor(String name, BiFunction<List<Integer>, Integer, Integer> runner) {
        return new DefaultExecutor(name, new BaseTask(runner), RandomIntGenerator.millionsSmallNumbers());
    }

    private DefaultExecutor(String testName, BaseTask task, RandomIntGenerator generator) {
        super(testName, task, generator);
    }


}
