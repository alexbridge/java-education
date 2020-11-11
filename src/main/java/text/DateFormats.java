package text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormats {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        System.out.println(formatter.format(LocalDateTime.now()));
    }
}
