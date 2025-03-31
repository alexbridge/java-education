package io.input;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class UrlDecodeHexedInputStream extends BufferedInputStream {
    private final int[] buffer = new int[3];
    public int hit;
    public int miss;
    private int bufferPos;
    private int bufferLen;

    public UrlDecodeHexedInputStream(InputStream source) {
        super(source);
        resetBuffer();
    }

    public static void main(String[] args) throws IOException {
        //var str = "percent%plus+slash/".repeat(10_000_000);
        //var plainText = Path.of("plain_text.txt");
        //Files.deleteIfExists(plainText);
        //Files.createFile(plainText);
        //Files.writeString(plainText, str);
        var urlEncodedText = Path.of("url_encoded.txt");
        var urlDecodedText = Path.of("url_decoded.txt");

        //Files.deleteIfExists(urlEncodedText);
        //Files.createFile(urlEncodedText);
        Files.deleteIfExists(urlDecodedText);
        Files.createFile(urlDecodedText);

        //Files.writeString(urlEncodedText, URLEncoder.encode(str, StandardCharsets.UTF_8));

        var input = new UrlDecodeHexedInputStream(Files.newInputStream(urlEncodedText, READ));
        var out = Files.newOutputStream(urlDecodedText, WRITE);

        input.transferTo(out);

        System.out.printf("Hit: %s, Miss: %s%n", input.hit, input.miss);
    }

    private static boolean isHexDigit(int c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    private static int hexDigitValue(int c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        } else {
            return c - 'a' + 10;
        }
    }

    @Override
    public int read() throws IOException {
        //System.out.print("\rAvailable at single: " + in.available());
        //System.out.flush();
//        if (bufferPos > -1) {
//            if (bufferPos < bufferLen) {
//                return buffer[bufferPos++] & 0xFF;
//            } else {
//                resetBuffer();
//            }
//        }
//
//        var b = source.read();
//        if (b == -1) {
//            return b;
//        }
//
//        if (b == '%') {
//            fillBuffer(b);
//            return buffer[bufferPos++] & 0xFF;
//        }

        //return in.read();
        throw new UnsupportedOperationException();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        //System.out.print("\rAvailable: " + in.available());
        //System.out.flush();
        if (len < 3) {
            throw new IOException("Buffered read must have larger buffer, %s given".formatted(len));
        }

        // read 2 bytes less
        var read = super.read(b, off, len - 2);
        if (read == -1) {
            return read;
        }

        boolean hasPercent = false;
        for (int i = 0; i < read; i++) {
            if (b[i] == '%') {
                hasPercent = true;
                break;
            }
        }

        if (!hasPercent) {
            return read;
        }

        var moreLength = 0;
        if (b[read - 1] == '%') {
            moreLength = 2;
        }
        if (read > 1 && b[read - 2] == '%') {
            moreLength = 1;
        }

        var moreRead = 0;
        if (moreLength > 0) {
            moreRead = super.read(b, read, moreLength);
        }

        var effectiveMore = moreRead == -1 ? 0 : moreRead;

        var effectiveB = new byte[read + effectiveMore];

        var cycleB = 0;
        var cycleEffective = 0;
        var haveEncoded = false;
        while (cycleB < effectiveB.length) {
            if (b[cycleB] == '%' && cycleB < effectiveB.length - 2) {
                var hex1 = b[cycleB + 1];
                var hex2 = b[cycleB + 2];

                if (isHexDigit(hex1) && isHexDigit(hex2)) {
                    effectiveB[cycleEffective++] = (byte) ((hexDigitValue(hex1) << 4) + hexDigitValue(hex2));
                    hit++;
                    cycleB += 3;
                    haveEncoded = true;
                    continue;
                }
                miss++;
            }
            effectiveB[cycleEffective++] = b[cycleB++];
        }

        if (haveEncoded) {
            System.arraycopy(effectiveB, 0, b, 0, cycleEffective);
        }
        return cycleEffective;
    }

    private void resetBuffer() {
        this.bufferPos = -1;
        this.bufferLen = -1;
    }

    private void fillBuffer(int percent) throws IOException {
        var hex1 = in.read();
        var hex2 = in.read();

        bufferPos = 0;
        bufferLen = 1;

        if (isHexDigit(hex1) && isHexDigit(hex2)) {
            buffer[0] = (hexDigitValue(hex1) << 4) + hexDigitValue(hex2);
            hit++;
            return;
        }

        miss++;

        buffer[0] = percent;

        if (hex1 == -1) {
            return;
        }
        if (hex2 == -1) {
            buffer[1] = hex1;
            bufferLen = 2;
            return;
        }
        buffer[2] = hex2;
        bufferLen = 3;
    }

    @Override
    public int available() throws IOException {
        return (bufferLen - bufferPos) + in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
