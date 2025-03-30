package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;
import static jecl.runtime.Profiler.millisFootprint;
import static jecl.runtime.Profiler.timeFootprint;

public class ReadWriteFile {
    static Path zipIn = Path.of("pack.bin");
    static Path zipOut = Path.of("pack-2.bin");

    public static void main(String[] args) throws IOException {
        /**
         * Copy buffered: PT0.542844369S ms
         * Copy buffered: 548,183,190 ns
         * Copy byte-by-byte: PT0.13204579S ms
         * Copy byte-by-byte: 132,272,858 ns
         * Channel transfer: PT0.122613656S ms
         * Channel transfer: 122,785,041 ns
         * Copy with URL String Decode: PT1M18.805301257S ms
         * Copy with URL String Decode: 84,671,590,396 ns
         * Copy with URL HEX Decode: PT1M13.993214793S ms
         * Copy with URL HEX Decode: 82,787,563,505 ns
         */
        wrapTime(false, "Copy buffered", () -> {
            try (
                    var input = new BufferedInputStream(Files.newInputStream(zipIn, READ));
                    var output = new BufferedOutputStream(Files.newOutputStream(zipOut, WRITE, TRUNCATE_EXISTING))
            ) {
                input.transferTo(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        wrapTime(true, "Copy byte-by-byte", () -> {
            try (
                    var input = Files.newInputStream(zipIn, READ);
                    var output = Files.newOutputStream(zipOut, WRITE, TRUNCATE_EXISTING)
            ) {
                input.transferTo(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        wrapTime(false, "Channel transfer", () -> {
            var chunk = 1024 * 1024; // 0.5Mb
            try (var inputStream = new FileInputStream(zipIn.toString());
                 var outputStream = new FileOutputStream(zipOut.toString());
                 var inChannel = inputStream.getChannel();
                 var outChannel = outputStream.getChannel()) {

                long size = inChannel.size();
                long position = 0;
                long count = 0;

                while (position < size) {
                    count = size - position > chunk ? chunk : size - position;
                    position += outChannel.transferFrom(inChannel, position, count);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        wrapTime(false, "Copy with URL String Decode", () -> {
            try (
                    var input = new io.input.UrlDecodeInputStream(Files.newInputStream(zipIn, READ));
                    var output = Files.newOutputStream(zipOut, WRITE, TRUNCATE_EXISTING)
            ) {
                input.transferTo(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        wrapTime(true, "Copy with URL HEX Decode", () -> {
            try (
                    var input = new io.input.UrlDecodeHexedInputStream(Files.newInputStream(zipIn, READ));
                    var output = Files.newOutputStream(zipOut, WRITE, TRUNCATE_EXISTING)
            ) {
                input.transferTo(output);

                System.out.println("Hit: %s, Miss: %s".formatted(input.hit, input.miss));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /**
         * Copy byte-by-byte: PT0.14209307S ms
         * Copy byte-by-byte: 151,419,958 ns
         * Pass through copy: PT0.223678579S ms
         * Pass through copy: 223,821,758 ns
         */
        wrapTime(true, "Pass through copy", () -> {
            try (
                    var input = new io.input.PassThroughInputStream(Files.newInputStream(zipIn, READ));
                    var output = Files.newOutputStream(zipOut, WRITE, TRUNCATE_EXISTING)
            ) {
                input.transferTo(output);
           } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static void wrapTime(boolean toRun, String prefix, Runnable runnable) throws IOException {
        if (!toRun) {
            return;
        }
        Files.deleteIfExists(zipOut);
        Files.createFile(zipOut);

        var ms = timeFootprint();
        var nanos = millisFootprint();

        runnable.run();

        System.out.println(prefix + ": " + ms.get() + " ms");
        System.out.println(prefix + ": " + nanos.get() + " ns");
    }
}
