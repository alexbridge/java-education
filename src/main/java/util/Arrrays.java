package util;

import java.util.Arrays;

public class Arrrays {

    public static void main(String[] args) {
        String[] s1 = {"Camel", "Peacock", "Llama"};
        String[] s2 = {"Camel", "Llama", "Peacock"};
        String[] s3 = {"Camel"};
        String[] s4 = {"Camel", null};

        System.out.println(Arrays.compare(s1, s2));
        System.out.println(Arrays.mismatch(s1, s2));
        System.out.println(Arrays.compare(s3, s4));
        System.out.println(Arrays.mismatch(s3, s4));
        System.out.println(Arrays.compare(s4, s4));
        System.out.println(Arrays.mismatch(s4, s4));
    }
}
