/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskalvsprim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
Section: KAR
Zuhra Othman Al-saadi            1780353
Bodor Nayaf Alamri               1805739
Shuroog Abdulmajed Alshaikh	 1812184
 */
/**
 *
 * @author bodoralharbi
 */
public class KruskalVsPrim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*•	n=1000 and m={10000, 15000, 25000}, 
•	work n=5000 and m={15000,  25000},
•	work n=10000 and m={15000, 25000}, 
•	work n=20000 and m={200000, 300000},
•	work n=50000 and m=1,000,000
         */
        //Ask the user to enter the number of vertices and edges
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the number of vertices: ");
        int n = input.nextInt();
        System.out.print("Please enter the number of edges: ");
        int m = input.nextInt();
        System.out.println();

        //Create graph
        WeightedGraph graph = new WeightedGraph(n, m);

        //save time before invokes kruskal method 
        long timeBefor = System.currentTimeMillis();
        //invokes kruskal method 
        kruskalMST(graph);
        //save time after invokes kruskal method 
        long timeAfter = System.currentTimeMillis();
        //calculate difference between two times (Elapsed time)
        int TimeE = (int) (timeAfter - timeBefor);

        //Print Elapsed time
        System.out.printf("Elapsed Time: %d ms\n", TimeE);

        //save time before invokes PrimsAlgorithm method 
        long timeBefor2 = System.currentTimeMillis();
        //invokes PrimsAlgorithm method 
        PrimsAlgorithm(graph);
        //save time after invokes PrimsAlgorithm method 
        long timeAfter2 = System.currentTimeMillis();
        //calculate difference between two times (Elapsed time)
        int TimeE2 = (int) (timeAfter2 - timeBefor2);

        //Print Elapsed time
        System.out.printf("Elapsed Time: %d ms\n", TimeE2);

    }
    
//-------------------------------------------------------------------------------------------------------------------------------------
    
    // the code source  : https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
    public static void kruskalMST(WeightedGraph graph) {

        System.out.println("_____________________________________Kruskal Algorithm_____________________________________\n");

        // initialize an array that will keep track of which vertices have been visited
        boolean[] visited = new boolean[graph.getVertices()];
        /* initialize a PriorityQueue to keep track of the possible edges that
           construct the minimum spanning tree.  */
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        // mark the initial vertex as visited
        visited[0] = true;

        //add all the edges to priority queue, sort the edges by it's weight. 
        for (int i = 0; i < graph.getAdjacencylist(0).size(); i++) {
            priorityQueue.add(graph.getAdjacencylist(0).get(i));
        }

        // create parent array by the vertices size. 
        int[] parent = new int[graph.getVertices()];

        // make set for all vertices of the graph
        makeSet(parent, graph);

        // the minumum spanning tree is store as ArrayList. 
        ArrayList<Edge> mst = new ArrayList<>();

        // loop to visit all edges in priorityQueue
        while (priorityQueue.peek() != null) {
            // remove the front edge, which will be with the lowest value among the edges. 
            Edge edge = priorityQueue.remove();
            // if we have already visited the opposite vertex, go to the next edge
            if (visited[edge.getDestination()]) {
                continue;
            }

            //check if adding this edge creates a cycle
            int x_set = find(parent, edge.getSource());
            int y_set = find(parent, edge.getDestination());

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add the edge to the minimum spanning tree. 
                mst.add(edge);

                // union the two elements 
                union(parent, x_set, y_set);

                // make the opposite vertex of the edge as visited.  
                visited[edge.getDestination()] = true;
                // for every edge connected to the opposite vertex, add it to the PriorityQueue
                for (int i = 0; i < graph.getAdjacencylist(edge.getDestination()).size(); i++) {
                    priorityQueue.add(graph.getAdjacencylist(edge.getDestination()).get(i));
                }

            }
        }

        /* assume the graph is connected, but for example if the edges are less than vertices 
            then the graph is not connectd.  By for loop check the connection.         
         */
        boolean connected = true;
        for (int i = 1; i < graph.getVertices(); i++) {
            // there is an unvisited vertix.  
            if (!visited[i]) {
                System.out.println("Error! the graph is not connected. ");
                connected = false;
                return;

            }
        }

    }
//-------------------------------------------------------------------------------------------------------------------------------------
    
    /*the code source  : https://github.com/cormacpayne/algorithms/blob/master/PrimsAlgorithmMST.java
    but we made many edits.*/
    public static void PrimsAlgorithm(WeightedGraph graph) {
        System.out.println("______________________________Prim's Algorithm(Priority Queue)_____________________________\n");
        ArrayList<Edge> mst = new ArrayList<>();
        // initialize an array that will keep track of which vertices have been visited
        boolean[] visited = new boolean[graph.getVertices()];
        // initialize a PriorityQueue 
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        // mark the initial vertex as visited
        visited[0] = true;

        // for every edge connected to the source, add it to the PriorityQueue       
        for (int i = 0; i < graph.getAdjacencylist(0).size(); i++) {
            priorityQueue.add(graph.getAdjacencylist(0).get(i));
        }

        // keep adding edges until the PriorityQueue is empty
        while (!priorityQueue.isEmpty()) {
            Edge e = priorityQueue.remove();

            // if we have already visited the opposite vertex, go to the next edge
            if (visited[e.getDestination()]) {
                continue;
            }

            //mark the opposite vertex as visited
            visited[e.getDestination()] = true;
            //Add an edge to the minimum spanning tree
            mst.add(e);

            // for every edge connected to the opposite vertex, add it to the PriorityQueue
            for (int i = 0; i < graph.getAdjacencylist(e.getDestination()).size(); i++) {
                priorityQueue.add(graph.getAdjacencylist(e.getDestination()).get(i));
            }

        }

        // if we haven't visited all of the vertices, return 
        for (int i = 1; i < graph.getVertices(); i++) {
            if (!visited[i]) {
                System.out.println("Error! the graph is not connected.");
                return;
            }
        }

    }
    //-------------------------------------------------------------------------------------------------------------------------------------
    
    /* this methods: makeSet, find and union are used to construct minimum spanning tree by kruskal algorathim
  the code source  : https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
     */
    public static void makeSet(int[] parent, WeightedGraph graph) {
        //Make set, creating a new element for all Vertices in the graph with a parent pointer to itself.
        for (int i = 0; i < graph.getVertices(); i++) {
            parent[i] = i;
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    
    public static int find(int[] parent, int vertex) {
        /* chain of parent pointers from x upwards through the tree
         until an element is reached whose parent is itself
        to do it, using recursive call of find method. 
         */

        if (parent[vertex] != vertex) {
            return find(parent, parent[vertex]);
        };
        return vertex;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    
    public static void union(int[] parent, int x, int y) {
        // call find method to find the parent of x and y
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }
}
