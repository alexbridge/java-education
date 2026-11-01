package nio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pathess {
    public static void main(String[] args) throws URISyntaxException, IOException {
        File file = new File("husky.png");
        Path path = file.toPath();
        File backToFile = path.toFile();

        System.out.println(file);
        System.out.println(path);
        System.out.println(backToFile);

        Path pathFromPaths = Paths.get("files/file.dat");
        System.out.println(pathFromPaths);
        System.out.println(pathFromPaths.getName(0));
        System.out.println(pathFromPaths.getName(1));
        System.out.println(pathFromPaths.getNameCount());
        System.out.println(pathFromPaths.toString());
        System.out.println(pathFromPaths.subpath(1, 2));

        System.out.println("-".repeat(50));
        System.out.println(System.getProperty("user.dir"));

        System.out.println("-".repeat(50));
        System.out.println(pathFromPaths.isAbsolute());
        System.out.println(pathFromPaths.toAbsolutePath());
        System.out.println(pathFromPaths.getFileName());
        System.out.println(pathFromPaths.getParent());
        System.out.println(pathFromPaths.getRoot());

        Path file2 = pathFromPaths.getParent().resolve("file2.dat");
        System.out.println(file2);
        System.out.println(file2.relativize(pathFromPaths));

        System.out.println("-".repeat(50));
        var p1 = Paths.get("/pony/../weather.txt");
        var p2 = Paths.get("/weather.txt");
        System.out.println(p1.equals(p2)); // false
        System.out.println(p1.normalize().equals(p2.normalize())); // true

        System.out.println("-".repeat(50));
        System.out.println(Paths.get(".").toRealPath());
    }
}
