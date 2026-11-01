package archives;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipFile;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ZipRead {

    public static void main(String[] args) throws IOException {
        var memoryStart = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        var tempFile = Files.createTempFile("zip", "create");

        System.out.println("Temp file: " + tempFile);

        try {
            //Files.write(tempFile, Files.readAllBytes(Path.of("zip-create.zip")));
            Files.copy(Path.of("zip-create.zip"), tempFile, REPLACE_EXISTING);

            try (var zipFile = new ZipFile(tempFile.toFile())) {

                var file0 = zipFile.stream()
                        .filter(zipEntry -> !zipEntry.isDirectory())
                        .filter(zipEntry -> zipEntry.getName().contains("file_0.txt"))
                        .findFirst();


                if (file0.isPresent()) {
                    var fileEntry = file0.get();
                    var filePath = Path.of(fileEntry.getName());
                    var fileNamePath = filePath.getFileName();

                    System.out.printf("Zip name %s, entry: %s\n", fileNamePath, fileEntry);

                    Files.copy(zipFile.getInputStream(fileEntry), fileNamePath, REPLACE_EXISTING);

                    var bytes = Files.readAllBytes(fileNamePath);
                    var content = new String(bytes);

                    System.out.printf(
                            "%s: %s [%s]\n", fileNamePath, Files.probeContentType(fileNamePath), content.length()
                    );
                    System.out.printf(
                            "Content: %s", content
                    );
                }
            }
        } finally {
            Files.delete(tempFile);
        }

        var memoryEnd = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.printf("Memory diff: %s%n", memoryEnd - memoryStart);
    }
}
