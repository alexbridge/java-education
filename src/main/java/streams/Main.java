package streams;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    }
}
