package depaul.edu.robotic.vacuum.navigation;

/******************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  A generic bag or multiset, implemented using a singly-linked list.
 *
 *  % more tobe.txt 
 *  to be or not to - be - - that - - - is
 *
 *  % java Bag < tobe.txt
 *  size of bag = 14
 *  is
 *  -
 *  -
 *  -
 *  that
 *  -
 *  -
 *  be
 *  -
 *  to
 *  not
 *  or
 *  be
 *  to
 *
 *Copyright © 2002–2015, Robert Sedgewick and Kevin Wayne.
Last updated: Fri Oct 16 05:00:20 EDT 2015.

 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The <tt>Bag</tt> class represents a bag (or multiset) of 
 *  generic items. It supports insertion and iterating over the 
 *  items in arbitrary order.
 *  <p>
 *  This implementation uses a singly-linked list with a static nested class Node.
 *  See {@link LinkedBag} for the version from the
 *  textbook that uses a non-static nested class.
 *  The <em>add</em>, <em>isEmpty</em>, and <em>size</em> operations
 *  take constant time. Iteration takes time proportional to the number of items.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this bag
 */
public class Bag<Floor> implements Iterable<Floor> {
    private Node<Floor> first;    // beginning of bag
    private int N;               // number of elements in bag

    // helper linked list class
    private static class Node<Floor> {
        private Floor item;
        private Node<Floor> next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        N = 0;
    }
    
    public Bag(Floor v){
    	first = null;
    	this.add(v);
    }
    
    

    /**
     * Returns true if this bag is empty.
     *
     * @return <tt>true</tt> if this bag is empty;
     *         <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return N;
    }

    /**
     * Adds the item to this bag.
     *
     * @param  item the item to add to this bag
     */
    public void add(Floor v) {
    	for (Floor f : this){
    		if (v == f) return;
    	}
        Node<Floor> oldfirst = first;
        first = new Node<Floor>();
        first.item = v;
        first.next = oldfirst;
        N++;
    }


    /**
     * Returns an iterator that iterates over the items in this bag in arbitrary order.
     *
     * @return an iterator that iterates over the items in this bag in arbitrary order
     */
    public Iterator<Floor> iterator()  {
        return new ListIterator<Floor>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Floor> implements Iterator<Floor> {
        private Node<Floor> current;

        public ListIterator(Node<Floor> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Floor next() {
            if (!hasNext()) throw new NoSuchElementException();
            Floor floor = current.item;
            current = current.next; 
            return floor;
        }
    }
}
