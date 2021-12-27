/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasetParser;

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
import utils.CommonUtils;

/**
 *
 * @author manos
 */
public class DataCleaner {

    static String[] startingCSVs = {
      //  "data/csv_panama_papers.2018-02-14/panama_papers.nodes.address.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer.csv",
       // "data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary.csv"
    };

    
    
    static int[][] startingCSVsInterestedCols = {
       // {0, 2, 3},
        {0, 1, 4},
        {0, 1, 2},
       // {0, 1, 2}
    };

    public static void simplifyDataSimple(int fromRow, int toRow) throws FileNotFoundException, CloneNotSupportedException, IOException {
        //for (String csvFile : startingCSVs) {
        for (int i = 0; i < startingCSVs.length; i++) {
            String csvFile = startingCSVs[i];
            CSV currFile = new CSV(csvFile, ",", true);

            currFile.parse();

            CSVFactory.selectCSVColumnFactory(CSVFactory.selectCSVRowFactory(currFile, fromRow, toRow), startingCSVsInterestedCols[i]).toFile(csvFile.replace(".csv", "_simplified.csv"));
            System.out.println("Parsed file: " + csvFile);
        }
    }

    public static void simplifyDataSampling(int count) throws FileNotFoundException, CloneNotSupportedException, IOException {

        for (String csvFile : startingCSVs) {
            CSV currFile = new CSV(csvFile, ",", true);

            currFile.parse();

            int min = 0;
            int max = currFile.getParsedLines().size();
            int[] cols = CommonUtils.generateRandomNums(count, min, max);

            CSVFactory.selectCSVRowFactory(currFile, cols).toFile(csvFile.replace(".csv", "_simplified.csv"));
            System.out.println("Parsed file: " + csvFile);
        }

    }

    public static void parseEdgesFile() throws FileNotFoundException, IOException {
        String[] CSVs = {
            //"data/csv_panama_papers.2018-02-14/panama_papers.nodes.address_simplified.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity_simplified.csv",
            "data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer_simplified.csv",
            //"data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary_simplified.csv"
        };

        String edgesPath = "data/csv_panama_papers.2018-02-14/panama_papers.edges.csv";
        CSV edgesCSV = new CSV(edgesPath, ",", true);
        edgesCSV.parse();

        ArrayList<Integer> nodesIDs = new ArrayList<>(); // We did not use a set because ddifferent files have different IDs

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
                output2write += line.toString(); //keep the connection if both IDs exist in out current Data
            }
        }

        CommonUtils.writeStringToFile(output2write, edgesPath.replace(".csv", "__selected.csv"));

    }

    public static void simplifyUsingNodes() throws FileNotFoundException, CloneNotSupportedException, IOException {

    }

    public static void main(String[] agrs) throws FileNotFoundException, CloneNotSupportedException, IOException {
        simplifyDataSimple(0, 200);
        parseEdgesFile();
    }

}
