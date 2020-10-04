package time;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import static jecl.cli.CliPrinter.withMemo;

public class Temporal {

    public static void main(String[] args) {
        withMemo("All Sundays of January", Temporal::allSundays);
        withMemo("Every 6 hours during 24 hours", Temporal::every6Hours);
    }

    public static void allSundays() {
        Month month = Month.JANUARY;

        LocalDate localdate = Year.now()
                .atMonth(month)
                .atDay(1)
                .with(
                        TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)
                );

        Month mi = localdate.getMonth();
        while (mi == month) {
            System.out.printf("%s%n", localdate);
            localdate = localdate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            mi = localdate.getMonth();
        }
    }

    public static void every6Hours() {
        IntStream.iterate(0, h -> h + 6)
                .limit(24 / 6)
                .mapToObj(h -> LocalTime.of(h, 0, 0))
                .forEach(time -> System.out.printf("%s%n", time));
    }
}
