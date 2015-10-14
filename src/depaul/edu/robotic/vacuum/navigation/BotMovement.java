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
 * this class is not complete
 *
 */
public class BotMovement {
	
	public static Boolean move = true;
	
	/*
	 * This method will move the robot one unit
	 * to the left. (x - 1, y)
	 * @param canMove allows bot to move in this 
	 * direction if true
	 * @return x-axis locations
	 */
	public static int moveLeft() {
		if(move) {
			return BoundingBoxManager.getInstance()
				.getBoundingBox(BoundingBoxName.CLEANING_BOT)
				.getRectangleObjectUsedToDrawBoundingBox().x--;
		}
		else
			return 0;
	}
	
	/**
	 *  This method will move the robot one unit
	 * to the right. (x + 1, y)
	 * @param canMove allows bot to move in this 
	 * direction if true
	 * @return x-axis location
	 */
	public static int moveRight() {
		if(move) {
			return BoundingBoxManager.getInstance()
					.getBoundingBox(BoundingBoxName.CLEANING_BOT)
					.getRectangleObjectUsedToDrawBoundingBox().x++;
		}
		else 
			return 0;
	}
	
	/**
	 * * This method will move the robot one unit
	 * to the right. (x, y - 1)
	 * @param canMove allows bot to move in this 
	 * direction if true
	 * @return y-axis
	 */
	public static int moveUp() {
		if(move) {
			return BoundingBoxManager.getInstance()
					.getBoundingBox(BoundingBoxName.CLEANING_BOT)
					.getRectangleObjectUsedToDrawBoundingBox().y--;
		}
		else 
			return 0;
	}
	
	/**
	 * * This method will move the robot one unit
	 * to the right. (x, y + 1)
	 * @param canMove allows bot to move in this 
	 * direction if true
	 * @return y-axis
	 */
	public static int moveDown() {
		if(move) {
			return BoundingBoxManager.getInstance()
					.getBoundingBox(BoundingBoxName.CLEANING_BOT)
					.getRectangleObjectUsedToDrawBoundingBox().y++;
		}
		else 
			return 0;
	}
}
