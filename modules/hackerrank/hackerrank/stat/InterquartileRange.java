package hackerrank.stat;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @link https://www.hackerrank.com/challenges/s10-interquartile-range
 */
public class InterquartileRange {

    static {
        // simulate stdin in development purposes
        if (System.getenv("DEVELOPMENT") != null) {

            String stdin = "6\n" +
                    "6 12 8 10 20 16\n" +
                    "5 4 3 2 1 5";
            System.setIn(new ByteArrayInputStream(stdin.getBytes()));

            System.out.println("Expected:\n9.0");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int[] inNumbers = new int[x];
        int[] inFrequency = new int[x];
        Arrays.setAll(inNumbers, el -> scanner.nextInt());
        Arrays.setAll(inFrequency, el -> scanner.nextInt());

        System.out.println(calculateInterquartileRange(inNumbers, inFrequency));
        System.exit(0);

    }

    private static double calculateInterquartileRange(int[] numbers, int[] freq) {

        int[] result = IntStream.range(0, numbers.length)
                .flatMap(i -> {
                            int[] curr = new int[freq[i]];
                            Arrays.fill(curr, numbers[i]);
                            return IntStream.of(curr);
                        }
                )
                .sorted()
                .toArray();

        double[] qrtls = calculateQuartiles(result);

        return qrtls[2] - qrtls[0];
    }

    private static double[] calculateQuartiles(int[] numbers) {

        double[] q = new double[3];

        q[1] = calculateMedian(numbers);

        if (numbers.length % 2 != 0) {
            // odd
            q[0] = calculateMedian(Arrays.copyOfRange(numbers, 0, (numbers.length - 1) / 2));
            q[2] = calculateMedian(Arrays.copyOfRange(numbers, (numbers.length + 1) / 2, numbers.length));
        } else {
            q[0] = calculateMedian(Arrays.copyOfRange(numbers, 0, numbers.length / 2));
            q[2] = calculateMedian(Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length));
        }

        return q;
    }

    private static double calculateMedian(int[] numbers) {

        if (numbers.length % 2 != 0) {
            // odd, take a middle
            return numbers[(numbers.length - 1) / 2];
        }

        final int size = numbers.length;
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Integer::compare)
                // element in the middle: 4 of 10
                .skip(size / 2 - 1)
                // take only 2 elements as middle of set
                .limit(2)
                .mapToDouble(e -> e)
                .average().getAsDouble();
    }
}