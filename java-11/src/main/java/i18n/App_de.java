package i18n;

import java.util.ListResourceBundle;

public class App_de extends ListResourceBundle {
    private Object[][] contents = {
            {"price", new Double(10.00)},
            {"currency", "EUR"},
            {"cities", new String[] { "Warsaw", "Cracow" }}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
