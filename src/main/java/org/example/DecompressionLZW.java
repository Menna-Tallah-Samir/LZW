package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DecompressionLZW {
    public void decompressFile(String file) {
        try {
            // read file to decompress
            List<Integer> inputCodes = Utils.getCompressedInput(file);

            // initial dictionary
            HashMap<Integer, ByteArrayWrapper> dictionary = Utils.getDecompressDictionary();

            ByteArrayOutputStream decompressed = new ByteArrayOutputStream();
            if (inputCodes.size() > 0) {
                int currCode = inputCodes.get(0);
                if (dictionary.containsKey(currCode)) {
                    List<Byte> currByteArray = dictionary.get(currCode).getByteArray();
                    decompressed.write(getByteArray(currByteArray));

                    for (int code = 1; code < inputCodes.size(); code++) {
                        int nextCode = inputCodes.get(code);
                        List<Byte> nextByteArray;
                        if (dictionary.containsKey(nextCode)) {
                            nextByteArray = dictionary.get(nextCode).getByteArray();

                        } else {
                            nextByteArray = new ArrayList<>();
                            nextByteArray.addAll(currByteArray);
                            nextByteArray.add(currByteArray.get(0));

                        }

                        decompressed.write(getByteArray(nextByteArray));

                        List<Byte> addedByte = new ArrayList<>();
                        addedByte.addAll(currByteArray);
                        addedByte.add(nextByteArray.get(0));
                        dictionary.put(dictionary.size(), new ByteArrayWrapper(addedByte));
                        currByteArray = nextByteArray;
                    }
                }
            }
            Utils.writeDecompressedFile(file, decompressed);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getByteArray(List<Byte> list) {
        if (list == null) {
            return new byte[0];
        }
        byte[] byteArray = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            byteArray[i] = list.get(i);
        }
        return byteArray;
    }

}
