package lang;

import static jecl.cli.CliPrinter.TypePrinter.withType;
import static jecl.cli.CliPrinter.withMemo;

public class Strings {

    public static void main(java.lang.String[] args) {
        withMemo("String::stringInitializer", Strings::stringInitializer);
        withMemo("String::stringsArrayInitializer", Strings::stringsArrayInitializer);
        withMemo("String::stringBuilder", Strings::stringBuilder);
        withMemo("String::stringReplace", Strings::stringReplace);

        String tea = "Tea";
        String b = "Tea";
        System.out.println(tea == b);

        String interned = new String("Tea");
        System.out.println(tea == interned);
        System.out.println(tea == interned.intern());
    }

    public static void stringInitializer() {
        /**
         * String empty reference
         * A reference to an empty string "" points to an object in the heap - so you can call methods on it.
         * But a reference pointing to null has no object to point in the heap and thus you'll get a NullPointerException.
         */
        java.lang.String str = ""; // String data type with reference to object in Heap
        java.lang.String str2 = null; // String data type with no reference
        System.out.println(str.length());
        System.out.println(str2); // str2.length() -> NullPointerException
    }

    public static void stringsArrayInitializer() {
        java.lang.String[] strings = {"str1", "str2"};
        java.lang.String str = "str1";

        withMemo("strings[0].equals(str)", () -> strings[0].equals(str));
        withMemo("strings[0] == str", () -> strings[0].equals(str));
    }

    public static void stringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(withType(true))
                .append(withType((byte) 123))
                .append(withType((char) 123))
                .append(withType((short) 123))
                .append(withType(123))
                .append(withType(123L))
                .append(withType(123.00f))
                .append(withType(123.00))
                .append(withType('A'))
                .append(withType("String"))
                .appendCodePoint(127);

        System.out.println("String result: " + stringBuilder);
        System.out.println("String result reverse: " + stringBuilder.reverse());
    }

    public static void stringReplace() {
        boolean flag1 = "Java" == "Java".replace( 'J' , 'J' );
        boolean flag2 = "Java" == "Java".replace( "J" , "J" );
        System. out .println(flag1 && flag2);
    }
}
