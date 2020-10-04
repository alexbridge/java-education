package streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntStreams {

    public static void main(String[] args) {

        // 0 1 2 3 4 5 6 7 8 9 10 11
        IntStream.range(0, 12).forEach(System.out::println);

        // From set of literal
        IntStream.of(0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274 ).forEach(System.out::println);

        // From array of int
        int[] intArr = { 1, 1, 2, 3, 5, 8, 13};
        Arrays.stream(intArr).forEach(System.out::println);

        //
        IntStream.iterate(1, i -> i + 2).limit(6).forEach(System.out::println);
    }
}
