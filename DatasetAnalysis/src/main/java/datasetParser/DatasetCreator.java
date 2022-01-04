package datasetParser;

import csv.CSV;
import csv.CSVFactory;
import csv.CSVLine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import utils.CommonUtils;

/**
 * This class contains all methods to apply for dataset pre-processing (sampling, simple, reconstruction) and some other 
 * utility functions.
 * 
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author Eva Chamilaki (evacham7@gmail.com)
 */
public class DatasetCreator {

    static int ADDRESS_INDEX = 0;
    static int ENTITY_INDEX = 1;
    static int OFFICER_INDEX = 2;
    static int INTERMEDIARY_INDEX = 3;

    static int[] CVSs2use = {0, 1, 2, 3};

    static String startingCSVEdges = "../Data/csv_panama_papers.2018-02-14/panama_papers.edges.csv";

    static String[] startingCSVs = {
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.address.csv",
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity.csv",
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer.csv",
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary.csv"
    };

    static String[] typeNames = {
        "address",
        "entity",
        "officer",
        "intermediary"
    };

    static int[][] startingCSVsInterestedCols = {
        {0, 2, 3},
        {0, 1, 4},
        {0, 1, 2},
        {0, 1, 2}
    };

    String[] outputCSVs = {
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.address_simplified.csv",
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity_simplified.csv",
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer_simplified.csv",
        "../Data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary_simplified.csv"
    };

    public static void createDatasetSimple() throws FileNotFoundException, IOException, CloneNotSupportedException {
        /*Step 1: Get some edges from the file and save the IDs in a set*/
        CSV edges = new CSV(startingCSVEdges, ",", true);
        edges.parse();

        int startRow = 0;
        int count = 4000;

        CSV selectedEdges = CSVFactory.selectCSVRowFactory(edges, startRow, startRow + count);

        ArrayList<CSVLine> lines = selectedEdges.getParsedLines();
        Set<Integer> nodeIDs = new HashSet<>();
        int index = 0;
        String edgesOutput = "ID, " + selectedEdges.getHeader().toString();
        for (CSVLine line : lines) {
            int fromID = Integer.parseInt(line.getColumns(0, 0).get(0));
            int toID = Integer.parseInt(line.getColumns(2, 2).get(0));

            nodeIDs.add(toID);
            nodeIDs.add(fromID);

            index++;

            edgesOutput += index + "," + line.toString();
        }

        CommonUtils.writeStringToFile(edgesOutput, startingCSVEdges.replace(".csv", "__selected.csv"));
        System.out.println("================ \n Phase 1 Completed.\n================");

        /*Step 2: Use the set to find the nodes from the file*/
        edgesOutput = "Type, ID, Name/Address, CountryCode\n";
        for (int i : CVSs2use) {
            String currType = typeNames[i];
            CSV currCSV = new CSV(startingCSVs[i], ",", true);
            currCSV.parse();
            int[] cols = startingCSVsInterestedCols[i];

            currCSV.selectColumns(cols);
            ArrayList<CSVLine> csvLines = currCSV.getParsedLines();

            for (CSVLine csvLine : csvLines) {
                int lineID = Integer.parseInt(csvLine.getColumns(0, 0).get(0).replace("\"", ""));

                if (nodeIDs.contains(lineID)) {
                    edgesOutput += currType + "," + csvLine.toString();
                }

            }
        }

        CommonUtils.writeStringToFile(edgesOutput, "../Data/csv_panama_papers.2018-02-14/nodes.csv");
        System.out.println("================ \n Phase 2 Completed.\n================");
    }

    public static void createDatasetSampling() throws FileNotFoundException, IOException, CloneNotSupportedException {
        /*Step 1: Get some edges from the file and save the IDs in a set*/
        CSV edges = new CSV(startingCSVEdges, ",", true);
        edges.parse();

        int min = 0;
        int max = edges.getParsedLines().size();
        int count = 4000;

        int[] randRows = CommonUtils.generateRandomNums(count, min, max);

        CSV selectedEdges = CSVFactory.selectCSVRowFactory(edges, randRows);

        ArrayList<CSVLine> lines = selectedEdges.getParsedLines();
        Set<Integer> nodeIDs = new HashSet<>();
        int index = 0;
        String edgesOutput = "ID, " + selectedEdges.getHeader().toString();
        for (CSVLine line : lines) {
            int fromID = Integer.parseInt(line.getColumns(0, 0).get(0));
            int toID = Integer.parseInt(line.getColumns(2, 2).get(0));

            nodeIDs.add(toID);
            nodeIDs.add(fromID);

            index++;

            edgesOutput += index + "," + line.toString();
        }

        CommonUtils.writeStringToFile(edgesOutput, startingCSVEdges.replace(".csv", "__selected.csv"));
        System.out.println("================ \n Phase 1 Completed.\n================");

        /*Step 2: Use the set to find the nodes from the file*/
        edgesOutput = "Type, ID, Name/Address, CountryCode\n";
        for (int i : CVSs2use) {
            String currType = typeNames[i];
            CSV currCSV = new CSV(startingCSVs[i], ",", true);
            currCSV.parse();
            int[] cols = startingCSVsInterestedCols[i];

            currCSV.selectColumns(cols);
            ArrayList<CSVLine> csvLines = currCSV.getParsedLines();

            for (CSVLine csvLine : csvLines) {
                int lineID = Integer.parseInt(csvLine.getColumns(0, 0).get(0).replace("\"", ""));

                if (nodeIDs.contains(lineID)) {
                    edgesOutput += currType + "," + csvLine.toString();
                }

            }
        }

        CommonUtils.writeStringToFile(edgesOutput, "../Data/csv_panama_papers.2018-02-14/nodes.csv");
        System.out.println("================ \n Phase 2 Completed.\n================");

    }

    public static void createDatasetPaths() throws FileNotFoundException, IOException, CloneNotSupportedException {

        int depth = 3;
        CSV edges = new CSV(startingCSVEdges, ",", true);
        edges.parse();
        String output = edges.getHeader().toString();

        //int startingNode = 11000007;
        int startingNode = 10071302;

        Set<Integer> nodeIDs = new HashSet<>();

        ArrayList<ArrayList<Integer>> levelNodes = new ArrayList<>();
        ArrayList<Integer> firstLevelNode = new ArrayList<>();
        ArrayList<Integer> currentLevelNodes = null;

        firstLevelNode.add(startingNode);
        levelNodes.add(firstLevelNode);

        int levelBFS = 0;
        while (levelBFS < depth && !(currentLevelNodes = levelNodes.get(levelBFS)).isEmpty()) {
            ArrayList<Integer> nextLevelNodes = new ArrayList<>();
            for (Integer currentNode : currentLevelNodes) {
                String toPrint = "Current Node: " + currentNode + ": [";
                nodeIDs.add(currentNode);
                CSV groupNeighboursCSV = CSVFactory.selectCSVRowsByValueFactory(edges, 0, currentNode + "");
                ArrayList<CSVLine> lines = groupNeighboursCSV.getParsedLines();
                for (CSVLine line : lines) {
                    int toID = Integer.parseInt(line.getColumns(2, 2).get(0));
                    toPrint += toID + " ";
                    if (!nodeIDs.contains(toID)) {
                        nextLevelNodes.add(toID);
                    }
                    //should write to file

                    output += line.toString();
                }
                //System.out.println(toPrint + "]");

            }

            levelNodes.add(nextLevelNodes);
            levelBFS++;

        }

        CommonUtils.writeStringToFile(output, startingCSVEdges.replace(".csv", "__selectedPATHS.csv"));
        System.out.println("================ \n Phase 1 Completed.\n================");
        
         /*Step 2: Use the set to find the nodes from the file*/
        String nodesOutput = "Type, ID, Name/Address, CountryCode\n";
        for (int i : CVSs2use) {
            String currType = typeNames[i];
            CSV currCSV = new CSV(startingCSVs[i], ",", true);
            currCSV.parse();
            int[] cols = startingCSVsInterestedCols[i];

            currCSV.selectColumns(cols);
            ArrayList<CSVLine> csvLines = currCSV.getParsedLines();

            for (CSVLine csvLine : csvLines) {
                int lineID = Integer.parseInt(csvLine.getColumns(0, 0).get(0).replace("\"", ""));

                if (nodeIDs.contains(lineID)) {
                    nodesOutput += currType + "," + csvLine.toString();
                }

            }
        }

        CommonUtils.writeStringToFile(nodesOutput, "../Data/csv_panama_papers.2018-02-14/nodes.csv");
        System.out.println("================ \n Phase 2 Completed.\n================");
        
    }

    public static void mergeNodes() throws FileNotFoundException, IOException, CloneNotSupportedException {
        String mergeOutput = "Type, ID, Name/Address, CountryCode\n";
        for (int i : CVSs2use) {
            String currType = typeNames[i];
            CSV currCSV = new CSV(startingCSVs[i], ",", true);
            currCSV.parse();
            int[] cols = startingCSVsInterestedCols[i];

            currCSV.selectColumns(cols);
            ArrayList<CSVLine> csvLines = currCSV.getParsedLines();

            for (CSVLine csvLine : csvLines) {
                mergeOutput += currType + "," + csvLine.toString();
            }

            CommonUtils.writeStringToFile(mergeOutput, "../Data/csv_panama_papers.2018-02-14/nodesMerged.csv");
        }
    }

    public static void shuffleCSV() throws FileNotFoundException, IOException {
        CSV edges = new CSV(startingCSVEdges, ",", true);
        edges.parse();

        ArrayList<CSVLine> lines = edges.getParsedLines();
        Collections.shuffle(lines);

        edges.setParsedLines(lines);
        edges.toFile("../Data/csv_panama_papers.2018-02-14/shuffledEdges.csv");
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, CloneNotSupportedException {
        createDatasetSimple();
        createDatasetSampling();
        createDatasetPaths();
    }

}
