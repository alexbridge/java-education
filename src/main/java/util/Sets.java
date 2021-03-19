package util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sets {

    public static void main(String[] args) {
        Set<?> set = Set.of("lion", "tiger", "bear");
        var s = Set.copyOf(set);
        s.forEach(System.out::println);

        List<String> list = List.of("lion", "tiger", "lion");
        Set<?> fromList = new HashSet<>(list);
        System.out.println(fromList);
    }
}
