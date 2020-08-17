package section2.lecture5;

public class Strings {

    public static void main(String[] args) {

        char myChar = 'a';
        String myString = "Nansy Reagan"; // A string is a sequence of chars. A sequence of chars can be represented via an array
        // [N], [a], [n] ... [n]

        String weirdInitialisation = new String("Nancy Reagan");
        //boolean areStringsSame = myString.equals(weirdInitialisation);

        int a = 2;
        int b = 3;
        if (a == b) {
            System.out.println("a same as b");
        }

        if (myString == weirdInitialisation) // Different memory blocks [NanceReagan] <-- myString, [Nancy Reagan] <-- weirdInitialisation
        {
            System.out.println("Strings are the same!");
        } else {
            System.out.println("Strings aren`t same!");
        }

        if (myString.equals(weirdInitialisation)) // Same content of memory blocks [NanceReagan] <-- myString, [Nancy Reagan] <-- weirdInitialisation
        {
            System.out.println("Strings are the same!");
        } else {
            System.out.println("Strings aren`t same!");
        }

        // Strings are immutable
        String nonImmutableString = "Hello"; // [Hello] <-- nonImmutableString
        nonImmutableString = nonImmutableString.replaceAll("l", "o"); // [Hello] --> new block of memory --> [Heooo]

        System.out.println(nonImmutableString);

    }
}
