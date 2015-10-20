package depaul.edu.robotic.vacuum.floorPlan;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

public interface Room {
	public BoundingBoxName getType();
	public void setDimension(int width, int height);
	public void setOrigin(int x, int y);
	public int getWidth();
	public int getHeight();
	public int getOriginX();
	public int getOriginY();
}
