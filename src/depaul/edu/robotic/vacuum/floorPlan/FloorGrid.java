package depaul.edu.robotic.vacuum.floorPlan;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxEdge;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;
import depaul.edu.robotic.vacuum.navigation.*;

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
	private FloorPlanManager fpm;
	private ComplexRoom floorPlan;

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
	public void establishGrid() {
		this.fpm = FloorPlanManager.getInstance();
		this.floorPlan = fpm.createFloorPlan();
		this.width = fpm.getFloorPlanWidth();
		this.height = fpm.getFloorPlanHeight();

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
		List<BoundingBoxEdge> edgeList = new ArrayList<BoundingBoxEdge>();
		if (x == 0 && y == 0) { // Top left corner
			edgeList.add(BoundingBoxEdge.LEFT);
			edgeList.add(BoundingBoxEdge.TOP);
		} else if (x == this.width && y == 0) { // Top right corner
			edgeList.add(BoundingBoxEdge.TOP);
			edgeList.add(BoundingBoxEdge.RIGHT);
		} else if (x == 0 && y == this.height) { // Bottom left corner
			edgeList.add(BoundingBoxEdge.LEFT);
			edgeList.add(BoundingBoxEdge.BOTTOM);
		} else if (x == this.width && y == this.height) { // Bottom right corner
			edgeList.add(BoundingBoxEdge.BOTTOM);
			edgeList.add(BoundingBoxEdge.RIGHT);
		} else if (x == 0 || x == this.width) { // Top or bottom sides
			BoundingBoxEdge edge = x == 0 ? BoundingBoxEdge.TOP
					: BoundingBoxEdge.BOTTOM;
			edgeList.add(edge);
		} else if (y == 0 || y == this.height) { // Left or right sides
			BoundingBoxEdge edge = y == 0 ? BoundingBoxEdge.LEFT
					: BoundingBoxEdge.RIGHT;
			edgeList.add(edge);
		} else {
			this.createBox(Arrays.asList(), x, y);
		}

		this.createBox(edgeList, x, y);
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
		BoundingBoxName floorName = BoundingBoxName.NOT_INITIALIZED;
		for (BoundingBoxEdge edge : BoundingBoxEdge.values()) {
			if (boundingBoxEdges.contains(edge))
				edges.put(edge, true);
			else
				edges.put(edge, false);
		}

		for (Room room : this.floorPlan.getRoomList()) {
			if (x >= room.getOriginX() && x <= room.getWidth()
					&& y >= room.getOriginY() && y <= room.getHeight())
				floorName = room.getType();
		}

		BoundingBoxManager.getInstance().createAndAddBoundingBoxToCollection(
				floorName, new Rectangle(50 * x, 50 * y, 50, 50),
				getColor(floorName), edges, x, y);

	}

	private Color getColor(BoundingBoxName floorName) {
		switch (floorName) {
		case BATHROOM:
			return Color.BLUE;
		case CLOSET:
			return Color.GREEN;
		case GUEST_BEDROOM:
			return Color.CYAN;
		case HALLWAY:
			return Color.BLACK;
		case MASTER_BEDROOM:
			return Color.YELLOW;
		case STAIRS:
			return Color.ORANGE;
		default:
			return Color.WHITE;
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
