package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class PersistProperties {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();

        p.setProperty("name", "John");
        p.setProperty("description", "Doe");

        FileWriter fw = new FileWriter(
                Path.of("", "tmp", "props.txt").toString()
        );
        p.store(fw, "Admin");
        fw.close();

        p = new Properties();
        FileReader fr = new FileReader(
                Path.of("", "tmp", "props.txt").toString()
        );
        p.load(fr);
        fr.close();
        System.out.println(p);
    }
}
