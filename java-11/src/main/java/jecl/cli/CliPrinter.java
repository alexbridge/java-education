package jecl.cli;

import java.util.function.BooleanSupplier;

public class CliPrinter {
    public static void withMemo(String memo, Runnable method) {
        String pr = String.format("----- %s -----", memo);
        System.out.println(pr);
        method.run();
        System.out.println("\n");
        System.out.println("-".repeat(pr.length()));
    }

    public static void withMemo(String memo, BooleanSupplier method) {
        System.out.println(" ----- " + memo + " ----- ");
        System.out.println("\t" + method.getAsBoolean());
    }

    static public class TypePrinter {
        public static String withType(boolean t) {
            return "[boolean]" + t;
        }
        public static String withType(byte t) {
            return "[byte]" + t;
        }
        public static String withType(char t) {
            return "[char]" + t;
        }
        public static String withType(short t) {
            return "[short]" + t;
        }
        public static String withType(int t) {
            return "[int]" + t;
        }
        public static String withType(long t) {
            return "[long]" + t;
        }
        public static String withType(float t) {
            return "[float]" + t;
        }
        public static String withType(double t) {
            return "[double]" + t;
        }
        public static String withType(String t) {
            return "[string]" + t;
        }
        public static String withType(Object t) {
            if (t == null) {
                return "[NULL]";
            }
            return "[" + t.getClass().getSimpleName() +  "]" + t.toString();
        }
    }
}
