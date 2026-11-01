package io.input;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UrlDecodeInputStream extends InputStream {
    private final InputStream source;
    private byte[] buffer;
    private int bufferPos;
    private int bufferLen;

    public UrlDecodeInputStream(InputStream source) {
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
        this.buffer = new byte[3];
        this.bufferPos = -1;
        this.bufferLen = -1;
    }

    private void fillBuffer(int percentByte) throws IOException {
        var urlChunk = new StringBuilder();
        urlChunk.append((char) percentByte);
        var allRead = true;
        for (int i = 1; i < 3; i++) {
            var b = source.read();
            if (b == -1) {
                allRead = false;
                break;
            }
            urlChunk.append((char) b);
        }

        var chunk = urlChunk.toString();

        if (!allRead) {
            buffer = chunk.getBytes(StandardCharsets.UTF_8);
            bufferPos = 0;
            bufferLen = buffer.length;
            return;
        }

        try {
            var decoded = URLDecoder.decode(chunk, StandardCharsets.UTF_8);
            buffer = decoded.getBytes(StandardCharsets.UTF_8);
            bufferPos = 0;
            bufferLen = buffer.length;
        } catch (Exception e) {
            buffer = chunk.getBytes(StandardCharsets.UTF_8);
            bufferPos = 0;
            bufferLen = buffer.length;
        }
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
