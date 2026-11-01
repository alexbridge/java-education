package util.function;

import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class Unaries {
    public static void main(String[] args) {
        UnaryOperator<Integer> first = x -> x;
        IntUnaryOperator second = (var x) -> x;

        System.out.println(first.apply(1));
        System.out.println(second.applyAsInt(2));
    }
}
