package io.input;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlDecodeHexedInputStream extends InputStream {
    private final InputStream source;
    private final int[] buffer = new int[3];
    public int hit;
    public int miss;
    private int bufferPos;
    private int bufferLen;

    public UrlDecodeHexedInputStream(InputStream source) {
        this.source = source;
        resetBuffer();
    }

    public static void main(String[] args) throws IOException {
        var str = "string + and / slash";
        var strEncoded = URLEncoder.encode(str, StandardCharsets.UTF_8);
        strEncoded = strEncoded.replace("+", "%20");

        var input = new UrlDecodeHexedInputStream(new ByteArrayInputStream(strEncoded.getBytes()));
        var out = new ByteArrayOutputStream();

        input.transferTo(out);

        System.out.println(str);
        System.out.println(strEncoded);
        System.out.println(out);
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
        if (bufferPos > -1) {
            if (bufferPos < bufferLen) {
                return buffer[bufferPos++] & 0xFF;
            } else {
                resetBuffer();
            }
        }

        var b = source.read();
        if (b == -1) {
            return b;
        }

        if (b == '%') {
            fillBuffer(b);
            return buffer[bufferPos++] & 0xFF;
        }

        return b;
    }

    /**
     * If this method not present (inherited), then timing is:
     * Copy with URL HEX Decode: PT1M13.993214793S ms
     * Copy with URL HEX Decode: 82,787,563,505 ns
     * If this method is present, then
     * Copy byte-by-byte: PT0.14209307S ms
     * Copy byte-by-byte: 151,419,958 ns
     * Pass through copy: PT0.223678579S ms
     * Pass through copy: 223,821,758 ns
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        // @TODO Add buffered decoding
        return source.read(b, off, len);
    }

    private void resetBuffer() {
        this.bufferPos = -1;
        this.bufferLen = -1;
    }

    private void fillBuffer(int percent) throws IOException {
        var hex1 = source.read();
        var hex2 = source.read();

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
        return (bufferLen - bufferPos) + source.available();
    }

    @Override
    public void close() throws IOException {
        source.close();
    }
}
