package com.extract;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File( "data-extract/logs/log");

        FileReader fileReader = new FileReader(file);

        try {

            int singleCh = 0;

            while ((singleCh = fileReader.read()) != -1) {

                System.out.print((char) singleCh);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
        }

    }
}