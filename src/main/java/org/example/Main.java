package org.example;

public class Main {
    public static void main(String[] args) {
        CompressionLZW menna = new CompressionLZW();
        menna.compressFile("DDIA.pdf");
        DecompressionLZW menna1 = new DecompressionLZW();
                menna1.decompressFile("DDIA.pdf.lzw");

    }
}