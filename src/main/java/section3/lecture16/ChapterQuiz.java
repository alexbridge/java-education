package section3.lecture16;

import java.util.function.Function;

public class ChapterQuiz {

    public static void main(String[] args) {

        quiz1();
        quiz2();
        quiz3();
        quiz4();
        quiz5();
    }

    /**
     * Write a simple function that returns true if an integer is negative and false if it’s positive.
     */
    public static void quiz1() {
        int a = 2;

        // Lambda
        Function<Integer, Boolean> isNegative = (n) -> n < 0;
        System.out.println(isNegative.apply(a));
    }

    /**
     * Create a class that represents a building. Include variables that keep track of the
     * number of people living in the building, the age of the building, and how much it
     * cost to construct the building. Then construct a building object using a constructor
     * that takes in the age of the building and the number of people living in it
     */
    public static void quiz2() {

    }

    public static void quiz3() {

    }

    public static void quiz4() {

    }

    public static void quiz5() {

    }
}
