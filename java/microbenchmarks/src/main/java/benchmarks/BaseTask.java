package benchmarks;

import java.util.List;
import java.util.function.BiFunction;

public class BaseTask implements Task<List<Integer>, Integer> {

    private BiFunction<List<Integer>, Integer, Integer> runner;

    public BaseTask(BiFunction<List<Integer>, Integer, Integer> runner) {
        this.runner = runner;
    }


    @Override
    public Integer process(List<Integer> data, int it) {
        return runner.apply(data, it);
    }
}
