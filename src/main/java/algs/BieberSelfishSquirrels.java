package algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BieberSelfishSquirrels {

    public static void main(String[] args) {
        List<int[]> days = new ArrayList<>();

        int[] day = {3, 3, 1, 3, 3};
        days.add(day);

        while (canHollowMigrate(day)) {
            day = moveHollow(day);
            days.add(day);
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
