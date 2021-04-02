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

//Edge class with three attribute 
public class Edge implements Comparable<Edge> {
    //edge(u,v) , number of the vertex u 
    private int source;
    //edge(u,v) , number of the vertex v
    private int destination;
    //The weight of the edge
    private int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    public int getSource() {
        return source;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    public int getDestination() {
        return destination;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    public int getWeight() {
        return weight;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Compare edges based on their weight
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}

