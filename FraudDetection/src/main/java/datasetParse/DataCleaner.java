/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasetParse;

import csv.CSV;
import csv.CSVFactory;
import csv.CSVLine;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author manos
 */
public class DataCleaner {

    public static void simplifyData() throws FileNotFoundException, CloneNotSupportedException, IOException {
        String[] CSVs = {
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.address.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary.csv"
        };

        int from = 0, to = 200;
        //int [] cols = {1,5,7,10,12};

        for (String csvFile : CSVs) {
            CSV currFile = new CSV(csvFile, ",", true);

            currFile.parse();

            int min = 0;
            int max = currFile.getParsedLines().size();
            int[] cols = generateRandomNums(200, min, max);

            CSVFactory.selectCSVRowFactory(currFile, cols).toFile(csvFile.replace(".csv", "_simplified.csv"));
            System.out.println("Parsed file: " + csvFile);
        }

    }

    public static void parseEdgesFile() throws FileNotFoundException, IOException {
        String[] CSVs = {
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.address_simplified.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity_simplified.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer_simplified.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary_simplified.csv"
        };

        String edgesPath = "data/csv_panama_papers.2018-02-14/panama_papers.edges.csv";
        CSV edgesCSV = new CSV(edgesPath, ",", true);
        edgesCSV.parse();

        ArrayList<Integer> nodesIDs = new ArrayList<>();

        for (String csvFile : CSVs) {
            System.out.println("Parsing file: " + csvFile);
            CSV currFile = new CSV(csvFile, ",", true);
            currFile.parse();
            ArrayList<Integer> IDs = currFile.getColumnAsInt(0);
            nodesIDs.addAll(IDs);
        }

        ArrayList<CSVLine> edgesLines = edgesCSV.getParsedLines();

        String output2write = edgesCSV.getHeader().toString();

        for (CSVLine line : edgesLines) {
            int currFromID = Integer.parseInt(line.getColumns(0, 0).get(0));
            int currToID = Integer.parseInt(line.getColumns(2, 2).get(0));

            if (nodesIDs.contains(currFromID) && (nodesIDs.contains(currToID))) {
                output2write += line.toString();
            }
        }

        writeStringToFile(output2write, edgesPath.replace(".csv", "__selected.csv"));
    }

    public static void main(String[] agrs) throws FileNotFoundException, CloneNotSupportedException, IOException {
        simplifyData();
        parseEdgesFile();
    }

    public static int[] generateRandomNums(int count, int min, int max) {
        ArrayList<Integer> randomNums = new ArrayList<>();
        int randomNumsCount = 0;

        while (randomNumsCount < count) {
            int rand = generateRandomNum(min, max);
            if (!randomNums.contains(rand)) {
                randomNums.add(rand);
                randomNumsCount++;
            }
        }
        int[] res = randomNums.stream().mapToInt(i -> i).toArray();
        return res;
    }

    public static Integer[] ArrayList2Array(ArrayList<Integer> list) {
        return (Integer[]) list.toArray();
    }

    public static int generateRandomNum(int min, int max) {
        //System.out.println("Random value in int from " + min + " to " + max + ":");
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
        //System.out.println(random_int);
    }

    public static String writeStringToFile(String data, String filepath) {
        File file = new File(filepath);

        try (FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] bytes = data.getBytes();
            bos.write(bytes);
            bos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
}
