package util.function;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

public class Lambdas {
    public static void main(String[] args) {
        x((var x) -> {}, (var x, var y) -> 0);

        DoubleFunction<Double> area = r -> Math.PI*r*r;

        DoublePredicate test = (x) -> x == 1.00;

        BiConsumer<String, String> biCon3 = (final var x, var y) -> System.out.println(x+y);

        BiConsumer<String, String> biCon1 = (final var x, final var y) -> System.out.println(x+y);
    }

    static void x(Consumer<String> x, Comparator<Boolean> y) {
        System.out.println(x);
        System.out.println(y);
    }
}
