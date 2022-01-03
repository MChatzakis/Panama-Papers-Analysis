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

# 2022 - Manos Chatzakis (chatzakis@ics.forth.gr) and Eva Chamilaki (evacham7@gmail.com)
# Networkx implementations of the algorithms for a panama papers dataset (pagerank and more...).
# To run this script, you need to install:
#   1) Python 3 (any version)
#   2) Networkx framework (using pip or conda)
#   3) MatplotLib framework (using pip or conda) !- Only if you want to visualize a network.
# This script was written for the project of cs484 - Complex Network Dynamics, to contribute in the analysis of Panama Papers.

NODE_ID_INDEX = 0
NODE_NAME_INDEX = 1
NODE_ADDRESS_INDEX = 2  # careful
FROM_NODE_ID_INDEX = 0
TO_NODE_ID_INDEX = 2

DATASET_PATH = "../data/csv_panama_papers.2018-02-14/"

GRAPH = nx.DiGraph()
NODE_ID_MAPPINGS = {}

# Load all the data and creates the Graph and a dictionary of mappings ID->names
def create_graph_from_dataset():
    print("\n>>>Parsing the dataset...")

    csvNodeFiles = ["panama_papers.nodes.officer.csv",
                    "panama_papers.nodes.intermediary.csv",
                    "panama_papers.nodes.address.csv",
                    "panama_papers.nodes.entity.csv"]

    csvNodeTypes = [" (officer)",
                    " (intermediary)",
                    " (address)",
                    " (entity)"]

    #nodeColors = ["blue"]
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
            NODE_ID_MAPPINGS[nodeID] = nodeName + csvNodeTypes[i]
            # print(row)
        # print(rows)
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


def run_pagerank():
    print("\n>>>Running pagerank...")

    pagerankDict = nx.pagerank(GRAPH, alpha=0.85, personalization=None,
                               max_iter=100, tol=1e-06, nstart=None, weight='weight', dangling=None)

    topN = 50  # get the top5 results
    top_tuples = sort_dict_by_value(pagerankDict)[:topN]
    # print(topPagerankDict) #raw print

    print_tuple_mappings(top_tuples)


def run_eigenvector_centrality():
    print("\n>>>Running eigenvector centrality...")

    centrality = nx.eigenvector_centrality(GRAPH)

    topN = 500
    top_tuples = sort_dict_by_value(centrality)[:topN]
    # print(topPagerankDict) #raw print

    print_tuple_mappings(top_tuples)


def run_degree_centrality():
    print("\n>>>Running degree centrality...")

    degree = nx.degree_centrality(GRAPH)
    topN = 100
    top_tuples = sort_dict_by_value(degree)[:topN]
    # print(topPagerankDict) #raw print

    print_tuple_mappings(top_tuples)


def run_clustering():
    print(">>>Running Clustering...")

    cluster = nx.clustering(GRAPH)

    topN = 100
    top_tuples = sort_dict_by_value(cluster)[:topN]
    # print(topPagerankDict) #raw print

    print_tuple_mappings(top_tuples)


def show_graph():
    nx.draw(GRAPH, with_labels=True, font_weight='bold')
    plt.show()


def print_tuple_mappings(tuples):
    for tuple in tuples:
        currID = tuple[0]
        currVal = tuple[1]
        currName = NODE_ID_MAPPINGS[currID]

        if currName == " (address)":
            #currName = "(address)"
            continue  # ingore addresses -> comment out this statement to include them

        #print(currID, ":", currName, "->", currVal)
        #print(currName, "->", currVal)
        print(currName, "&", "{:.5f}".format(currVal), "\\\\", "\n \\hline")



def sort_dict_by_value(dict):
    return sorted(dict.items(), key=lambda x: x[1], reverse=True)


def main():
    create_graph_from_dataset()

    #run_pagerank()
    #run_eigenvector_centrality()
    #run_degree_centrality()
    #run_clustering()
    show_graph()


if __name__ == "__main__":
    main()
