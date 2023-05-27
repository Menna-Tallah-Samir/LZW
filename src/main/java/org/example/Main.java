package org.example;

public class Main {
    public static void main(String[] args) {
        if(args[0]=="compress"){
            CompressionLZW compress = new CompressionLZW();
            compress.compressFile(args[1]);
        }
        else if(args[0]=="decompress"){
            DecompressionLZW decompressionLZW = new DecompressionLZW();
            decompressionLZW.decompressFile(args[1]);
        }

    }
}