package streams;

import java.util.stream.Stream;

public class Reducers {

    public static void main(String[] args) {

        Stream<String> stream = Stream.of("w", "o", "l", "f!");

        int length = stream.reduce(0, (i, s) -> {
            System.out.println("Acc: " + i  + ":" + s);
            return i + s.length();
        }, (a, b) -> {
            System.out.println("Combiner: " + a  + ":" + b);
            return a + b;
        });

        System.out.println(length); // 5
    }
}
