package nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static jecl.cli.CliPrinter.withMemo;

public class FilesNIO {
    public static void main(String[] args) {
        withMemo("FilesIO::pathResolve", FilesNIO::pathResolve);
        withMemo("FilesIO::buffered", FilesNIO::buffered);
        withMemo("FilesIO::streams", FilesNIO::streams);
    }

    public static void pathResolve() {
        try {
            var path1 = Path.of("/bats/night", "..").resolve(Paths.get("./sleep.txt")).normalize();
            Path path2 = new File("tmp/animals.txt").toPath().toRealPath();
            System.out.print(Files.isSameFile(path1, path2));
            System.out.print(" " + path1.equals(path2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void buffered() {
        var path = Path.of("tmp/animals.txt");

        try (var reader = Files.newBufferedReader(path)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("1");
        }

        var list = new ArrayList<String>();
        list.add("Lion");
        list.add("Monkey");

        try (var writer = Files.newBufferedWriter(
                path,
                StandardOpenOption.APPEND
        )) {
            for (var line : list) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<String> lines;
        try {
            lines = Files.readAllLines(path);
            lines.forEach(System.out::println);
            System.out.println(Files.size(path));

            FileTime fileTime = Files.getLastModifiedTime(path);
            System.out.println(fileTime);

            BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println("Is a directory? " + data.isDirectory());
            System.out.println("Is a regular file? " + data.isRegularFile());
            System.out.println("Is a symbolic link? " + data.isSymbolicLink());
            System.out.println("Size (in bytes): " + data.size());
            System.out.println("Last modified: " + data.lastModifiedTime());

            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            var lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);
            view.setTimes(lastModifiedTime, null, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void streams() {
        var path = Path.of("tmp");
        try (Stream<Path> s = Files.list(path)) {
            s.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Walking...");

        try (var s = Files.walk(path)) {
            s.parallel().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
