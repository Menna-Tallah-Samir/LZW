package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LZW {

    public void compression(String file){
        //read file to compress
        byte[] input;
        try {
            input = Files.readAllBytes(Paths.get(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //initial dictionary
        HashMap<ByteArrayWrapper, Integer> checkIn = new HashMap<>();
        for(int i=0; i<256; i++){
            byte[] temp = new byte[1];
            temp[0] = (byte) i;
            checkIn.put(new ByteArrayWrapper(temp),i);
        }

        //convert to compressed values
        List<Integer> compressed = new ArrayList<>();
        List<Byte> curr = new ArrayList<>();
        int lastInt = 256;
        for(byte b: input){
            List<Byte> next = new ArrayList<>();
            next.addAll(curr);
            next.add(b);
            if(checkIn.containsKey(new ByteArrayWrapper(next))){
                curr = new ArrayList<>();
                curr.addAll(next);
            }else{
                compressed.add(checkIn.get(new ByteArrayWrapper(curr)));
                checkIn.put(new ByteArrayWrapper(next),lastInt);
                lastInt++;
                curr = new ArrayList<>();
                curr.add(b);
            }
        }

        if(curr.size()>0){
            compressed.add(checkIn.get(new ByteArrayWrapper(curr)));
        }

        //write compressed file
        File newFile = new File(file.split("\\.")[0]+".lzw");
        try {
            ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(newFile));
            outputFile.writeObject(compressed);
            outputFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void decompression(String file){

    }
}
