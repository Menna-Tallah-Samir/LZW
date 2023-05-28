package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static void writeCompressedFile(String file, List<Integer> compressed) {
        File newFile = new File(file + ".lzw");
        try {
            DataOutputStream outputFile = new DataOutputStream(new FileOutputStream(newFile));
            for (int code : compressed) {
                outputFile.writeInt(code);
            }
            outputFile.close();
            System.out.println("File is compressed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDecompressedFile(String file, ByteArrayOutputStream decompressedData) {
        String[] s = file.split("\\.");
        String path = s[0] + "_decompressed." + s[1];
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path))) {
            outputStream.write(decompressedData.toByteArray());
            System.out.println("File is decompressed");

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

    public static List<Integer> getCompressedInput(String file) {
        List<Integer> compressed = new ArrayList<>();
        try {
            DataInputStream inputFile = new DataInputStream(new FileInputStream(new File(file)));
            while (inputFile.available() > 0) {
                int code = inputFile.readInt();
                compressed.add(code);
            }
            inputFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return compressed;
    }

}
