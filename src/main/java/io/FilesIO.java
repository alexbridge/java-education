package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static jecl.cli.CliPrinter.TypePrinter.withType;
import static jecl.cli.CliPrinter.withMemo;

public class FilesIO {
    static Path tmpIODir;
    static {
        try {
            Path res = Paths.get("", "tmp", "io");
            tmpIODir = Files.createDirectories(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        withMemo("FilesIO::sequenceInputStream", FilesIO::sequenceInputStream);
        withMemo("FilesIO::byteArrayInputStream", FilesIO::byteArrayInputStream);
        withMemo("FilesIO::dataOutputStream", FilesIO::dataOutputStream);
        withMemo("FilesIO::dataInputStream", FilesIO::dataInputStream);
        withMemo("FilesIO::fileWriter", FilesIO::fileWriter);
        withMemo("FilesIO::fileReader", FilesIO::fileReader);
        withMemo("FilesIO::printStream", FilesIO::printStream);
        withMemo("FilesIO::listIODir", FilesIO::listIODir);
    }

    public static void sequenceInputStream() {
        try {
            List<FileInputStream> files = List.of("a.txt", "b.txt", "c.txt", "d.txt")
                    .stream()
                    .map(f -> Paths.get(tmpIODir.toString(), f))
                    .peek((Path path) -> {
                        try (FileOutputStream out = new FileOutputStream(path.toString())) {
                            out.write(path.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .map((Path path) -> {
                        try {
                            return new FileInputStream(path.toString());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());

            Enumeration<FileInputStream> e = Collections.enumeration(files);
            SequenceInputStream sio = new SequenceInputStream(e);
            int i = 0;
            while (true) {
                try {
                    if ((i = sio.read()) == -1) break;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.print((char) i);
            }
            sio.close();

            files.forEach(f -> {
                try {
                    f.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void byteArrayInputStream() {
        byte[] buf = { 35, 36, 37, 38 };
        // Create the new byte array input stream
        ByteArrayInputStream byt = new ByteArrayInputStream(buf);
        int k = 0;
        while ((k = byt.read()) != -1) {
            //Conversion of a byte into character
            char ch = (char) k;
            System.out.println("ASCII value of Character is:" + k + "; Special character is: " + ch);
        }
    }

    public static void dataOutputStream() {
        try (FileOutputStream file = new FileOutputStream(
                    Paths.get(tmpIODir.toString(), "dataOutputStream.txt").toString()
            )) {
            DataOutputStream data = new DataOutputStream(file);
            data.writeInt(65);
            data.writeUTF("Hello world");
            data.writeFloat(10.02f);
            data.flush();
            data.close();
            System.out.println("Success...");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void dataInputStream() {
        try (FileInputStream file = new FileInputStream(
                Paths.get(tmpIODir.toString(), "dataOutputStream.txt").toString()
        )) {
            DataInputStream data = new DataInputStream(file);
            System.out.println("Input available: " + data.available());

            System.out.println("Int is read: " + withType(data.readInt()));
            System.out.println("UTF is read: " + withType(data.readUTF()));
            System.out.println("Float is read: " + withType(data.readFloat()));
            System.out.println("EOF is reached: " + withType(data.read()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void fileWriter() {
        try (FileWriter file = new FileWriter(
                Paths.get(tmpIODir.toString(), "fileWriter.txt").toString()
        )) {
            file.write("Hello world!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void fileReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                Paths.get(tmpIODir.toString(), "fileWriter.txt").toString()
        ))) {
            System.out.println(reader.readLine());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void printStream() {
        try (PrintStream printer = new PrintStream(new FileOutputStream(
                Paths.get(tmpIODir.toString(), "printStream.txt").toString()
        ))) {
            printer.println("Hello world!");
            printer.println("Hello space!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void listIODir() {
        File dir = new File(tmpIODir.toString());
        File[] files = dir.listFiles();
        for(File file : files){
            System.out.println(file.getName());
        }
    }
}
