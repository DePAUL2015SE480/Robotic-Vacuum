package depaul.edu.robotic.vacuum.map;

import depaul.edu.robotic.vacuum.navigation.Floor;

public class Edge implements Comparable<Edge> { 
    private final Floor thisFloor;
    private final Floor adjFloor;
    private final double weight;

    /**
     * Initializes an edge between vertices <tt>v</tt> and <tt>w</tt> of
     * the given <tt>weight</tt>.
     *
     * @param  v Current Floor
     * @param  w Adjacent Floor vertex
     * @param  weight the weight of this edge
     * @throws IndexOutOfBoundsException if either <tt>v</tt> or <tt>w</tt> 
     *         is a negative integer
     * @throws IllegalArgumentException if <tt>weight</tt> is <tt>NaN</tt>
     */
    public Edge(Floor v, Floor w, double weight) {
        this.thisFloor = v;
        this.adjFloor = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public Floor adjFloor() {
        return adjFloor;
    }


    /**
     * Compares two edges by weight.
     * Note that <tt>compareTo()</tt> is not consistent with <tt>equals()</tt>,
     * which uses the reference equality implementation inherited from <tt>Object</tt>.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(Edge that) {
        if      (this.getWeight() < that.getWeight()) return -1;
        else if (this.getWeight() > that.getWeight()) return +1;
        else                                    return  0;
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return ("Edges: " + thisFloor.getVertex().toString() + " " + thisFloor.getFloorType()  + " connected to " + adjFloor.getVertex().toString() + " " + adjFloor.getFloorType() +
        		"\n" + " Weight: " + this.getWeight()) ;
    }
}