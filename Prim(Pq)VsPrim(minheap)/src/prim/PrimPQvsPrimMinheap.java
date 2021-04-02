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
public class PrimPQvsPrimMinheap {

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

        //save time before invokes PrimsAlgorithmPq method 
        long timeBefor = System.currentTimeMillis();
        //invokes PrimsAlgorithmPq method 
        PrimsAlgorithmPq(graph);
        //save time after invokes PrimsAlgorithmPq method 
        long timeAfter = System.currentTimeMillis();
        //calculate difference between two times (Elapsed time)
        int TimeE = (int) (timeAfter - timeBefor);

        //Print Elapsed time
        System.out.printf("Elapsed Time: %d ms\n", TimeE);

        //save time before invokes PrimsAlgorithmMinHeap method 
        long timeBefor2 = System.currentTimeMillis();
        //invokes PrimsAlgorithmMinHeap method 
        PrimsAlgorithmMinHeap(graph);
        //save time after invokes PrimsAlgorithmMinHeap method 
        long timeAfter2 = System.currentTimeMillis();
        //calculate difference between two times (Elapsed time)
        int TimeE2 = (int) (timeAfter2 - timeBefor2);

        //Print Elapsed time
        System.out.printf("Elapsed Time: %d ms\n", TimeE2);

    }

//-------------------------------------------------------------------------------------------------------------------------------------
    /*the code source  : https://github.com/cormacpayne/algorithms/blob/master/PrimsAlgorithmMST.java
    but we made many edits.*/
    public static void PrimsAlgorithmPq(WeightedGraph graph) {
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

    /*the code source  : https://github.com/cormacpayne/algorithms/blob/master/PrimsAlgorithmMST.java
    but we made many edits.*/
    public static void PrimsAlgorithmMinHeap(WeightedGraph graph) {

        System.out.println("______________________________Prim's Algorithm(Min Heap)_____________________________\n");
        ArrayList<Edge> mst = new ArrayList<>();

        // initialize an array that will keep track of which vertices have been visited
        boolean[] visited = new boolean[graph.getVertices()];
        /* initialize a  MinHeap that will keep track of the possible edges that
         we can add to the tree we are forming, and will allow us to select the 
         edge of least cost every step of the way*/
        MinHeap minHeap = new MinHeap(graph.getAllEdges().size());

        // mark the initial vertex as visited
        visited[0] = true;

        // for every edge connected to the source, add it to the MinHeap
        for (int i = 0; i < graph.getAdjacencylist(0).size(); i++) {
            minHeap.insert(graph.getAdjacencylist(0).get(i));
        }

        // keep adding edges until the MinHeap is empty
        while (!minHeap.isEmpty()) {
            Edge e = minHeap.remove();

            // if we have already visited the opposite vertex, go to the next edge
            if (visited[e.getDestination()]) {
                continue;
            }

            // mark the opposite vertex as visited
            visited[e.getDestination()] = true;
            //Add an edge to the minimum spanning tree
            mst.add(e);

            // for every edge connected to the opposite vertex, add it to the MinHeap
            for (int i = 0; i < graph.getAdjacencylist(e.getDestination()).size(); i++) {
                minHeap.insert(graph.getAdjacencylist(e.getDestination()).get(i));
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
}
