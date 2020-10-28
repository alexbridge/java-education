package hackerrank.algs;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BieberSelfishSquirrels {
    static {
        // simulate stdin in development purposes
        if (System.getenv("DEVELOPMENT") != null) {
            String stdin = "3 3 1 3 3";
            System.setIn(new ByteArrayInputStream(stdin.getBytes()));
        }
    }

    public static void main(String[] args) {
        System.out.println("Please enter number of squirrels in 5 hollows for first day (space separated)");
        int[] day = new int[5];

        Scanner scanner = new Scanner(System.in);

        Arrays.setAll(day, el -> scanner.nextInt());

        System.out.printf("Checking days for %s", Arrays.toString(day));

        List<int[]> days = new ArrayList<>();
        days.add(day);

        int[] nextDay;
        while (canHollowMigrate(day)) {
            System.out.println("Can migrate");
            nextDay = moveHollow(day);
            System.out.printf("Day %d\n\t%s\n\t%s\n", days.size() + 1, Arrays.toString(day), Arrays.toString(nextDay));
            if (Arrays.compare(day, nextDay) == 0) {
                System.out.println("Migration failed");
                System.exit(1);
            }
            days.add(nextDay);
            day = nextDay;

            if (days.size() > 10) {
                System.out.println("Migration failed due to max 50 days limited or recursion");
                System.exit(1);
            }
        }

        System.out.printf("All squirrels are in same hollow after %d days\n", days.size() - 1);
        System.out.printf("All squirrels are in same hollow on exact %d day\n", days.size());
        days.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    private static boolean canHollowMigrate(int[] day) {
        // Can migrate if numbers are differ
        boolean same = Arrays.stream(day).allMatch(s -> s == day[0]);
        if (same) {
            return false;
        }

        // Can migrate if not in same hollow
        long zeroHollows = Arrays.stream(day)
                .filter(v -> v == 0)
                .count();

        return zeroHollows < day.length - 1;
    }

    private static int[] moveHollow(int[] day) {
        int[] nextDay = new int[day.length];

        for (int i = 0, s = day.length; i < s; i++) {
            // Define prev and next hollow
            int prev = i == 0
                    ? 0
                    : i - 1;
            int next = i == s - 1
                    ? i
                    : i + 1;

            if (day[i] > day[prev]) {
                // Move to prev hollow (preferably)
                nextDay[prev] += day[i];
            } else if (day[i] > day[next]) {
                // Move to next hollow
                nextDay[next] += day[i];
            } else {
                // Stay at own hollow
                nextDay[i] += day[i];
            }
        }
        return nextDay;
    }
}
