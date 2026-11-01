package hackerrank.stat;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @link https://www.hackerrank.com/challenges/s10-basic-statistics
 */
public class MeanMedianMode {

    static {
        // simulate stdin in development purposes
        if (System.getenv("DEVELOPMENT") != null) {

            String stdin = "10\n64630 11735 14216 99233 14470 4978 73429 38120 51135 67060";
            System.setIn(new ByteArrayInputStream(stdin.getBytes()));

            System.out.println("43900.6\n44627.5\n4978");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            numbers.add(scanner.nextInt());
        }

        // Mean calculation
        System.out.println(calculateMean(numbers).orElse(0));

        // Median calculation
        System.out.println(calculateMedian(numbers).orElse(0));

        // Mode calculation
        System.out.println(calculateMode(numbers));

        System.exit(0);

    }

    /**
     * Calculate mean of integers list
     * Cast each integer to double, average a stream
     *
     * @param numbers a list of integers
     * @return optional double (stream can be empty)
     */
    private static OptionalDouble calculateMean(List<Integer> numbers) {
        return numbers.stream().mapToDouble(n -> n).average();
    }

    /**
     * Calculate median for a set
     *
     * @param numbers a list of integers
     * @return optional double (stream can be empty)
     */
    private static OptionalDouble calculateMedian(List<Integer> numbers) {

        int size = numbers.size();

        return numbers.stream()
                .sorted(Integer::compare)
                // element in the middle: 4 of 10
                .skip(size / 2 - 1)
                // take only 2 elements as middle of set
                .limit(2)
                .mapToDouble(e -> e)
                .average();
    }

    private static Integer calculateMode(List<Integer> numbers) {

        Optional<Map.Entry<Integer, Long>> optMax = numbers.stream()
                .sorted(Integer::compare)
                .collect(
                        Collectors.groupingBy(
                                e -> e,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .reduce((left, right) -> {
                    if (left.getValue().equals(right.getValue())) {
                        return left.getKey() < right.getKey() ? left : right;
                    }
                    return left.getValue() > right.getValue() ? left : right;
                });

        return optMax.map(Map.Entry::getKey).orElse(0);
    }
}