package depaul.edu.robotic.vacuum.bounding.box;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * 
 * @author Deonte Johnson
 *
 */
public interface Box {
	public void setColor(Color rectangleColor);
	public BoundingBoxName getNameGivenToBoundingBox();
	public Rectangle getRectangleObjectUsedToDrawBoundingBox();
	public HashMap<BoundingBoxEdge, Boolean> getEdges();
	public void setEdge(BoundingBoxEdge edge, Boolean hasObstacle);
}

