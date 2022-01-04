package embeddings;

import csv.CSV;
import csv.CSVLine;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains method to create a triple-like corpus from the Fraud Detection graph.
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author Eva Chamilaki (evacham7@ics.forth.gr)
 */
public class VocabCreator {

    static int ADDRESS_INDEX = 0;
    static int ENTITY_INDEX = 1;
    static int OFFICER_INDEX = 2;
    static int INTERMEDIARY_INDEX = 3;

    static int[] CVSs2use = {0, 1, 2, 3};

    static String startingCSVEdges = "data/csv_panama_papers.2018-02-14/panama_papers.edges.csv";

    static String[] startingCSVs = {
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.address.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary.csv"
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
    
    public static void createVocab() throws FileNotFoundException, IOException {
        Map<Integer, String> IDs2Names = new HashMap<>();

        for (int i : CVSs2use) {
            //String currType = typeNames[i];
            CSV currCSV = new CSV(startingCSVs[i], ",", true);
            currCSV.parse();
            int[] cols = startingCSVsInterestedCols[i];

            currCSV.selectColumns(cols);
            ArrayList<CSVLine> csvLines = currCSV.getParsedLines();

            for (CSVLine csvLine : csvLines) {
                int lineID = Integer.parseInt(csvLine.getColumns(0, 0).get(0).replace("\"", ""));
                String name = csvLine.getColumns(1, 1).get(0).replace("\"", "").replace(" ", "_").replace(".", "");
                //System.out.println("[ " + lineID + "," + name + " ]");
                if(i == ADDRESS_INDEX){
                    name += "(address)";
                }
                
                IDs2Names.put(lineID, name);
            }

            //System.out.println(IDs2Names.get(10163475));
            CSV edges = new CSV(startingCSVEdges, ",", true);
            edges.parse();

             System.out.println("Started the vocab creation...");
            
            ArrayList<CSVLine> totalLines = edges.getParsedLines();
            PrintWriter writer = new PrintWriter("./FraudDetection.txt", "UTF-8");
            
            for (int num = 0; num < totalLines.size(); num++) {
                CSVLine line = totalLines.get(num);

                int fromID = Integer.parseInt(line.getColumns(0, 0).get(0).replace("\"", ""));
                String link = line.getColumns(1, 1).get(0).replace("\"", "").replace(" ", "_").replace(".", "");
                int toID = Integer.parseInt(line.getColumns(2, 2).get(0).replace("\"", ""));
                
                String fromName = IDs2Names.get(fromID);
                String toName = IDs2Names.get(toID);
                
                String vocabLine = fromName + " link:" + link + " " + toName + " .";
                writer.println(vocabLine);
                
                if (num % 10000 == 0) {
                    System.out.println("Proccessed " + num + " out of " + totalLines.size() + " lines.");
                }
            }
            
            writer.close();
            
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        createVocab();
    }
}
