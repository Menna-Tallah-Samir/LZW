package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.000");

    public static void main(String[] args) {
        System.out.println("Hello");
        long start, end;
        double compressTime, decompressTime;
        start = System.currentTimeMillis();
        if (args[0].equals("compress")) {
            try {
                double compressRatio = 0;
                System.out.println("Starting compressing");
                CompressionLZW compress = new CompressionLZW();
                compress.compressFile(args[1]);
                end = System.currentTimeMillis();
                compressTime = ((double) (end - start)) / 1000;

                compressRatio = (double) Files.size(Paths.get(args[1] + ".lzw")) / Files.size(Paths.get(args[1]));

                System.out.println("Compressing Time(s): " + compressTime);

                System.out.println("\n Compressing Time(s): " + compressTime
                        + " , Time(mins): " + df.format((compressTime) / 60.0)
                        + " , Compression Ratio: " + df.format(compressRatio));
                System.out.println(" the compressed file path : " + args[1] + ".lzw");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (args[0].equals("decompress")) {
            System.out.println("Starting decompressing");
            start = System.currentTimeMillis();
            DecompressionLZW decompressionLZW = new DecompressionLZW();
            decompressionLZW.decompressFile(args[1]);
            end = System.currentTimeMillis();

            decompressTime = ((double) (end - start)) / 1000;
            System.out.println("\n Decompressing Time(s): "
                    + decompressTime + " ,  Time(mins): " + df.format((decompressTime) / 60.0));

        }

    }
}