package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        if(args[0].equals("compress")){
            System.out.println("Starting compressing");
            CompressionLZW compress = new CompressionLZW();
            compress.compressFile(args[1]);
        }
        else if(args[0].equals("decompress")){
            System.out.println("Starting decompressing");
            DecompressionLZW decompressionLZW = new DecompressionLZW();
            decompressionLZW.decompressFile(args[1]);
        }

    }
}