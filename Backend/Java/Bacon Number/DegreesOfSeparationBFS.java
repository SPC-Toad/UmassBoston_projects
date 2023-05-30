package pa2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.BreadthFirstPaths;


/**
 * The {@code DegreesOfSeparation} class provides a client for finding
 * the degree of separation between one distinguished individual and
 * every other individual in a social network.
 * As an example, if the social network consists of actors in which
 * two actors are connected by a link if they appeared in the same movie,
 * and Kevin Bacon is the distinguished individual, then the client
 * computes the Kevin Bacon number of every actor in the network.
 * <p>
 * The running time is proportional to the number of individuals and
 * connections in the network. If the connections are given implicitly,
 * as in the movie network example (where every two actors are connected
 * if they appear in the same movie), the efficiency of the algorithm
 * is improved by allowing both movie and actor vertices and connecting
 * each movie to all of the actors that appear in that movie.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class DegreesOfSeparationBFS {
    private SymbolGraph sg;
    private BreadthFirstPaths bfs;

    // this class cannot be instantiated
    public DegreesOfSeparationBFS(String fname, String delimiter, String source) {
        sg = new SymbolGraph(fname, delimiter);
        bfs = new BreadthFirstPaths(sg.graph(), sg.indexOf(source));
    }


    // Read actor's name from standard input, print bacon relationship
    public int baconNumber(String sink) {
        if (sg.contains(sink)) {
            return bfs.distTo(sg.indexOf(sink)) / 2;
        }
        return -1;
    }

    // Calculate the path itself.
    public Stack<Integer> graphPath(String sink) {
        Stack<Integer> path = new Stack<Integer>();
        Iterable<Integer> p = bfs.pathTo(sg.indexOf(sink));

        for (Integer x : p) {
            path.push(x);
        }
        return path;
    }

    public SymbolGraph getSymbolGraph() {
        return sg;
    }

    public BreadthFirstPaths getBreadthFirstPaths() {
        return bfs;
    }

    public void printPath(Stack<Integer> path) {
        // Now print. Every other vertex is an actor
    }


    /*
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];

        DegreesOfSeparationBFS baconGraph = new DegreesOfSeparationBFS(filename, delimiter, source);

        System.out.println(baconGraph.baconNumber("Holt, Jim (I)")/2);

        System.out.println(baconGraph.graphPath("Holt, Jim (I)"));

        // Print the Bacon diagram
        //baconGraph.printBaconDiagram();
        int i, no_args = args.length;
        // Get degrees of separation
        for (i = 3; i < no_args; i++) {
            baconGraph.baconNumber(args[i]);
            Stack<Integer> path = baconGraph.graphPath(args[i]);
            baconGraph.printPath(path);
        }
    }
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

