package com.james.flynn;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;



public class Utils {
    final static Charset ENCODING = StandardCharsets.UTF_8;
    public static List<String> readFileLineInput(String fileName) throws IOException{
        Path path = Paths.get(fileName);
        return Files.readAllLines(path, ENCODING);
    }
    public static ArrayList<ArrayList<String>> readFileDelimitedInput(String fileName, String delimiter) throws IOException{
        ArrayList<ArrayList<String>> fileArray = new  ArrayList<ArrayList<String>> ();
        File file = new File(fileName);
        try (Scanner scanner =  new Scanner(file)){
            while(scanner.hasNextLine()){
                ArrayList<String> processedLine = new ArrayList<String>();
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(delimiter);
                while (lineScanner.hasNext()){
                    processedLine.add(lineScanner.next());
                }
                fileArray.add(processedLine);
                lineScanner.close();
            }
        }
            return fileArray;

    }

}
