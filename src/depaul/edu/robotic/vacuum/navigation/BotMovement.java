package depaul.edu.robotic.vacuum.navigation;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxEdge;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;
import depaul.edu.robotic.vacuum.display.DataPanel;
import depaul.edu.robotic.vacuum.map.Graph;
import depaul.edu.robotic.vacuum.power.management.BatteryException;
import depaul.edu.robotic.vacuum.power.management.BatteryManager;

/**
 * 
 * @author Deonte Johnson
 * Date: 10/13/15
 * 
 * This class will depend on the BoundingBoxManager 
 * for movement of the robot. The BoundingBoxManager
 * has a method that will allow the robot to be 
 * chosen from the list of boxes. The rectangle 
 * associated with each box can be chosen and will be
 * used for navigation along the x/y-axis one unit
 * according to requirements
 * 
 * class is almost complete, but still needs to be 
 * modified.
 *
 *@author Briant Becote
 *added Battery functionality
 *added Graph functionality
 */
public class BotMovement {
	private static BotMovement instance;
	private final int VELOCITY_UNITS = 20; //change to one for 1 unit
	private final int SLEEP_TIME_IN_SECONDS = 5;
	private final static BatteryManager battery = BatteryManager.getInstance();
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	private static Graph map = Graph.getInstance();
	
	/**
	 * Updates the Graph representation of available cells to move to
	 */
	private void updateMap(){
		Floor floor = boxManager.getFloor();
		for(BoundingBoxEdge direction : BoundingBoxEdge.values()){
			Floor floorAdj = boxManager.getFloor(direction);
			map.addEdge(floor, floorAdj);
		}
	}
	
	
	
	private void sleep(int sleepTimeInSeconds) {
		try {
			Thread.sleep(sleepTimeInSeconds*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return instance of this class
	 */
	public static BotMovement getInstance() {
		if(instance == null)
			instance = new BotMovement();
		return instance;
	}
	
	/**
	 * This method will move the robot one unit
	 * to the left. (x - 1, y)
	 * @throws BatteryException 
	 */
	public void moveWest() {
		updateMap();
		battery.batteryTravel(boxManager.getFloor().getFloorType(), boxManager.getFloor(BoundingBoxEdge.WEST).getFloorType());
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().x-=VELOCITY_UNITS;
		sleep(SLEEP_TIME_IN_SECONDS);
	}
	
	/**
	 *  This method will move the robot one unit
	 * to the right. (x + 1, y)
	 * @throws BatteryException 
	 */
	public void moveEast() {
		
		//Call to a method in this class - updates the graph in every direction
		updateMap();
		
		//Grabs floor info on current floor cell and floor cell to the east
		Floor currentFloor = boxManager.getFloor();
		Floor nextFloor = boxManager.getFloor(BoundingBoxEdge.EAST);
		System.out.println("Moving from " + currentFloor.getVertex().toString() + " to " + nextFloor.getVertex().toString());
		
		//updates the battery
	battery.batteryTravel(boxManager.getFloor().getFloorType(), boxManager.getFloor(BoundingBoxEdge.EAST).getFloorType());
		
		//moves the vacuum
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().x+=VELOCITY_UNITS;
		
		sleep(SLEEP_TIME_IN_SECONDS);
		//System.out.println(map.toString()); //KEEP for Troubleshooting, Map currently working correctly
	}
	
	/**
	 * * This method will move the robot one unit
	 * to the right. (x, y - 1)
	 * @throws BatteryException 
	 */
	public void moveNorth(){
		battery.batteryTravel(boxManager.getFloor().getFloorType(), boxManager.getFloor(BoundingBoxEdge.NORTH).getFloorType());
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().y+=VELOCITY_UNITS;
		sleep(SLEEP_TIME_IN_SECONDS);
	}
	
	/**
	 * * This method will move the robot one unit
	 * to the right. (x, y + 1)
	 * @throws BatteryException 
	 */
	public void moveSouth() {
		battery.batteryTravel(boxManager.getFloor().getFloorType(), boxManager.getFloor(BoundingBoxEdge.SOUTH).getFloorType());
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().y-=VELOCITY_UNITS;
		sleep(SLEEP_TIME_IN_SECONDS);
	}
	
	/**
	 * 
	 * @return current x-axis location
	 */
	public int getCurrentXLocation() {
		return BoundingBoxManager.getInstance()
				.getBoundingBox(BoundingBoxName.CLEANING_BOT)
				.getRectangleObjectUsedToDrawBoundingBox().x;
	}
	
	/**
	 * 
	 * @return current y-axis location
	 */
	public int getCurrentYLocation() {
		return BoundingBoxManager.getInstance()
				.getBoundingBox(BoundingBoxName.CLEANING_BOT)
				.getRectangleObjectUsedToDrawBoundingBox().y;
	}
	
	public int getVelocity(){
		return this.VELOCITY_UNITS;
	}
	
	
}
