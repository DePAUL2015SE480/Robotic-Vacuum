package depaul.edu.robotic.vacuum.navigation;


public class Edge implements Comparable<Edge> { 
    private final Floor v;
    private final Floor w;
    private final double weight;

    /**
     * Initializes an edge between vertices <tt>v</tt> and <tt>w</tt> of
     * the given <tt>weight</tt>.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  weight the weight of this edge
     * @throws IndexOutOfBoundsException if either <tt>v</tt> or <tt>w</tt> 
     *         is a negative integer
     * @throws IllegalArgumentException if <tt>weight</tt> is <tt>NaN</tt>
     */
    public Edge(Floor v, Floor w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public Floor either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public Floor other(Floor vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
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
        if      (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return  0;
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return ("Edges: " + v.getVertex().toString() + " " + v.getFloorType()  + " connected to " + w.getVertex().toString() + " " + w.getFloorType() +
        		"\n" + " Weight: " + this.weight()) ;
    }
}