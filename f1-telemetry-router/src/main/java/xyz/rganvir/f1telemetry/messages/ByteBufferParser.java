package xyz.rganvir.f1telemetry.messages;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferParser {
    private final ByteBuffer buffer;

    public ByteBufferParser(byte[] rawData) {
        this.buffer = ByteBuffer.wrap(rawData);
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    int getUint8() {
        return buffer.get();
    }

    int getInt16() {
        return buffer.getShort();
    }

    public int getUint16() {
        int result = 0;
        result |= (0xFF & buffer.get());
        result |= (0xFF & buffer.get()) << 8;
        return  result;
    }

    long getUint32() {
        long result = 0;
        result |= (0xFF & buffer.get());
        result |= (0xFF & buffer.get()) << 8;
        result |= (0xFF & buffer.get()) << 16;
        result |= (long) (0xFF & buffer.get()) << 24;
        return result;
    }

    float getFloat() {
        return buffer.getFloat();
    }

    int[] getUint8Array(int quantity) {
        int[] data = new int[8];
        for (int i = 0; i < quantity; ++i) {
            data[i] = this.getUint8();
        }
        return data;
    }

    float[] getFloatArray(int quantity) {
        float[] data = new float[quantity];
        for (int i = 0; i < quantity; ++i) {
            data[i] = this.getFloat();
        }
        return data;
    }


    public long getLong() {
        return this.buffer.getLong();
    }

    public int[] getUint16Array(int quantity) {
        int[] data = new int[quantity];
        for (int i = 0; i < quantity; ++i) {
            data[i] = this.getUint16();
        }
        return data;
    }
}
