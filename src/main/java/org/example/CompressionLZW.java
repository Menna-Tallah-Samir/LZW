package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompressionLZW {

    public void compressFile(String file) {
        //read file to compress
        byte[] input;
        try {
            input = Files.readAllBytes(Paths.get(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //initial dictionary
        HashMap<ByteArrayWrapper, Integer> checkIn = Utils.getCompressDictionary();

        //convert to compressed values
        List<Integer> compressed = new ArrayList<>();
        List<Byte> curr = new ArrayList<>();
        int lastInt = 256;
        for (byte b : input) {
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

        if (curr.size() > 0) {
            compressed.add(checkIn.get(new ByteArrayWrapper(curr)));
        }
        Utils.writeCompressedFile(file, compressed);
    }

}
