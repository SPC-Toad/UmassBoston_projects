package pa2;
/*************************************************************************
 *  Compilation:  javac EuclideanGraph.java
 *  Execution:    java EuclideanGraph
 *  Dependencies: In.java IntIterator.java
 *
 *  Undirected graph of points in the plane, where the edge weights
 *  are the Euclidean distances.
 *
 *************************************************************************/
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class EuclideanGraph {
    private int V;
    private int E;
    private Bag<Edge> edges;
    private Point2D[] vertices;


    /*******************************************************************
     *  Read in a graph from a file, bare bones error checking.
     *  V E
     *  node: id x y
     *  edge: from to
     *******************************************************************/
    public EuclideanGraph(In in) {
        // Initial Set up.
        // Make string array to take in all the input rows.
        String[] file = in.readAllLines();
        // Take the first row of the input file and split it by space.
        String[] vAndE = file[0].split("\\s+");
        // First integer is number of vertices
        V = Integer.parseInt(vAndE[0]);
        // Second integer is the number of the edges.
        E = Integer.parseInt(vAndE[1]);
        // Declare Bag of edges.
        edges = new Bag<>();
        // Declare array list of vertices.
        vertices = new Point2D[V];
        // Initializing the empty Bag for edges and Arraylist for the vertices.
        // Read the file array V times.
        for (int i = 1; i <= V; i++) {
            // We want to read after 0th row. split the file line by space gap.
            String[] line = file[i].split("\\s+");
            // Array list of that vertex's (x, y) coordinate pair.
            vertices[i - 1] = new Point2D(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
        }

        for (int i = V + 2; i < V + E + 2; i++) {
            // Read the file, and split the space to get connected pair of vertices.
            String[] line = file[i].trim().split("\\s+");
            // First vertex is the source.
            int source = Integer.parseInt(line[0]);
            // Second vertex is the destination
            int destination = Integer.parseInt(line[1]);
            // Make point2D between the connected edge.
            // Weight is the euclidean distance from the source to destination.
            Edge edge = new Edge(source,destination, distance(source, destination));
            // Add the edge to bag of edges.
            edges.add(edge);
        }
    }


    // string representation - takes quadratic time because of string concat
    public String toString() {
        // Empty String s.
        String s = "";
        //
        s += "V = " + V + "\n";
        s += "E = " + E + "\n";

        for (int v = 0; v < V; v++) {
            String t = v + " " + vertices[v] + ": ";

            if (!edges.isEmpty()) {
                for (Iterator<Edge> it = edges.iterator(); it.hasNext(); ) {
                    Edge x = it.next();
                    if (x.other(x.either()) == v) {
                        t += x + " ";
                    }
                }
                s += t + "\n";
            }
        }

        return s;
    }

    public int V() {
        return V;
    }
    public int E() {
        return E;
    }


    public Bag<Edge> adj(int v) {
        Bag<Edge> temp = new Bag<>();
        for (Edge e : edges) {
            if (e.other(e.either()) == v) {
                temp.add(e);
            }
        }
        return temp;
    }

    public Bag<Edge> edges() {
        return edges;
    }

    public double distance(int v, int w) {
        return vertices[v].distanceTo(vertices[w]);
    }

    // test client
    public static void main(String args[]) {
        In in = new In(args[0]);
        EuclideanGraph G = new EuclideanGraph(in);
        System.out.println(G);
    }
}
