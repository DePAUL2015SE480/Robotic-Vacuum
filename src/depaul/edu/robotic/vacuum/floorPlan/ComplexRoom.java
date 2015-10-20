package depaul.edu.robotic.vacuum.floorPlan;

import java.util.ArrayList;
import java.util.List;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

/**
 * Composite of the Room class. Contains a list of rooms
 * @author rodri_000
 *
 */
public class ComplexRoom implements Room {
	
	private List<Room> listOfRooms;
	private int x, y, width, height;
	
	public ComplexRoom() {
		this.listOfRooms = new ArrayList<Room>();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	@Override
	public BoundingBoxName getType() {
		return BoundingBoxName.NOT_INITIALIZED;
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
	
	public void addRoomToList(Room room) {
		listOfRooms.add(room);
	}
	
	public List<Room> getRoomList() {
		return this.listOfRooms;
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
