package nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

public class FilesCopyMove {
    public static void main(String[] args) throws Exception {
        Path source = Path.of("tmp", "car");
        Path dest = Path.of("tmp", "car2");
        Path arch = Path.of("tmp", "arch");

        if (!Files.exists(dest)) {
            Files.createDirectories(dest);
        }

        if (!Files.exists(arch)) {
            Files.createDirectories(arch);
        }

        Files.list(source).forEach(file -> {
            try {
                Path p = dest.resolve(file);
                if (!Files.exists(p)) {
                    Files.createDirectories(p);
                }
                Files.copy(file, p,
                        StandardCopyOption.COPY_ATTRIBUTES,
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println(dest.toString());
        System.out.println(arch.toString());

        Files.move(dest, arch, StandardCopyOption.REPLACE_EXISTING);

        Files.walk(arch)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
