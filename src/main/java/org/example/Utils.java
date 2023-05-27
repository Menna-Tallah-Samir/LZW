package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    public static void writeCompressedFile(String file, List<Integer> compressed) {
        File newFile = new File(file + ".lzw");
        try {
            ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(newFile));
            outputFile.writeObject(compressed);
            outputFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDecompressedFile(String file, ByteArrayOutputStream decompressedData) {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("decompressed_"+file.substring(0, file.length()-4)))) {
            outputStream.write(decompressedData.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<ByteArrayWrapper, Integer> getCompressDictionary() {
        HashMap<ByteArrayWrapper, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            byte[] temp = new byte[1];
            temp[0] = (byte) i;
            dictionary.put(new ByteArrayWrapper(temp), i);
        }
        return dictionary;

    }

    public static HashMap<Integer, ByteArrayWrapper> getDecompressDictionary() {
        HashMap<Integer, ByteArrayWrapper> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            byte[] temp = new byte[1];
            temp[0] = (byte) i;
            dictionary.put(i, new ByteArrayWrapper(temp));
        }
        return dictionary;

    }

    public static List<Integer> getCompressedInput(String inputFile) {
        List<Integer> input = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(inputFile)))) {
            input = (ArrayList<Integer>) inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return input;
    }

}
