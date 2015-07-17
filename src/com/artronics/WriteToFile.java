package com.artronics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class WriteToFile{
    private String Content;

    private WriteToFile() {
    }
    public static <T> void write(T content) throws IOException {

        try {
            File data = new File("data.txt");
            data.createNewFile();
            FileWriter writer = new FileWriter(data, false);
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An Error ocured during writing file.\n");
//            e.printStackTrace();
        }
    }

}
