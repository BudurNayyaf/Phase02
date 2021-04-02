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
/*the code source  : https://stackoverflow.com/questions/49523512/creating-a-min-heap-of-objects-in-java
but we made many edits.*/
public class MinHeap {
    // Java implementation of Min Heap
    private Edge[] EHeap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        EHeap = new Edge[this.maxsize + 1];
        Edge e = new Edge(0, 0, Integer.MIN_VALUE);
        EHeap[0] = e;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Calculate parent position
    private int parent(int pos) {
        return pos / 2;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Calculate left child position
    private int leftChild(int pos) {
        return (2 * pos);
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Calculate right child position
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Check if the edge is leaf edge 
    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Swap two edges
    private void swap(int fpos, int spos) {
        Edge tmp;
        tmp = EHeap[fpos];
        EHeap[fpos] = EHeap[spos];
        EHeap[spos] = tmp;

    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Recursion method to sort min-heap
    private void minHeapify(int pos) {
        //Check if the node is not a leaf then check if its weight greater than its child's weight
        if (!isLeaf(pos) ) {
            if (EHeap[rightChild(pos)] != null) {
                if ((EHeap[pos].getWeight() > EHeap[leftChild(pos)].getWeight()) || (EHeap[pos].getWeight() > EHeap[rightChild(pos)].getWeight())) {
                    if (EHeap[leftChild(pos)].getWeight() < EHeap[rightChild(pos)].getWeight()) {
                        //Swap with the left child then heapify
                        swap(pos, leftChild(pos));
                        minHeapify(leftChild(pos));
                    } else {
                        //Swap with the right child then heapify
                        swap(pos, rightChild(pos));
                        minHeapify(rightChild(pos));
                    }
                }
            }
        }

    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Insert edge to the tree and put it in the appropriate position
    public void insert(Edge element) {
        if (size >= maxsize) {
            return;
        }
        EHeap[++size] = element;
        int current = size;

        while (EHeap[current].getWeight() < EHeap[parent(current)].getWeight()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Create min heap by using heapify
    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Remove the edge having the least weight (root)
    public Edge remove() {
        Edge popped = EHeap[FRONT];
        EHeap[FRONT] = EHeap[size--];
        minHeapify(FRONT);
        return popped;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    //Check if the tree is empty
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
