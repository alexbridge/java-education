package lang;

import lang.exception.StackTraceStub;

import static jecl.cli.CliPrinter.withMemo;

public class ExceptionHandling {
    public static void main(String[] args) {
        withMemo("ExceptionHandling::stackTrace", ExceptionHandling::stackTrace);
    }

    public static void stackTrace() {
        StackTraceStub trace = new StackTraceStub();
        try {
            trace.method1();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
