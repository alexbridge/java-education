package text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Locale;

public class NumberFormats {
    public static void main(String[] args) {
        withoutLocale();
        withLocale();
        currency();
    }

    public static void withoutLocale() {
        assert new DecimalFormat("#.##").format(12345.678).equals("12345.68");
        assert new DecimalFormat("0.00")
                .format(12345.678).equals("12345.68"); // as is

        assert new DecimalFormat("##,###.##").format(12345.678).equals("12,345.68");

        //assert (new Formatter().format("%010d", 12345)).equals("0000012345");
        System.out.println(new Formatter().format("%010d", 12345));
        //assert new Formatter().format("%-10d", 12345).equals("12345     ");
    }

    public static void withLocale() {
        assert new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.ENGLISH))
                .format(12345.678).equals("12,345.68");
        assert new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.GERMANY))
                .format(12345.678).equals("12.345,68");

        try {
            assert new DecimalFormat("#,###.##",
                    new DecimalFormatSymbols(Locale.ENGLISH))
                    .parse("12,345.68").equals(12345.68);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public static void currency() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        assert currency.format(100.0000).equals("$100.00");
    }
}
