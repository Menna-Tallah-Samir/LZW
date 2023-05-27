package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteArrayWrapper {

    private final byte[] byteArray;

    public ByteArrayWrapper(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public ByteArrayWrapper(List<Byte> bytes) {
        this.byteArray = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            this.byteArray[i] = bytes.get(i);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ByteArrayWrapper) {
            ByteArrayWrapper other = (ByteArrayWrapper) o;
            return Arrays.equals(byteArray, other.byteArray);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(byteArray);
    }

    public List<Byte> getByteArray() {
        List<Byte> byteList = new ArrayList<>();
        for (byte b : byteArray) {
            byteList.add(Byte.valueOf(b));
        }
        return byteList;
    }
}
