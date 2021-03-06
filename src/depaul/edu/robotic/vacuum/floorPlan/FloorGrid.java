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
 * Connects the Floorgrid to the UI
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
				this.createBox(i, j);
			}
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
	private void createBox(int x, int y) {
		BoundingBoxName floorName = BoundingBoxName.NOT_INITIALIZED;

		for (Room room : this.floorPlan.getRoomList()) {
			if (x >= room.getOriginX() && x <= room.getWidth()
					&& y >= room.getOriginY() && y <= room.getHeight())
				floorName = room.getType();
		}

		BoundingBoxManager.getInstance().createAndAddBoundingBoxToCollection(
				floorName, new Rectangle(20 * x, 20 * y, 20, 20),
				getColor(floorName), x, y);

	}

	private Color getColor(BoundingBoxName floorName) {
		switch (floorName) {
		case BATHROOM:
			return null;
			//return Color.BLUE;
		case CLOSET:
			return null;
			//return Color.GREEN;
		case GUEST_BEDROOM:
			return null;
			//return Color.CYAN;
		case HALLWAY:
			return null;
			//return Color.BLACK;
		case MASTER_BEDROOM:
			return null;
			//return Color.YELLOW;
		case STAIRS:
			return null;
			//return Color.ORANGE;
		default:
			return null;
			//return Color.WHITE;
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
