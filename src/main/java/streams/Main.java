package streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        double x = IntStream.of(1, 3, 5).filter(i -> i % 2 == 0).average().orElse(0); //1
        System.out.println(x);

        System.out.println(
                DoubleStream
                        .of(1.0, 3.0, 5.0)
                        .filter(i -> i % 2 == 0)
                        .average()
                        .orElse(0) //1
        );

        System.out.println(
                DoubleStream
                        .of(1.0, 3.0, 5.0)
                        .filter(i -> i % 2 == 0)
                        .sum() //1
        );

        int y = IntStream.of(1, 3, 5).filter(i -> i % 2 != 0).sum();//2
        System.out.println(y);

        long z = IntStream.of(1, 3, 5).filter(i -> i % 3 != 0).count();//3
        System.out.println(z);

        HashMap<Integer, Person> persons = new HashMap<Integer, Person>() {{
            put(1, new Person("Willy", "2010-01-01"));
            put(2, new Person("Sally", "2010-01-01"));
            put(3, new Person("Bill", "2010-01-01"));
        }};

        Map<Integer, Person> persons1 = persons.entrySet().stream()
                .filter(p -> p.getKey() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        System.out.println(persons1);

        Stream.of("duck", "duck", "duck", "goose")
                .distinct()
                .forEach(System.out::println);

        Stream.of("monkey", "gorilla", "bonobo")
                .map(String::length)
                .forEach(System.out::print);

        System.out.println("-".repeat(100));

        List<String> zero = List.of();
        var one = List.of("Bonobo");
        var two = List.of("Mama Gorilla", "Baby Gorilla");
        Stream.of(zero, one, two)
                .flatMap(m -> m.stream())
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println("-".repeat(100));

        List.of("Toby", "Anna", "Leroy", "Alex")
                .stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-".repeat(100));

        new Random().ints()
                .limit(10)
                .sorted()
                .forEach(System.out::println);

        Optional<String> minim = List.of("Tob", "An", "Lero", "Alexo")
                .stream()
                .min(String::compareTo);
        System.out.println(minim);

        Comparator<String> sorting = String::compareTo;
        Predicate<String> filtering = Predicate.not(String::isEmpty);

        Stream<String> strs = List.of("Tob", " ", "An", "Lero", "Alexo").stream();
        strs = strs.sorted(sorting);
        strs = strs.filter(filtering);
        strs.map(String::toCharArray)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}
