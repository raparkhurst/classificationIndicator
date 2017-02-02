package io.digitalsynapse.classificationIndicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by raparkhurst on 2/1/17.
 */
public class ClassificationFileReader {

    public static void main(String[] args) {
        try {
            Reader("/etc/classification");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param ClassificationPath
     * @return
     * @throws IOException
     */
    public static List<String> Reader(String ClassificationPath) throws IOException {
        List<String> list = new ArrayList<>();
        List<String> classificationStringArray = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(ClassificationPath))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        //list.forEach(System.out::println);

        if(list.contains("UNCLASSIFIED")) {
            //System.out.println("Unclassified found!");
            classificationStringArray.add("UNCLASSIFIED");
        } else if(list.contains("CONFIDENTIAL")) {
            //System.out.println("confidential found!");
            classificationStringArray.add("CONFIDENTIAL");
        } else if(list.contains("SECRET")) {
            //System.out.println("secret found!");
            classificationStringArray.add("SECRET");
        } else if(list.contains("TOP SECRET")) {
            //System.out.println("top secret found!");
            classificationStringArray.add("TOP SECRET");
        } else {
            //System.out.println("ERROR - INVALID OR MISSING CLASSIFICATION!");
            classificationStringArray.add("INVALID");
        }



        String classificationString = "";

        for (int i = 0; i < list.size(); i++ ) {
            if (i > 0) {
                classificationString +=  " // " + list.get(i);
            } else {
                classificationString += list.get(i);
            }
        }
        //System.out.println(classificationString);

        classificationStringArray.add(classificationString);

        //System.out.println("\nPrinting formatted classifiedStringArray...");
        //System.out.println(classificationStringArray.get(0));
        //System.out.println(classificationStringArray.get(1));


        return classificationStringArray;
    }
}
