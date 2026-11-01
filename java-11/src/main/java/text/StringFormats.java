package text;

import java.time.LocalDateTime;

public class StringFormats {

    public static void main(String[] args) {

        assert String.format("%1$tT",
                LocalDateTime.parse("2020-01-01T10:00:00")
        ).equals("10:00:00") : "Expect 10:00:00";

        assert String.format(
                "%1$tT %2$s %3$s",
                LocalDateTime.parse("2020-01-01T10:00:00"),
                "INFO",
                new Exception()
        ).equals("10:00:00 INFO java.lang.Exception");

        assert String.format("%15s", "Hello world")
                .equals("    Hello world");
        assert String.format("%-15s", "Hello world")
                .equals("Hello world    ");
        assert String.format("%.5s", "Hello world")
                .equals("Hello");
        assert String.format("%.5s", "Hello world")
                .equals("Hello");// truncate
    }
}
