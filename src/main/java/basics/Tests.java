package basics;

import jecl.cli.CliPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tests {

    /*static class A {

        A() {
            logger.append("A");
            System.out.println(logger);
        }

        private StringBuilder logger = new StringBuilder();
        protected A fly() throws Exception {
            return new A();
        }
    }

    static class B extends A {
        protected B fly() {
            return new B();
        }
    }

    static class C extends B {
        public C fly() {
            return new C();
        }
    }*/

    static class Student {
        public static enum Grade {A, B, C, D, F}
        private String name;
        private Grade grade;
        public Student(String name, Grade grade) {
            this.name = name;
            this.grade = grade;
        }
        public String toString() {
            return name + ":" + grade;
        }

        public Grade getGrade() {
            return grade;
        }

        public String getName() {
            return name;
        }

    }

    public static void main(String[] args) {
        var ls = Arrays.asList(new Student("S1", Student.Grade.A),
                new Student("S2", Student.Grade.A),
                new Student("S3", Student.Grade.B),
                new Student("S4", Student.Grade.C),
                new Student("S5", Student.Grade.F));

        var group = ls.stream()
                .filter(student -> student.getGrade() != Student.Grade.F)
                .collect(Collectors.groupingBy(Student::getGrade,
                        Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(group);

        var collection = new ArrayList<>();
        collection.add(1);
        var list1 = List.of(collection);
        collection.add(2);
        var list2 = List.copyOf(collection);
        System.out.println(list1);
        System.out.println(list2);

        X x = new Y();
        Y y = new Y();
        var list = new ArrayList<>();
        list.add(1);

        double x3 = 39.21;
        float y3 = 2.1f; // f is important

        System.out.println(
                CliPrinter.TypePrinter.withType(x3 + y3)
        );
    }
}
class X {
    public List<Integer> getCollection() {
        return new ArrayList<Integer>();
    }
    public void print(List<Integer> lst) {
        System.out.println("X list " + lst);
    }
}

class Y extends X {
    /*public List<? super Integer> getCollection() {
        return new ArrayList<Integer>();
    }*/
    /*public List<? extends Number> getCollection() {
        return new ArrayList<Integer>();
    }*/
    public ArrayList<Integer> getCollection() {
        return new ArrayList<Integer>();
    }

    public void print(List<Integer> lst) {
        System.out.println("Y list " + lst);
    }
}

interface Bird {
    default String fly() {
        return "fly";
    }
}

interface Parrot extends Bird {
    public String fly();
}

class RainbowParrot implements Parrot {
    public static void main(String[] args) {
        Bird b = new RainbowParrot();
        System.out.println(b.fly());
    }

    @Override
    public String fly() {
        return null;
    }
}
