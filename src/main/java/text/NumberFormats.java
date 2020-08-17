package text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberFormats {
    public static void main(String[] args) {
        withoutLocale();
        withLocale();
    }

    public static void withoutLocale() {
        assertThat(new DecimalFormat("#.##")
                .format(12345.678))
                .isEqualTo("12345.68"); // rounding
        assertThat(new DecimalFormat("0.00")
                .format(12345.678))
                .isEqualTo("12345.68"); // as is
        assertThat(new DecimalFormat("##,###.##")
                .format(12345.678))
                .isEqualTo("12,345.68"); // groups and rounding
        assertThat(new Formatter()
                .format("%010d", 12345).toString())
                .isEqualTo("0000012345");
        assertThat(new Formatter()
                .format("%-10d", 12345).toString())
                .isEqualTo("12345     ");
    }

    public static void withLocale() {
        assertThat(new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.ENGLISH))
                .format(12345.678))
                .isEqualTo("12,345.68");
        assertThat(new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.GERMANY))
                .format(12345.678))
                .isEqualTo("12.345,68");

        try {
            assertThat(new DecimalFormat("#,###.##",
                    new DecimalFormatSymbols(Locale.ENGLISH))
                    .parse("12,345.68"))
                    .isEqualTo(12345.68);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
}
