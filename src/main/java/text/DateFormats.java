package text;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.format.FormatStyle.SHORT;

public class DateFormats {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        System.out.println(formatter.format(LocalDateTime.now()));
        Locale.setDefault(new Locale("en", "US"));

        var italy = new Locale("it", "IT");
        var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 15, 12, 34);
        print(DateTimeFormatter.ofLocalizedDate(SHORT), dt, italy);
        print(DateTimeFormatter.ofLocalizedTime(SHORT), dt, italy);
        print(DateTimeFormatter.ofLocalizedDateTime(SHORT, SHORT), dt, italy);
    }

    public static void print(DateTimeFormatter dtf, LocalDateTime dateTime, Locale locale) {
        System.out.println(dtf.format(dateTime) + ", " + dtf.withLocale(locale).format(dateTime));
    }
}
