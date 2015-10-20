package depaul.edu.robotic.vacuum.floorPlan;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

/**
 * Basic room class that handles simple rooms like Bedroom, Closet, etc..
 * 
 * @author rodri_000
 *
 */
public class SimpleRoom implements Room {

	private BoundingBoxName type;
	private int x, y, width, height;

	public SimpleRoom(BoundingBoxName name) {
		this.type = name;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	@Override
	public BoundingBoxName getType() {
		return this.type;
	}

	@Override
	public void setDimension(int width, int height) {
		this.width = width + x;
		this.height = height + y;
	}

	@Override
	public void setOrigin(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getOriginX() {
		return this.x;
	}

	@Override
	public int getOriginY() {
		return this.y;
	}

}
