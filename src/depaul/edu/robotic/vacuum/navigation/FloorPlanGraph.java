package depaul.edu.robotic.vacuum.navigation;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.stream.Collectors;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBox;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

public class FloorPlanGraph {
	private static final int BOX_WIDTH = 20;
	private static final int BOX_HEIGHT = 20;
	private static FloorPlanGraph instance;
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	private final static Graph graph = new Graph();
	
	
	public static FloorPlanGraph getInstance(){
		if (instance == null) instance = new FloorPlanGraph();
		return instance;
	}
	
	private FloorPlanGraph() {
		this.generateFloorGraph();
	}
	
	/**
	 * Loops through all the bounding boxes established in the BoundingBoxManager collection and creates the proper edges based on BoxName or if it's adjacent
	 * 
	 */
	private void generateFloorGraph() {
		ArrayList<BoundingBox> boxCollection = boxManager.getCollectionOfBoundingBoxes();
		for (int i = 0; i < boxCollection.size() - 1; i++) {
			BoundingBox b1 = boxCollection.get(i);
			BoundingBox b2 = boxCollection.get(i + 1);
			
			// make sure we're not accidentally getting the cleaning bot
			if (b1.getNameGivenToBoundingBox() != BoundingBoxName.CLEANING_BOT && b2.getNameGivenToBoundingBox() != BoundingBoxName.CLEANING_BOT) {
				Floor f1 = b1.getFloor();
				Floor f2 = b2.getFloor();
				
				// if the 2 boxes are right next to each other (ex: (0,1) and (0,2))
				if ((b1.getRectangleObjectUsedToDrawBoundingBox().y == b2.getRectangleObjectUsedToDrawBoundingBox().y) && (b1.getNameGivenToBoundingBox() == b2.getNameGivenToBoundingBox() || f1.getAccessPoints().contains(b2.getNameGivenToBoundingBox()))) {					
					graph.addEdge(f1, f2);
					
				// else if the next box in the list is on the next row in the grid (ex: (0,3) and (1,0)
				} else if (f1.getVertex().getY() < f2.getVertex().getY()) {
					// this looks nuts but basically it's saying: Since it's a new row in the grid, get the bounding box with the same x value and that's right above it.
					BoundingBox upperBox = boxCollection.stream().filter(b -> b.getFloor().getVertex().getX() == b1.getFloor().getVertex().getX() && b.getFloor().getVertex().getY() == b1.getFloor().getVertex().getY() + BOX_HEIGHT).collect(Collectors.toList()).get(0);
					
					// if they're the same type or has an access point, create an edge!
					if (b1.getNameGivenToBoundingBox() == upperBox.getNameGivenToBoundingBox() || f1.getAccessPoints().contains(upperBox.getNameGivenToBoundingBox())) {
						graph.addEdge(f1, upperBox.getFloor());
					}
				}
			}
			
		}
	}
}
