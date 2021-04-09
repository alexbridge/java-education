package util.function;

import java.util.Comparator;
import java.util.function.Consumer;

public class Lambdas {
    public static void main(String[] args) {
        x((var x) -> {}, (var x, var y) -> 0);
    }

    static void x(Consumer<String> x, Comparator<Boolean> y) {
        System.out.println(x);
        System.out.println(y);
    }
}
