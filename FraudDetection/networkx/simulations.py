import sys
import os
import subprocess
import statistics
import copy
import getopt
import csv
import operator
import collections

from os import path
from sys import argv

import networkx as nx
 
import matplotlib.pyplot as plt

NODE_ID_INDEX = 0
NODE_NAME_INDEX = 1
NODE_ADDRESS_INDEX = 2 #careful
FROM_NODE_ID_INDEX = 0
TO_NODE_ID_INDEX = 2

DATASET_PATH = "../data/csv_panama_papers.2018-02-14/"

GRAPH = nx.DiGraph()
NODE_ID_MAPPINGS = {}

def create_graph_from_dataset():
    csvNodeFiles = ["panama_papers.nodes.officer.csv", 
                    "panama_papers.nodes.intermediary.csv", 
                    "panama_papers.nodes.address.csv", 
                    "panama_papers.nodes.entity.csv"]

    nodeColors = ["blue"]
    csvEdgeFile = "panama_papers.edges.csv"

    for i in range(len(csvNodeFiles)):
        csvNodeFile = csvNodeFiles[i]
        file = open(DATASET_PATH+csvNodeFile, encoding='utf8')
        csvreader = csv.reader(file)
        header = next(csvreader)
        print("Parsing File:", csvNodeFile)
        #rows = []
        for row in csvreader:
            nodeID = int(row[NODE_ID_INDEX])
            nodeName = row[NODE_NAME_INDEX]
            
            GRAPH.add_node(nodeID)
            NODE_ID_MAPPINGS[nodeID] = nodeName
            #print(row)
        #print(rows)
        file.close()

    file = open(DATASET_PATH+csvEdgeFile, encoding='utf8')
    csvreader = csv.reader(file)
    header = next(csvreader)
    print("Parsing File:", csvEdgeFile)
    #rows = []
    for row in csvreader:
        fromNodeID = int(row[FROM_NODE_ID_INDEX])
        toNodeID = int(row[TO_NODE_ID_INDEX])
            
        GRAPH.add_edge(fromNodeID, toNodeID)
        
    file.close()

    print("NODES: ", GRAPH.number_of_nodes())
    print("EDGES: ", GRAPH.number_of_edges())


def show_graph():
    nx.draw(GRAPH, with_labels=True, font_weight='bold')
    plt.show()  

def main():
    create_graph_from_dataset()
    #show_graph()
    #print(list(nx.clustering(GRAPH)))
    #nx.clustering(GRAPH)
    A = nx.pagerank(GRAPH, alpha=0.85, personalization=None, max_iter=100, tol=1e-06, nstart=None, weight='weight', dangling=None)
    #newA = dict(sorted(A.items(), key=operator.itemgetter(0), reverse=False)[:10])
    #newA = dict(sorted(A.items(), key=lambda item: item[1]))[:5]
    #newA = collections.OrderedDict(A)[:5]
    newA = sorted(A.items(), key=lambda x: x[1], reverse=True)[:5]
    print(newA)

if __name__ == "__main__":
    main()