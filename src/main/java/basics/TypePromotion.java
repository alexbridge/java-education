package basics;

import jecl.cli.CliPrinter;

public class TypePromotion {

    public static void main(String[] args) {
        double a = 10.0;
        Double b = 10.0;
        float c = (float) a;
        promote(a);
        promote(b);
        promote(c);
        promote(10.0);
        promote(13.0);

        int a2 = 117, b2 = 5;
        int abint = a2 / b2;
        int abm = a2 * b2;
        System.out.println(abint);
        System.out.println(CliPrinter.TypePrinter.withType(a2 / b2));

        System.out.println(CliPrinter.TypePrinter.withType(true && true));
        System.out.println(CliPrinter.TypePrinter.withType(true & (3 > 4)));
        System.out.println(CliPrinter.TypePrinter.withType(true || true));
        System.out.println(CliPrinter.TypePrinter.withType(true | false));
        System.out.println(CliPrinter.TypePrinter.withType(1 & 2));
        System.out.println(Character.isLetter('a'));
    }

    private static void promote(int value) {
        System.out.println("promote int value " + value);
    }
    private static void promoteFloat(float value) {
        System.out.println("promote float value " + value);
    }
    private static void promoteDouble(double value) {
        System.out.println("promote double value " + value);
    }
    private static void promote(Integer value) {
        System.out.println("promote Integer value " + value);
    }
    private static void promote(float value) {
        System.out.println("promote float value " + value);
    }
    private static void promote(Object value) {
        System.out.println("promote Object value " + value);
    }
}
