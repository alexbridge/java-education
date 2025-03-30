package io.input;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class PassThroughInputStream extends InputStream {
    private final InputStream source;

    public PassThroughInputStream(InputStream source) {
        this.source = source;
    }

    @Override
    public int read() throws IOException {
        return source.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return source.read(b, off, len);
    }

    @Override
    public int available() throws IOException {
        return source.available();
    }

    @Override
    public void close() throws IOException {
        source.close();
    }
}
