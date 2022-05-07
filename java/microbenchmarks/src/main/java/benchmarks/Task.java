package benchmarks;

public interface Task<I, O> {
    O process(I data, int it);

}
