package depaul.edu.robotic.vacuum.bounding.box;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * 
 * @author Deonte Johnson
 *
 */
public interface Box {
	public void setColor(Color _color);
	public BoundingBoxName getNameGivenToBoundingBox();
	public Rectangle getRectangleObjectUsedToDrawBoundingBox();
}

