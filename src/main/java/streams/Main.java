package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

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

    }
}
