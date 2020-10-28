package hackerrank.strings;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pangram {

    static {
        // simulate stdin in development purposes
        if(System.getenv("DEVELOPMENT") != null) {
            String stdin = "The quick brown fox jumps over a lazy dog.";
            System.setIn(new ByteArrayInputStream(stdin.getBytes()));
        }
    }

    public static void main(String[] args) {

        System.out.println("Please enter a string to test for pangram: ");

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        System.out.printf("Checking if [%s] is pangram ... \n", line);

        Map<Integer, Integer> alphabet = alphabet();
        char[] chars = line.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {

            try {
                int found = alphabet.get((int) chars[i]);
                alphabet.remove((int) chars[i]);
            } catch (NullPointerException e) {
                //e.printStackTrace();
            }
        }

        if(alphabet.size() > 0) {
            System.out.println("not pangram");
        } else {
            System.out.println("pangram");
        }
    }

    private static Map<Integer, Integer> alphabet () {
        return IntStream.rangeClosed('A', 'Z')
                .mapToObj(c -> c)
                .collect(Collectors.toMap(c -> c, c -> c));
    }
}