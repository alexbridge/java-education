package section2.lecture8;

import java.util.Arrays;

public class CreatingArraysForLoops {

    public static void main(String[] args) {

        int[] myAray = new int[4]; // [20], [30], [2], [4], ...
        myAray[0] = 20;
        myAray[1] = 30;
        myAray[2] = 2;
        myAray[3] = 4;

        int sum = 0;
        for (int i = 0; i < myAray.length; i++) {
            sum += myAray[i]; // sum = sum + myAray[i]
        }

        Arrays.sort(myAray);
        for (int i = 0; i < myAray.length; i++) {
            System.out.println(myAray[i]);
        }

        String[] names = {
                "Joey", "Willy", "Sam", "Frodo", "Merry"
        };
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }

        System.out.println("----------");

        for (String name : names) {
            System.out.println(name);
        }

    }
}
