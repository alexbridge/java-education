package hackerrank.stat;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @link https://www.hackerrank.com/challenges/s10-quartiles
 */
public class Quartiles {

    static {
        // simulate stdin in development purposes
        if (System.getenv("DEVELOPMENT") != null) {

            String stdin = "10\n3 7 8 5 12 14 21 15 18 14";
            System.setIn(new ByteArrayInputStream(stdin.getBytes()));

            System.out.println("Expected:\n7\n13\n15");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        int[] inN = new int[x];
        Arrays.setAll(inN, el -> scanner.nextInt());
        Arrays.sort(inN);

        double q1, q3;
        double q2 = calculateMedian(inN);

        if (inN.length % 2 != 0) {
            // odd
            q1 = calculateMedian(Arrays.copyOfRange(inN, 0, (inN.length - 1) / 2));
            q3 = calculateMedian(Arrays.copyOfRange(inN, (inN.length + 1) / 2, inN.length));
        } else {
            q1 = calculateMedian(Arrays.copyOfRange(inN, 0, inN.length / 2));
            q3 = calculateMedian(Arrays.copyOfRange(inN, inN.length / 2, inN.length));
        }

        System.out.println((int) q1);
        System.out.println((int) q2);
        System.out.println((int) q3);
        System.exit(0);

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