package depaul.edu.robotic.vacuum.navigation;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

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
 */
public class BotMovement {
	private static BotMovement instance;
	private final int VELOCITY_UNITS = 10; //change to one for 1 unit
	private final int SLEEP_TIME_IN_SECONDS = 2;
	
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
	
	/*
	 * This method will move the robot one unit
	 * to the left. (x - 1, y)
	 */
	public void moveLeft() {
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().x-=VELOCITY_UNITS;
		sleep(SLEEP_TIME_IN_SECONDS);
	}
	
	/**
	 *  This method will move the robot one unit
	 * to the right. (x + 1, y)
	 */
	public void moveRight() {
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().x+=VELOCITY_UNITS;
		sleep(SLEEP_TIME_IN_SECONDS);
	}
	
	/**
	 * * This method will move the robot one unit
	 * to the right. (x, y - 1)
	 */
	public void moveUp() {
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().y-=VELOCITY_UNITS;
		sleep(SLEEP_TIME_IN_SECONDS);
	}
	
	/**
	 * * This method will move the robot one unit
	 * to the right. (x, y + 1)
	 */
	public void moveDown() {
		BoundingBoxManager.getInstance()
			.getBoundingBox(BoundingBoxName.CLEANING_BOT)
			.getRectangleObjectUsedToDrawBoundingBox().y+=VELOCITY_UNITS;
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
}
