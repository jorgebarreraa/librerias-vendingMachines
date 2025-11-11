package com.jess.arms.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class CharacterDecoder {
    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    protected void decodeAtom(PushbackInputStream pushbackInputStream, OutputStream outputStream, int i) throws IOException {
        throw new CEStreamExhausted();
    }

    public void decodeBuffer(InputStream inputStream, OutputStream outputStream) throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        decodeBufferPrefix(pushbackInputStream, outputStream);
        while (true) {
            try {
                int decodeLinePrefix = decodeLinePrefix(pushbackInputStream, outputStream);
                int i = 0;
                while (bytesPerAtom() + i < decodeLinePrefix) {
                    decodeAtom(pushbackInputStream, outputStream, bytesPerAtom());
                    bytesPerAtom();
                    i += bytesPerAtom();
                }
                if (bytesPerAtom() + i == decodeLinePrefix) {
                    decodeAtom(pushbackInputStream, outputStream, bytesPerAtom());
                    bytesPerAtom();
                } else {
                    decodeAtom(pushbackInputStream, outputStream, decodeLinePrefix - i);
                }
                decodeLineSuffix(pushbackInputStream, outputStream);
            } catch (CEStreamExhausted unused) {
                decodeBufferSuffix(pushbackInputStream, outputStream);
                return;
            }
        }
    }

    public byte[] decodeBuffer(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeBuffer(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] decodeBuffer(String str) throws IOException {
        byte[] bArr = new byte[str.length()];
        str.getBytes(0, str.length(), bArr, 0);
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeBuffer(byteArrayInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected void decodeBufferPrefix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
    }

    protected void decodeBufferSuffix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
    }

    public ByteBuffer decodeBufferToByteBuffer(InputStream inputStream) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(inputStream));
    }

    public ByteBuffer decodeBufferToByteBuffer(String str) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(str));
    }

    protected int decodeLinePrefix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
        return bytesPerLine();
    }

    protected void decodeLineSuffix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            int read = inputStream.read();
            if (read == -1) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            bArr[i3 + i] = (byte) read;
        }
        return i2;
    }
}
