package com.extract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader;

        reader = new BufferedReader(new FileReader("data-extract/logs/log.txt"));

        String line = reader.readLine();

        System.out.println("/***START***/");
        while (line != null) {

            if (line.contains("Request Data")) {

                if (line.contains("\"ErrorCode\":\"01\"")) {

                    System.out.println(line);

                } else if (line.contains("\"ErrorCode\":\"01\"")) {

                    System.out.println(line);

                }
            }
            line = reader.readLine();
        }

        System.out.println("/***END***/");
    }
}