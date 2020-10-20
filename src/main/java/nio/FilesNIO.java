package nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jecl.cli.CliPrinter.withMemo;

public class FilesNIO {
    public static void main(String[] args) {
        withMemo("FilesIO::pathResolve", FilesNIO::pathResolve);
    }

    public static void pathResolve() {
        try {
            var path1 = Path.of("/bats/night", "..").resolve(Paths.get("./sleep.txt")).normalize();
            Path path2 = new File("../sleep.txt").toPath().toRealPath();
            System.out.print(Files.isSameFile(path1, path2));
            System.out.print(" " + path1.equals(path2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
