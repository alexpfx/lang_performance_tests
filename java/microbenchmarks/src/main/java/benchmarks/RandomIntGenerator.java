package benchmarks;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomIntGenerator implements Generator<List<Integer>> {
    private SecureRandom secureRandom = new SecureRandom();
    private Random pseudoRandom = new Random();
    private int count;
    private int max;

    public static RandomIntGenerator tenMillions() {
        return new RandomIntGenerator(10_000_000, 1000_000_000);
    }
    public static RandomIntGenerator millionsSmallNumbers() {
        return new RandomIntGenerator(100_000_000, 100);
    }

    private RandomIntGenerator(int count, int max) {
        this.count = count;
        this.max = max;
    }


    @Override
    public List<Integer> genData() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // l.add(secureRandom.nextInt(max));
            l.add(pseudoRandom.nextInt(max));
        }
        return l;
    }
}
