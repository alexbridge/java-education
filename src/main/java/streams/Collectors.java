package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Collectors {

    public static void main(String[] args) {

        List<String> list2 = Arrays.asList("adf", "bcd", "abc", "hgr", "jyt", "edr", "biu");

        // Sequential stream without combiner
        StringBuilder stringBuilder = list2
                .stream()
                .map(s -> s.concat(" "))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        (res1, res2) -> {
                            System.out.printf("In combiner not called: res1=%s, res2=%s\n", res1, res2);
                            res1.append(res2);
                        }
                );
        System.out.println("collected to: " + stringBuilder);

        // Parallel stream with combiner
        stringBuilder = list2
                .parallelStream()
                .map(s -> s.concat(" "))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        (res1, res2) -> {
                            System.out.printf("In combiner: res1=%s, res2=%s\n", res1, res2);
                            res1.append(res2);
                        }
                );
        System.out.println("collected to: " + stringBuilder);

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map = ohMy.collect(
                java.util.stream.Collectors.toMap(
                        String::length,
                        k -> k,
                        (s1, s2) -> s1.concat("," + s2),
                        TreeMap::new
                )
        );
        System.out.println(map); // {5=lions,bears, 6=tigers}

        TreeMap<Integer, List<String>> groupedMap = Stream.of("lions", "tigers", "bears")
                .collect(
                    java.util.stream.Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        java.util.stream.Collectors.toList()
                   )
                );
        System.out.println(groupedMap);

        var collected = LongStream.of(6L, 8L, 10L)
                .mapToInt(x -> (int) x)
                .boxed()
                .collect(java.util.stream.Collectors.groupingBy(x -> x));
        System.out.println(collected);

        var avg = collected.keySet().stream().collect(java.util.stream.Collectors.averagingInt(x -> x));
        System.out.println(avg);
    }
}
