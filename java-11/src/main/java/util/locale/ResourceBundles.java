package util.locale;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundles {
    private final static Locale locale = Locale.US;

    public static void main(String[] args) {
        var rb = ResourceBundle.getBundle("i18n.App", locale);
        System.out.println(rb.getString("hello"));

        rb = ResourceBundle.getBundle("i18n.App", new Locale("de"));
        System.out.println(rb.getObject("currency"));
        System.out.println(rb.getObject("price"));
        System.out.println(Arrays.toString(rb.getStringArray("cities")));
    }
}
