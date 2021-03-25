package nio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Serialization {
    static Map<String, String> map = new HashMap<>() {{
        put("1", "2");
    }};

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Path dest = Path.of("tmp/objects");

        try (ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(
                        dest,
                        StandardOpenOption.CREATE
                )
        )) {
            out.writeObject(map);
            out.writeObject(Instant.now());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(
                Files.newInputStream(
                        dest,
                        StandardOpenOption.DELETE_ON_CLOSE
                )
        )) {
            map = (Map<String, String>) in.readObject();
            System.out.println(map);
            Instant inst = (Instant) in.readObject();
            System.out.println(inst);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
