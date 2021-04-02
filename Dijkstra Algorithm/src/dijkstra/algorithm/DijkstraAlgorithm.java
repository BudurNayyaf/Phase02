/*
Section: KAR
Zuhra Othman Al-saadi            1780353
Bodor Nayaf Alamri               1805739
Shuroog Abdulmajed Alshaikh	 1812184
 */

/* 
This code is an implementation of the Dijkstra algorithm.
The data structure used for implementing the priority queue is LinkedList.
2D weight matrix used to represent the graph.
*/
package dijkstra.algorithm;

import java.util.LinkedList;

public class DijkstraAlgorithm {

    public static void main(String[] args) {

        // 2D weight matrix used to represent the graph.
        int[][] distances_matrix = new int[12][12];

        // Method for filling the matrix with the weight of the edges.
        fill_matrix(distances_matrix);
        
        // Implementation of Dijkstra algorithm.
        dijkstra(distances_matrix, 0);
    }
    ////////////////////////////////////////////////////////////////////////////
    
    // Method for filling the matrix with the weight of the edges.
    public static void fill_matrix(int[][] distances_matrix) {

        // 12-city dataset from (http://www.the-saudi.net/saudi-arabia/saudi_city_distances.htm)
        distances_matrix[0][1] = distances_matrix[1][0] = 79;
        distances_matrix[0][2] = distances_matrix[2][0] = 420;
        distances_matrix[0][3] = distances_matrix[3][0] = 949;
        distances_matrix[0][4] = distances_matrix[4][0] = 1343;
        distances_matrix[0][5] = distances_matrix[5][0] = 167;
        distances_matrix[0][6] = distances_matrix[6][0] = 625;
        distances_matrix[0][7] = distances_matrix[7][0] = 1024;
        distances_matrix[0][8] = distances_matrix[8][0] = 863;
        distances_matrix[0][9] = distances_matrix[9][0] = 777;
        distances_matrix[0][10] = distances_matrix[10][0] = 710;
        distances_matrix[0][11] = distances_matrix[11][0] = 905;

        distances_matrix[1][2] = distances_matrix[2][1] = 358;
        distances_matrix[1][3] = distances_matrix[3][1] = 870;
        distances_matrix[1][4] = distances_matrix[4][1] = 1265;
        distances_matrix[1][5] = distances_matrix[5][1] = 88;
        distances_matrix[1][6] = distances_matrix[6][1] = 627;
        distances_matrix[1][7] = distances_matrix[7][1] = 1037;
        distances_matrix[1][8] = distances_matrix[8][1] = 876;
        distances_matrix[1][9] = distances_matrix[9][1] = 790;
        distances_matrix[1][10] = distances_matrix[10][1] = 685;
        distances_matrix[1][11] = distances_matrix[11][1] = 912;

        distances_matrix[2][3] = distances_matrix[3][2] = 848;
        distances_matrix[2][4] = distances_matrix[4][2] = 1343;
        distances_matrix[2][5] = distances_matrix[5][2] = 446;
        distances_matrix[2][6] = distances_matrix[6][2] = 985;
        distances_matrix[2][7] = distances_matrix[7][2] = 679;
        distances_matrix[2][8] = distances_matrix[8][2] = 518;
        distances_matrix[2][9] = distances_matrix[9][2] = 432;
        distances_matrix[2][10] = distances_matrix[10][2] = 1043;
        distances_matrix[2][11] = distances_matrix[11][2] = 1270;

        distances_matrix[3][4] = distances_matrix[4][3] = 395;
        distances_matrix[3][5] = distances_matrix[5][3] = 782;
        distances_matrix[3][6] = distances_matrix[6][3] = 1064;
        distances_matrix[3][7] = distances_matrix[7][3] = 1304;
        distances_matrix[3][8] = distances_matrix[8][3] = 330;
        distances_matrix[3][9] = distances_matrix[9][3] = 640;
        distances_matrix[3][10] = distances_matrix[10][3] = 1272;
        distances_matrix[3][11] = distances_matrix[11][3] = 950;

        distances_matrix[4][5] = distances_matrix[5][4] = 1177;
        distances_matrix[4][6] = distances_matrix[6][4] = 1495;
        distances_matrix[4][7] = distances_matrix[7][4] = 1729;
        distances_matrix[4][8] = distances_matrix[8][4] = 725;
        distances_matrix[4][9] = distances_matrix[9][4] = 1035;
        distances_matrix[4][10] = distances_matrix[10][4] = 1667;
        distances_matrix[4][11] = distances_matrix[11][4] = 1345;

        distances_matrix[5][6] = distances_matrix[6][5] = 561;
        distances_matrix[5][7] = distances_matrix[7][5] = 1204;
        distances_matrix[5][8] = distances_matrix[8][5] = 936;
        distances_matrix[5][9] = distances_matrix[9][5] = 957;
        distances_matrix[5][10] = distances_matrix[10][5] = 763;
        distances_matrix[5][11] = distances_matrix[11][5] = 864;

        distances_matrix[6][7] = distances_matrix[7][6] = 1649;
        distances_matrix[6][8] = distances_matrix[8][6] = 1488;
        distances_matrix[6][9] = distances_matrix[9][6] = 1402;
        distances_matrix[6][10] = distances_matrix[10][6] = 202;
        distances_matrix[6][11] = distances_matrix[11][6] = 280;

        distances_matrix[7][8] = distances_matrix[8][7] = 974;
        distances_matrix[7][9] = distances_matrix[9][7] = 664;
        distances_matrix[7][10] = distances_matrix[10][7] = 1722;
        distances_matrix[7][11] = distances_matrix[11][7] = 1929;

        distances_matrix[8][9] = distances_matrix[9][8] = 310;
        distances_matrix[8][10] = distances_matrix[10][8] = 1561;
        distances_matrix[8][11] = distances_matrix[11][8] = 1280;

        distances_matrix[9][10] = distances_matrix[10][9] = 1475;
        distances_matrix[9][11] = distances_matrix[11][9] = 1590;

        distances_matrix[10][11] = distances_matrix[11][10] = 482;
    }

    // Implementation of Dijkstra algorithm.
    public static void dijkstra(int[][] distances_matrix, int sourse_vertex) {

        // LinkedList that represents the priority queue of remaining vertices (V-Vt).
        LinkedList<vertex> priority_queue = new LinkedList<vertex>();
        
        // LinkedList that represents the tree vertices (Vt)
        LinkedList<vertex> Vt = new LinkedList<vertex>();

        for (int i = 0; i < distances_matrix.length; i++) {
            
            // Initialize vertex priority in the priority queue
            priority_queue.add(new vertex(i, Integer.MAX_VALUE, null));
        }

        // Update priority of the source with 0
        priority_queue.get(sourse_vertex).setD(0);

        for (int i = 0; i < distances_matrix.length; i++) {
            
            // Delete the minimum priority element
            vertex temp = priority_queue.poll();
            
            // Add the deleted vertex to the tree vertices
            Vt.add(temp); 

            // For every vertex in V-Vt that is adjacent to the last vertex 
            // that added to the tree vertices, do this...
            for (int j = 0; j < priority_queue.size(); j++) {
                
                // Last vertex that added to the tree vertices
                int vertix_A = temp.getVertex_name();
                // A vertex in V-Vt priority queue
                int vertix_B = priority_queue.get(j).getVertex_name();

                // If the weight between A & B more than 0, 
                // there is a path between A & B, So they are adjacent 
                if (distances_matrix[vertix_A][vertix_B] > 0) {
                    
                    // Get the length of the shortest path from the source to this vertex
                    int d_vertex_A = temp.getD(); 
                    int d_vertex_B = priority_queue.get(j).getD();
                    
                    // weight of the edge between A & B
                    int distance_A_to_B = distances_matrix[vertix_A][vertix_B];

                    if ((d_vertex_A + distance_A_to_B) < d_vertex_B) {
                        
                        // Update the length of the shortest path
                        priority_queue.get(j).setD(d_vertex_A + distance_A_to_B);
                        
                        // Update the penultimate vertex
                        priority_queue.get(j).setP(temp.getVertex_name() + "");
                    }
                }
            }
        }

        print_shortest_paths(Vt);
    }
    ////////////////////////////////////////////////////////////////////////////
    
    public static void print_shortest_paths(LinkedList<vertex> Vt) {

        System.out.println("The shortest paths from Jeddah to every other city in the graph");
        
        // For every vertex in tree vertices do this...
        for (int i = 1; i < Vt.size(); i++) {
            
            // Replace the vertex number with its actual name to print 
            String vertex_name = city_name(Vt.get(i).getVertex_name());
            
            // Store the shortest path for this vertex
            int length = Vt.get(i).getD();
            String path = "";

            System.out.print("From Jeddah to " + vertex_name + " ----- [");
            
            // Backtrack along the path to reach the source then print the path
            path_backtrack(Vt.get(i), Vt, path);
            System.out.println("] ----- of length " + length);
        }
    }
    ////////////////////////////////////////////////////////////////////////////

    // This method takes the vertex number and returns the actual name
    public static String city_name(int x) {
        String city = "";

        switch (x) {
            case 0 ->
                city = "Jeddah";
            case 1 ->
                city = "Makkah";
            case 2 ->
                city = "Madinah";
            case 3 ->
                city = "Riyadh";
            case 4 ->
                city = "Dammam";
            case 5 ->
                city = "Taif";
            case 6 ->
                city = "Abha";
            case 7 ->
                city = "Tabuk";
            case 8 ->
                city = "Qasim";
            case 9 ->
                city = "Hail";
            case 10 ->
                city = "Jizan";
            case 11 ->
                city = "Najran";
        }
        return city;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    // Backtrack along the path to reach the source then print the path
    public static void path_backtrack(vertex x, LinkedList<vertex> Vt, String path) {

        String vertex_name = city_name(x.getVertex_name());

        // x == 0 >>> source
        if (x.getVertex_name() == 0) {
            path = vertex_name + path;
            System.out.print(path);
            return;

        } else {
            path = " - " + vertex_name + path;
            path_backtrack(Vt.get(Integer.parseInt(x.getP())), Vt, path);
        }
    }
}
////////////////////////////////////////////////////////////////////////////////

class vertex {

    private int vertex_name; // The number that represents the city (vertex)
    private int d; // The length of the shortest path from the source to this vertex
    private String p; // Penultimate vertex

    public vertex(int vertex_name, int d, String p) {
        this.vertex_name = vertex_name;
        this.d = d;
        this.p = p;
    }

    public int getVertex_name() {
        return vertex_name;
    }

    public void setVertex_name(int vertex_name) {
        this.vertex_name = vertex_name;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

}
