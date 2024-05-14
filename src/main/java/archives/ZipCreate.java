package archives;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreate {

    public static void main(String[] args) throws IOException {
        try (
                var bout = new ByteArrayOutputStream();
                var zout = new ZipOutputStream(bout)) {

            for (int d = 0; d < 10; d++) {
                for (int e = 0; e < 10; e++) {
                    var ze = new ZipEntry("dir_%s/file_%s.txt".formatted(d, e));
                    zout.putNextEntry(ze);
                    zout.write("dir_%s/file_%s.txt".formatted(d, e).getBytes());
                }
            }

            zout.closeEntry();
            zout.close();

            Files.write(Path.of("zip-create.zip"), bout.toByteArray());
        }
    }
}
