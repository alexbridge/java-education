package io.input;

import java.io.IOException;
import java.io.InputStream;

public class UrlDecodeHexedInputStream extends InputStream {
    private final InputStream source;
    private int[] buffer;
    private int bufferPos;
    private int bufferLen;

    public UrlDecodeHexedInputStream(InputStream source) {
        this.source = source;
        resetBuffer();
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

    private void resetBuffer() {
        this.buffer = new int[3];
        this.bufferPos = -1;
        this.bufferLen = -1;
    }

    private void fillBuffer(int percent) throws IOException {
        var hex1 = source.read();
        var hex2 = source.read();

        if (isHexDigit(hex1) && isHexDigit(hex2)) {
            int decoded = (hexDigitValue(hex1) << 4) + hexDigitValue(hex2);
            buffer = new int[] { percent, decoded };
            bufferPos = 0;
            bufferLen = 2;
            return;
        }

        if (hex1 == -1) {
            buffer = new int[] { percent };
            bufferPos = 0;
            bufferLen = 1;
            return;
        }
        if (hex2 == -1) {
            buffer = new int[] { percent, hex1 };
            bufferPos = 0;
            bufferLen = 2;
            return;
        }
        buffer = new int[] { percent, hex1, hex2 };
        bufferPos = 0;
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
}
