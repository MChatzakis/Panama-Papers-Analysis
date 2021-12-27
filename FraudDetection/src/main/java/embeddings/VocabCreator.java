/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embeddings;

/**
 *
 * @author manos
 */
public class VocabCreator {
    
    static int ADRESS_INDEX = 0;
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

    String[] outputCSVs = {
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.address_simplified.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.entity_simplified.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.officer_simplified.csv",
        "data/csv_panama_papers.2018-02-14/panama_papers.nodes.intermediary_simplified.csv"
    };
    
    public static void createVocab(){
        
        
        
    }
    
    public static void main(String [] args){
        
    }
}
