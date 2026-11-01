package edu.java14;

/**
 * Demonstrates the Java features used in this training example:
 *
 * <ul>
 *   <li>Switch Expressions (Java 14)</li>
 *   <li>Text Blocks (Java 15)</li>
 *   <li>Records (Java 16)</li>
 *   <li>Pattern Matching for {@code instanceof} (Java 16)</li>
 * </ul>
 *
 * <p>The examples below show practical usage of these language features in a
 * compact Java program.</p>
 */
public class SwitchExpression {

    public static void main(String[] args) {
        System.out.println("Day 5 => " + java14PrimitiveMatch(5));
        System.out.println("Day 7 => " + java14PrimitiveMatch(7));
        System.out.println("Score 92 => " + java14PrimitiveMatchAndDefault(92));
        System.out.println("Text block demo:");
        System.out.println(reportText("Java 25"));

        Point point = new Point(3, 4);
        System.out.println("Record demo: " + point);

        java16InstanceOf("hello");
        java16InstanceOf(42);
        java16InstanceOf(3.14d);
    }

    private static String java14PrimitiveMatch(int day) {
        return switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> "Unknown day";
        };
    }

    private static String java14PrimitiveMatchAndDefault(int score) {
        return switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }

    private static String reportText(String projectName) {
        return """
               Project: %s
               Status: Ready for Java 25 practice
               Topics: switch expressions, text blocks, records, pattern matching
               """.formatted(projectName);
    }

    private static void java16InstanceOf(Object value) {
        if (value instanceof String text) {
            System.out.println("String: " + text);
        } else if (value instanceof Integer number) {
            System.out.println("Integer: " + number);
        } else {
            System.out.println("Other: " + value);
        }
    }

    record Point(int x, int y) {
    }
}
