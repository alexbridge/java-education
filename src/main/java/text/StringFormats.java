package text;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class StringFormats {

    public static void main(String[] args) {

        assertThat(String.format("%1$tT",
                LocalDateTime.parse("2020-01-01T10:00:00")
        )).isEqualTo("10:00:00");

        assertThat(String.format(
                "%1$tT %2$s %3$s",
                LocalDateTime.parse("2020-01-01T10:00:00"),
                "INFO",
                new Exception()
        )).isEqualTo("10:00:00 INFO java.lang.Exception");

        assertThat(String.format("%15s", "Hello world"))
                .isEqualTo("    Hello world");
        assertThat(String.format("%-15s", "Hello world"))
                .isEqualTo("Hello world    ");
        assertThat(String.format("%.5s", "Hello world"))
                .isEqualTo("Hello");
        assertThat(String.format("%.5s", "Hello world"))
                .isEqualTo("Hello");// truncate
    }
}
