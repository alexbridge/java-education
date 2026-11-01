package util.function;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.ToDoubleBiFunction;

public class Predicates {
    public static void main(String[] args) {
        DoublePredicate doubleP = d -> d == 10.0;
        IntPredicate intP = d -> d == 10;
        DoubleFunction<Double> doubleF = d -> d;
        ToDoubleBiFunction<Double, Integer> toDouble = (d, t) -> d.doubleValue();

        System.out.println(doubleP.test(10.0));
        System.out.println(intP.test(10));
        System.out.println(doubleF.apply(10));
        System.out.println(toDouble.applyAsDouble(10.0, 10));
    }
}
