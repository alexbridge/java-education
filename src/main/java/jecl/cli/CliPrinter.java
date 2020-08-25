package jecl.cli;

import java.util.function.BooleanSupplier;

public class CliPrinter {
    public static void withMemo(String memo, Runnable method) {
        System.out.println(" ----- " + memo + " ----- ");
        method.run();
    }

    public static void withMemo(String memo, BooleanSupplier method) {
        System.out.println(" ----- " + memo + " ----- ");
        System.out.println("\t" + method.getAsBoolean());
    }
}
