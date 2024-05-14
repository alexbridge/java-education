package archives;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipFile;

public class ZipRead {

    public static void main(String[] args) throws IOException {
        var tempFile = Files.createTempFile("zip", "create");

        System.out.println("Temp file: " + tempFile);

        try {
            Files.write(tempFile, Files.readAllBytes(Path.of("zip-create.zip")));

            try (var zipFile = new ZipFile(tempFile.toFile())) {

                var file0 = zipFile.stream()
                        .filter(zipEntry -> !zipEntry.isDirectory())
                        .filter(zipEntry -> zipEntry.getName().contains("file_0.txt"))
                        .findFirst();


                if (file0.isPresent()) {
                    var fileEntry = file0.get();
                    var name = new File(fileEntry.getName()).getName();

                    System.out.printf("Zip name %s, entry: %s\n", name, file0.get());
                    var bytes = zipFile.getInputStream(file0.get()).readAllBytes();
                    Files.write(Path.of("file_0.txt"), bytes);

                    var content = new String(bytes);

                    System.out.println("Content: %s [%s]".formatted(
                            content, content.length()
                    ));
                }
            }
        } finally {
            Files.delete(tempFile);
        }
    }
}
