package benchmarks;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomIntGenerator implements Generator<List<Integer>> {
    private SecureRandom secureRandom = new SecureRandom();
    private int count;
    private int max;

    public static RandomIntGenerator tenMillions() {
        return new RandomIntGenerator(10_000_000, 1000_000_000);
    }

    private RandomIntGenerator(int count, int max) {
        this.count = count;
        this.max = max;
    }


    @Override
    public List<Integer> genData() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            l.add(secureRandom.nextInt(max));
        }
        return l;
    }
}
