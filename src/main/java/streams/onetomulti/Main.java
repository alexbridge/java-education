package streams.onetomulti;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Event> events = Arrays.asList(
            new Event("event1", "scope1"),
            new Event("event2", "scope1"),
            new Event("event1", "scope2"),
            new Event("event2", "scope2")
        );

        Map<String, String> groupped = events.stream()
                .collect(Collectors.groupingBy(
                        Event::getScope, Collectors.mapping(e -> e.getName(), Collectors.joining(""))
                ));

        System.out.println(groupped);

    }
}
