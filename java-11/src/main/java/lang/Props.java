package lang;

import java.util.Properties;

public class Props {
    public static void main(String[] args) {
        var props = new Properties();
        props.setProperty("tigerAge", "4");
        props.setProperty("lionAge", "5");
        System.out.println(props.getProperty("tigerAge"));
        System.out.println(props.getProperty("lionAge"));
    }
}
