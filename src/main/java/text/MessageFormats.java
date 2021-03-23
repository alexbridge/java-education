package text;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Arrays;

public class MessageFormats {

    public static void main(String[] args) {

        MessageFormat message = new MessageFormat("{0}:'{TEXT}'{1}");

        Object[] parts;
        try {
            parts = message.parse("Foo:{TEXT}bar");

            System.out.println(Arrays.toString(parts));

            System.out.println(message.format(parts));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
