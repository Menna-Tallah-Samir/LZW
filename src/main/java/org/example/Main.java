package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        LZW menna = new LZW();
        menna.compression("DDIA.pdf");
    }
}