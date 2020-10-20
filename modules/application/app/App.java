package app;

import jecl.cli.CliPrinter;

public class App {
    public static void main(String[] args) {
        System.out.printf("\nRunning main method of " + App.class.getName() + "\n\n");

        System.out.println("Type of 10 is " + CliPrinter.TypePrinter.withType(10));

        System.out.printf("\nDone");
    }
}
