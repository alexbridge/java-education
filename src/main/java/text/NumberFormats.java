package text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Locale;

public class NumberFormats {
    public static void main(String[] args) throws ParseException {
        withoutLocale();
        withLocale();
        currency();
        attendeesPerYear();
        parse();
        customFormat();
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
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        assert currency.format(100.0000).equals("$100.00");

        currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println(currency.format(28));
    }

    public static void attendeesPerYear() {
        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;
        System.out.printf("US locale: %s\n", NumberFormat.getInstance(Locale.US).format(attendeesPerMonth));
        System.out.printf("DE locale: %s\n", NumberFormat.getInstance(Locale.GERMANY).format(attendeesPerMonth));
        System.out.printf("CA locale: %s\n", NumberFormat.getInstance(Locale.CANADA_FRENCH).format(attendeesPerMonth));
    }

    public static void parse() throws ParseException {
        var en = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(en.parse("$40.45")); // 40.45
    }

    public static void customFormat() {
        double d = 1234.467;
        NumberFormat f1 = new DecimalFormat("###,###,###.0");
        System.out.println(f1.format(d)); // 1,234.5
        NumberFormat f2 = new DecimalFormat("000,000,000.00000");
        System.out.println(f2.format(d)); // 000,001,234.46700
        NumberFormat f3 = new DecimalFormat("$#,###,###.##");
        System.out.println(f3.format(d)); // $1,234.47
    }
}
