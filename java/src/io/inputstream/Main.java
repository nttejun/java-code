package io.inputstream;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void TEST_CopyTxtFile() throws IOException {

        File targetTest = new File("/Users/wjjeong/wjjeong/dev/project/java-code/java/resource/targetTest.txt");
        File target = new File("/Users/wjjeong/wjjeong/dev/project/java-code/java/resource/target.txt");

        InputStream inputStream = new FileInputStream(target);
        OutputStream outputStream = new FileOutputStream(targetTest);

        while (true){
            int data = inputStream.read();
            if(data == -1) break;
            outputStream.write(data);
        }

        inputStream.close();
        outputStream.close();


        String sTargetTest = "/Users/wjjeong/wjjeong/dev/project/java-code/java/resource/targetTest.txt";
        String sTarget = "/Users/wjjeong/wjjeong/dev/project/java-code/java/resource/target.txt";

        byte[] bTargetTest = Files.readAllBytes(new File(sTargetTest).toPath());
        byte[] bTarget = Files.readAllBytes(new File(sTarget).toPath());

        assertEquals(bTargetTest, bTarget);

    }

    @Test
    public void TEST_CopyImageFile() throws IOException {

        File targetTest = new File("/Users/wjjeong/wjjeong/dev/project/java-code/java/resource/before.png");
        File target = new File("/Users/wjjeong/wjjeong/dev/project/java-code/java/resource/after.png");

        InputStream inputStream = new FileInputStream(target);
        OutputStream outputStream = new FileOutputStream(targetTest);

        while (true){
            int data = inputStream.read();
            if(data == -1) break;
            outputStream.write(data);
        }

        inputStream.close();
        outputStream.close();

        assertEquals(inputStream.toString(), outputStream.toString());

    }

}
