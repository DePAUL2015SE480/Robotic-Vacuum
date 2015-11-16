package depaul.edu.robotic.vacuum.map;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ListIterator;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.navigation.Floor;

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
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	private ConcurrentHashMap <Floor, ArrayList<Edge>> adj = new ConcurrentHashMap<>();


	/**
	 * Singleton object creation
	 * @return Graph
	 */

	//CONSTRUCTOR
	public Graph() {
		Floor floor =  boxManager.getFloor();
		Floor v = floor;
	}
	
	public ConcurrentHashMap<Floor, ArrayList<Edge>> getMap(){
		return adj;
	}



	/**
	 * Returns the number of vertices (floor cells) in this graph.
	 * @return int
	 */
	public int totalCells() {
		return adj.size();
	}

	/**
	 * Adds an edge adjacent to the vacuums location
	 *
	 * 
	 */
	public void addEdge(Floor botFloor, Floor adjFloor) {
		//if (adjFloor.isObstacle()) return;
		if (!adj.containsKey(botFloor))
			adj.put(botFloor, new ArrayList<Edge>());
		if (!adj.containsKey(adjFloor))
			adj.put(adjFloor, new ArrayList<Edge>());
		ArrayList<Edge> tempList = adj.get(botFloor);
		double tempWeight = (botFloor.getFloorType().getValue() + adjFloor.getFloorType().getValue())/2.0;
		Edge tempEdge = new Edge (botFloor, adjFloor, tempWeight);
		tempList.add(tempEdge);
		tempList = adj.get(adjFloor);
		tempEdge = new Edge (adjFloor, botFloor, tempWeight);
		tempList.add(tempEdge);
	}


	/**
	 * Returns the vertices adjacent to vertex <tt>v</tt>.
	 *
	 * @param  v the vertex
	 * @return the vertices adjacent to vertex <tt>v</tt>, as an iterable
	 * @throws IndexOutOfBoundsException unless 0 <= v < V
	 */
	public Iterable<Edge> adj(Floor v) {
		return adj.get(v);
	}

	/**
	 * Returns the degree of vertex <tt>v</tt>.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex <tt>v</tt>
	 * @throws IndexOutOfBoundsException unless 0 <= v < V
	 */
	public int degree(Floor v) {
		ArrayList<Edge> tempList = adj.get(v);
		return tempList.size();
	}


	/**
	 * Returns a string representation of this graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
	 *         followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(adj.size() + " Floor cells"  + NEWLINE);
		for (Floor w : adj.keySet()){ 
			s.append("Current location: " + w.toString() + " " + NEWLINE);
			ArrayList<Edge> list = adj.get(w);
			Iterator it = list.iterator();
			int x = 1;
			s.append(w.getVertex()+ " has " + list.size() + " connected floor cells: " + NEWLINE);
			while (it.hasNext()){
				s.append(x + ": " + it.next() + NEWLINE);
				x++;
			}
		}
		s.append(NEWLINE);
		return s.toString();
	}
}
