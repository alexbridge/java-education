package streams;

import java.util.*;
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

        collectStudents();
    }

    public static void collectStudents()
    {
        var ls = Arrays.asList(
                new Student("S1", Student.Grade.A),
                new Student("S2", Student.Grade.A),
                new Student("S3", Student.Grade.B),
                new Student("S4", Student.Grade.C),
                new Student("S5", Student.Grade.F)
        );

        var group = ls.stream()
                .filter(student -> student.getGrade() != Student.Grade.F)
                .collect(java.util.stream.Collectors.groupingBy(
                        Student::getGrade,
                        java.util.stream.Collectors.mapping(
                                Student::getName,
                                java.util.stream.Collectors.toList()
                        )));
        System.out.println(group);

        var collection = Arrays.asList(1,2,3);
        var list1 = List.of(collection);
        var list2 = List.copyOf(collection);
        System.out.println(collection);
        System.out.println(list1);
        System.out.println(list2);
    }

    static class Student {
        public static enum Grade {A, B, C, D, F}
        private String name;
        private Grade grade;
        public Student(String name, Grade grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public Grade getGrade() {
            return grade;
        }

        public String toString() {
            return name + ":" + grade;
        }
    }
}
