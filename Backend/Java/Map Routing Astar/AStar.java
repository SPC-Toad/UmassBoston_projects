package pa2;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.LinkedQueue;

// A* algorithm
public class AStar {

    private int heurstic; // Regular dijkstra or A*
    private EuclideanGraph G;
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private Edge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices
    private int source;
    private int target;
    private int count;
    private boolean found;


    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every
     * other vertex in the edge-weighted graph {@code G}.
     *
     * @param //g the edge-weighted digraph
     * @param s   the source vertex, t is target
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public AStar(String s, int h) {
        In file = new In(s);
        G = new EuclideanGraph(file);
        distTo = new double[G.V()];
        edgeTo = new Edge[G.E()];
        heurstic = h;
        found = false;
    }


    // Reset dists for new query
    public void reset() {
        for (int v = 0; v < G.V(); v++) {
            this.distTo[v] = Double.POSITIVE_INFINITY;
            this.edgeTo[v] = null;
        }
    }


    // Perform a query on specific start and target nodes
    public int route(int s, int t) {
        source = s;
        target = t;
        reset();
        distTo[s] = 0.0 + heuristic(G, s, t);

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, heuristic(G, s, target));
        while (!pq.isEmpty() || found == true) {
            int v = pq.delMin();
            count++;
            for (Edge e : G.adj(v)) {
                int end = e.other(v);
                if (distTo[end] - distTo[v] - e.weight() > 0.00001) {
                    distTo[end] = distTo[v] + e.weight();
                    edgeTo[end] = e;
                    if (end == target) {
                        found = true;
                    }
                    else if (pq.contains(end)) {
                        pq.decreaseKey(end, distTo[end] + heuristic(G, end, target));
                    } else {
                        pq.insert(end, distTo[end] + heuristic(G, end, target));
                    }
                }
            }
        }
        return count;
    }


    // The function that biases the search
    private double heuristic(EuclideanGraph G, int v, int w) {
        if (heurstic == 1) {
            return G.distance(v, w);
        } else {
            return 0.0;
        }
    }

    /**
     * Returns the length of a shortest path between the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param v the destination vertex
     * @return the length of a shortest path between the source vertex {@code s} and
     * the vertex {@code v}; {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * Returns true if there is a path between the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param v the destination vertex
     * @return {@code true} if there is a path between the source vertex
     * {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path between the source vertex {@code s} and vertex {@code v}.
     *
     * @param v the destination vertex
     * @return a shortest path between the source vertex {@code s} and vertex {@code v};
     * {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.other(v)]) {
            path.push(e);
        }
        return path;
    }

    // check optimality conditions:
    // (i) for all edges e = v-w:            distTo[w] <= distTo[v] + e.weight()
    // (ii) for all edge e = v-w on the SPT: distTo[w] == distTo[v] + e.weight()
    private boolean check(EuclideanGraph G, int s) {
        // check that edge weights are non-negative
        for (Edge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (Edge e : G.adj(v)) {
                int w = e.other(v);
                int start = e.other(w);
                double heuristic = G.distance(w, target);
                if (distTo[start] + e.weight() + heuristic < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            Edge e = edgeTo[w];
            int start = e.other(w);
            double heuristic = G.distance(w, target);
            if (w != e.other(start)) return false;
            if (distTo[start] + e.weight() + heuristic != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // Main testing
    public static void main(String[] args) {
        // Build the graph
        int heuristic = Integer.parseInt(args[2]);
        AStar sp = new AStar(args[0], heuristic);
        In paths = new In(args[1]);
        // Now run queries
        int processed = 0;
        long startTime = System.currentTimeMillis();
        while (!paths.isEmpty()) {
            int s = paths.readInt();
            int t = paths.readInt();
            StdOut.println(s + " " + t);

            processed = sp.route(s, t);
            if (sp.hasPathTo(t)) {
                StdOut.printf("Printing path! %d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (Edge e : sp.pathTo(t)) {
                    StdOut.println(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
            sp.reset();
            StdOut.println("Processed " + processed);
        }
        long endTime = System.currentTimeMillis();
        long tm = endTime - startTime;
        StdOut.println(tm);
    }

}

