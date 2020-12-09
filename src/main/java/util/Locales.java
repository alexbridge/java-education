package util;

import java.util.Locale;

public class Locales {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);

        locale = Locale.GERMANY;
        System.out.println(locale);

        System.out.println(new Locale("hi", "IN"));

        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("DE")
                .build();

        System.out.println(l1);

        Locale.setDefault(new Locale("hi", "IN"));
        System.out.println(Locale.getDefault());
    }
}
