package time;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Times {

    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        System.out.println(formatter.format(ZonedDateTime.now()));


        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2021-05-17T10:00:00.000000+00:00");
        System.out.println(zonedDateTime);
        LocalDateTime localDateTime = LocalDateTime.parse("2021-05-17T10:00:00.000000");
        System.out.println(localDateTime);
    }
}
