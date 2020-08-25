package lang;

import static jecl.cli.CliPrinter.withMemo;

public class Strings {

    public static void main(java.lang.String[] args) {
        withMemo("String::stringInitializer", Strings::stringInitializer);
        withMemo("String::stringsArrayInitializer", Strings::stringsArrayInitializer);
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
}
