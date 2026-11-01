package lang;

import lang.exception.StackTraceStub;

import static jecl.cli.CliPrinter.withMemo;

public class ExceptionHandling {
    public static void main(String[] args) {
        try {
            withMemo("ExceptionHandling::stackTrace", ExceptionHandling::stackTrace);
            withMemo("ExceptionHandling::runtimeAndException", ExceptionHandling::runtimeAndException);
            withMemo("Divide by zero", () -> {
                // Runtime: Exception in thread "main" java.lang.ArithmeticException
                final int dividend = 5;
                final int divisor = 0;
                float number = dividend / divisor;
            });
        } finally {
            System.out.println("End of ExceptionHandling");
        }
    }

    public static void stackTrace() {
        StackTraceStub trace = new StackTraceStub();
        try {
            trace.method1();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    public static void runtimeAndException() {
        try {
            badMethod();
            System.out.println("After call badMethod");
        } catch (java.lang.Exception e) {
            System.out.println("java.lang.Exception is caught");
        } finally {
            System.out.println("java.lang.Exception finally is printed");
        }
    }

    public static void badMethod() {
        throw new RuntimeException();
    }

    static class Danger extends RuntimeException {
        public Danger(String message) {
            super();
        }

        public Danger(int value) {
            super((String) null);
        }
    }
}


