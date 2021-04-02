/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prim;

/**
 *
 * @author bodoralharbi
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


/* the code source:
https://algorithms.tutorialhorizon.com/weighted-graph-implementation-java/
we make a lot of changes in WeightedGraph constructor.
 */
public class WeightedGraph {

    static LinkedList<Edge>[] adjacencylist;
    static ArrayList<Edge> allEdges = new ArrayList<>();
    LinkedList<Edge> list;
    boolean exit;

    // A Random instance to generate random values
    Random random = new Random();
    static int vertices;
    int source, destenation;

    public WeightedGraph(int nuOfVertices, int nuOfEdges) {
        make_graph(nuOfVertices, nuOfEdges);
    }
//-------------------------------------------------------------------------------------------------------------------------------------

    public void make_graph(int nuOfVertices, int nuOfEdges) {
        this.vertices = nuOfVertices;
        adjacencylist = new LinkedList[nuOfVertices];

        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new LinkedList<>();
        }

        // loop to add the edges until reach the maximum number of edges.
        int i = 0;
        while (i < nuOfEdges) {

            // loop to generate edges to all vertices in the garph. 
            for (int j = 0; j < vertices; j++) {
                source = j;
                // the destenation is random vertex.
                destenation = random.nextInt(vertices);

                // check whether the edge is already generated before.
                exit = checkEdge(source, destenation);

                // this edge is new edge. 
                if (!exit) {
                    /* if the method return false then the random edge is new edge, 
                    so add it to to the graph then, increment the number of edges. */
                    i++;
                }

            }

            // to generate two diffrent random sources.
            source = random.nextInt(vertices);
            destenation = random.nextInt(vertices);

            // check if the edge is already exist.
            list = adjacencylist[source];
            exit = checkEdge(source, destenation);
            // this edge is new edge. 
            if (!exit) {
                /* if the method return false then the random edge was new and add to to the graph.
                    so, increment the number of edges. */
                i++;
            }

        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    // this method to check whether an edge is exit or not. 

    public boolean checkEdge(int source, int destination) {
        // NO self edges 
        if (source == destenation) {
            return true;
        }

        list = adjacencylist[source];
        for (int j = 0; j < list.size(); j++) {
            // if the destenation is exit in the source list, then it already generated before.
            if (destenation == list.get(j).getDestination()) {
                return true;
            }
        }
        // add the new edge. Since it undirect graph, the edges should be in both side. BUT it will not assum as diffrent edges. 
        addEgde(source, destenation);
        addEgde(destenation, source);
        return false;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    // This method is generate a new edge and add it to adjacencylist of the source. 

    public void addEgde(int source, int destination) {
        // the range of edge weight is (1 - 100), since random class is start from ZERO we add 1. 
        int weight = random.nextInt(99) + 1;
        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source].addFirst(edge); //for directed graph
        allEdges.add(edge); //add to total edges
    }
//-------------------------------------------------------------------------------------------------------------------------------------

    public LinkedList<Edge> getAdjacencylist(int edgeNo) {
        return adjacencylist[edgeNo];
    }
//-------------------------------------------------------------------------------------------------------------------------------------

    public ArrayList<Edge> getAllEdges() {
        return allEdges;
    }
//-------------------------------------------------------------------------------------------------------------------------------------

    public int getVertices() {
        return vertices;
    }
}
