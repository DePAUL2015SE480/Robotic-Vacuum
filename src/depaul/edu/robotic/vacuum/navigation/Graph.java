package depaul.edu.robotic.vacuum.navigation;

import java.util.Stack;

/******************************************************************************
 *  Compilation:  javac Graph.java        
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41graph/tinyG.txt
 *	@author Robert Sedgewick and Kevin Wayne.
 *	Last updated: Wed Aug 26 07:33:08 EDT 2015.
 *
 *
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges 
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges 
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15 
 *  1: 220 203 200 194 189 164 150 130 107 72 
 *  2: 141 110 108 86 79 51 42 18 14 
 *  ...
 *  
 ******************************************************************************/


/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  named 0 through <em>V</em> - 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em>. 
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent to a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  
 *  @author Briant Becote
 *  Updated for use with the Clean Sweep program to utilize Floor object inputs.
 */

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private int floorCount;
    private Bag<Integer>[] adj;
    private static Graph instance;
    
    //CONSTRUCTOR
    private Graph() {
        this.floorCount = 1;
        Floor floor =  new Floor(1);
        this.adj = (Bag<Integer>[]) new Bag[floor.getVertex()];
            adj[floor.getVertex()] = new Bag<Integer>();
    }
    
    /**
     * Singleton object creation
     * @return Graph
     */
    public Graph getInstance(){
    	if (instance == null) instance = new Graph() ;
    	return instance;
    }
    
    

    /**
     * Returns the number of vertices (floor cells) in this graph.
     * @return int
     */
    public int totalCells() {
        return floorCount;
    }

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int v) {
        if (v < 0 || v >= floorCount)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (floorCount-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(Floor v, Floor w) {
        Integer vVertex = v.getVertex();
        Integer wVertex = w.getVertex();
    	validateVertex(vVertex);
        validateVertex(wVertex);
        adj[vVertex].add(wVertex);
        adj[wVertex].add(vVertex);
    }


    /**
     * Returns the vertices adjacent to vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex <tt>v</tt>, as an iterable
     * @throws IndexOutOfBoundsException unless 0 <= v < V
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the degree of vertex <tt>v</tt>
     * @throws IndexOutOfBoundsException unless 0 <= v < V
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        //s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < floorCount; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
