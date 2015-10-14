package depaul.edu.robotic.vacuum.floorPlan;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxEdge;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

/**
 * Establishes the floor grid out of BoundingBoxes by using the
 * BoundingBoxManager
 * 
 * @author rodri_000
 *
 */
public class FloorGrid {

	private int width;
	private int height;
	private static FloorGrid instance;

	private FloorGrid() {
		instance = null;
	}

	public static FloorGrid getInstance() {
		if (instance == null)
			instance = new FloorGrid();
		return instance;
	}

	/**
	 * Establishes the grid based on the max width and height
	 * 
	 * @param width
	 * @param height
	 */
	public void establishGrid(int width, int height) {
		this.width = width;
		this.height = height;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.addBoundingBox(i, j);
			}
		}
	}

	/**
	 * Adds a bounding box to the grid. This adds obstacles to the sides of the
	 * boxes depending if its a corner piece or an edge piece. Can be updated
	 * for different scenarios
	 * 
	 * @param x
	 * @param y
	 */
	private void addBoundingBox(int x, int y) {
		if (x == 0 && y == 0) { // Top left corner
			this.createBox(
					Arrays.asList(BoundingBoxEdge.LEFT, BoundingBoxEdge.TOP),
					x, y);
		} else if (x == this.width && y == 0) { // Top right corner
			this.createBox(
					Arrays.asList(BoundingBoxEdge.TOP, BoundingBoxEdge.RIGHT),
					x, y);
		} else if (x == 0 && y == this.height) { // Bottom left corner
			this.createBox(
					Arrays.asList(BoundingBoxEdge.LEFT, BoundingBoxEdge.BOTTOM),
					x, y);
		} else if (x == this.width && y == this.height) { // Bottom right corner
			this.createBox(Arrays.asList(BoundingBoxEdge.BOTTOM,
					BoundingBoxEdge.RIGHT), x, y);
		} else if (x == 0 || x == this.width) { // Top or bottom sides
			BoundingBoxEdge edge = x == 0 ? BoundingBoxEdge.TOP
					: BoundingBoxEdge.BOTTOM;
			this.createBox(Arrays.asList(edge), x, y);
		} else if (y == 0 || y == this.height) { // Left or right sides
			BoundingBoxEdge edge = y == 0 ? BoundingBoxEdge.LEFT
					: BoundingBoxEdge.RIGHT;
			this.createBox(Arrays.asList(edge), x, y);
		} else {
			this.createBox(Arrays.asList(), x, y);
		}
	}

	/**
	 * Creates a box based on the coordinates and edges passed in and adds it to
	 * the BoundingBoxManager array
	 * 
	 * @param boundingBoxEdges
	 * @param x
	 * @param y
	 */
	private void createBox(List<BoundingBoxEdge> boundingBoxEdges, int x, int y) {
		HashMap<BoundingBoxEdge, Boolean> edges = new HashMap<BoundingBoxEdge, Boolean>();
		for (BoundingBoxEdge edge : BoundingBoxEdge.values()) {
			if (boundingBoxEdges.contains(edge))
				edges.put(edge, true);
			else
				edges.put(edge, false);
		}

		BoundingBoxManager.getInstance().createAndAddBoundingBoxToCollection(
				BoundingBoxName.NOT_INITIALIZED,
				new Rectangle(50 * x, 50 * y, 50, 50), Color.blue, edges, x, y);

	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
