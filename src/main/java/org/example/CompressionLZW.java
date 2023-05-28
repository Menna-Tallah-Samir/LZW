package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompressionLZW {

    public void compressFile(String file) {
        // initial dictionary
        HashMap<ByteArrayWrapper, Integer> checkIn = Utils.getCompressDictionary();
        // convert to compressed values
        List<Integer> compressed = new ArrayList<>();
        List<Byte> curr = new ArrayList<>();
        int lastInt = 256;

        try (FileInputStream inputStream = new FileInputStream(new File(file))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    byte b = buffer[i];
                    List<Byte> next = new ArrayList<>();
                    next.addAll(curr);
                    next.add(b);
                    if (checkIn.containsKey(new ByteArrayWrapper(next))) {
                        curr = new ArrayList<>();
                        curr.addAll(next);
                    } else {
                        compressed.add(checkIn.get(new ByteArrayWrapper(curr)));
                        checkIn.put(new ByteArrayWrapper(next), lastInt);
                        lastInt++;
                        curr = new ArrayList<>();
                        curr.add(b);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (curr.size() > 0) {
            compressed.add(checkIn.get(new ByteArrayWrapper(curr)));
        }
        Utils.writeCompressedFile(file, compressed);
    }

}
