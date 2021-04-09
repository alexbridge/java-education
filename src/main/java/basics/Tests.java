package basics;

import java.util.Set;
import java.util.stream.Collectors;

public class Tests {

    static class A {

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
    }

    public static void main(String[] args) {
        final int i = 0;
        //System.out.print(4 / i);

        Integer.parseInt("0");

        Set<String> islandNations = Set.of("Japan", "Australia", "Cyprus", "Cuba", "Taiwan");
        islandNations = islandNations.stream()
                .map(s -> {
                    if (s.equals("Australia"))
                        return "New Zealand";
                    else
                        return s;
                })
                .map(n -> n.substring(0, 1))
                .peek(System.out::println)
                .collect(Collectors.toSet());
        System.out.println(islandNations);
        for (String s : islandNations) {
            System.out.print(s);
        }

        A sc = new C();
        System.out.println(((B) sc.fly()).getClass().getName() );
    }
}