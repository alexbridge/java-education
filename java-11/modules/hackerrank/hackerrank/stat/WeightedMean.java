package hackerrank.stat;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @link https://www.hackerrank.com/challenges/s10-weighted-mean
 */
public class WeightedMean {

    static {
        // simulate stdin in development purposes
        if (System.getenv("DEVELOPMENT") != null) {

            String stdin = "30\n" +
                    "10 40 30 50 20 10 40 30 50 20 1 2 3 4 5 6 7 8 9 10 20 10 40 30 50 20 10 40 30 50\n" +
                    "1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 10 40 30 50 20 10 40 30 50 20";
            System.setIn(new ByteArrayInputStream(stdin.getBytes()));

            System.out.println("Expected: 26.1");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int[] inNumbers = new int[x];
        int[] inWeights = new int[x];

        Arrays.setAll(inNumbers, el -> scanner.nextInt());
        Arrays.setAll(inWeights, el -> scanner.nextInt());

        System.out.printf("%.1f", weightedMean(inNumbers, inWeights));
        System.exit(0);
    }

    private static double weightedMean(int[] numbers, int[] weights) {
        double numerator = IntStream.range(0, numbers.length)
                .mapToDouble(i -> numbers[i] * weights[i])
                .sum();
        int denominator = Arrays.stream(weights).sum();

        return numerator / denominator;
    }
}