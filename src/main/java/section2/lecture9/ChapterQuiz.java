package section2.lecture9;

public class ChapterQuiz {

    public static void main(String[] args) {

        quiz1();
        quiz2();
        quiz3();
        quiz4();
        quiz5();
    }

    public static void quiz1() {
        // What is the output of the following program?
        int a = 2;
        double b = 4.0;
        double c = 10.0;
        double out = c / b + a / (2 * a);

        /**
         * out --> 2.5
         *
         * a / (2 * a) is zero because we’re dividing two integers. Whenever you divide two
         * integers, you don’t worry about anything that comes after the decimal point in the
         * result - you’re always left with a whole number
         */
        System.out.println(out);
    }

    public static void quiz2() {
        // What is the output of the following program?
        String in = "hello";
        String out = in.replaceAll("l", "");
        in = out.toUpperCase();

        /**
         * in --> HEO
         * Answer: HEO
         *
         * First, we got rid of all the s in hello. Then, we converted the string to uppercase
         * and re-assigned the original in variable. Careful: if we hadn’t reassigned the
         * original variable, we would have printed just hello.
         */
        System.out.println(in);
    }

    public static void quiz3() {
        // Given the following program, write code to capitalize the first letter of lowerCaseString
        String lowercaseString = "this is a very long lower case string";

        // MINE
        String out = lowercaseString.substring(0, 1).toUpperCase();
        out = out + lowercaseString.substring(1);
        System.out.println(out);

        // THEIRS
        if (lowercaseString.length() > 0) {
            lowercaseString = Character.toUpperCase(lowercaseString.charAt(0))
                    +
                    lowercaseString.substring(1, lowercaseString.length());
        }
        System.out.println(lowercaseString);
    }

    public static void quiz4() {
        // Which of the following are correct ways of creating arrays in Java?

        // error String[] myArray = new String[] ;
        String[] myArray = {"Hey", "Bye", "Hello"};
        int[] myArray2 = new int[10];
        // error int[10] myArray = new int[] ;
    }

    public static void quiz5() {
        // What is the output of the following program?
        int val = 10;
        switch (val) {
            case 1:
                System.out.println("Orange");
                break;
            case 2:
                System.out.println("Pear");
                break;
            default:
                System.out.println("Apples!");
                break;
        }

        /**
         * --> Apples!
         */
    }
}
