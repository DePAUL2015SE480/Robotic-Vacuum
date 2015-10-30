package depaul.edu.robotic.vacuum.navigation;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;
import depaul.edu.robotic.vacuum.display.ApplicationPanel;
import depaul.edu.robotic.vacuum.display.DataPanel;
import depaul.edu.robotic.vacuum.power.management.BatteryException;
import depaul.edu.robotic.vacuum.power.management.BatteryManager;

public class NavigationSystem {

	private static Boolean moveR = true;
	
	public static void navigateBot() {
		//this is only test data
		if (moveR) {
			BotMovement.getInstance().moveEast();
		}
		
		if (BotMovement.getInstance().getCurrentXLocation() >= (ApplicationPanel.SCREEN_SIZE_X - BoundingBoxManager
				.getInstance().getBoundingBox(BoundingBoxName.CLEANING_BOT)
				.getRectangleObjectUsedToDrawBoundingBox().getWidth()-10)) {
			moveR = false;
			BotMovement.getInstance().moveSouth();
		}
		
		if (BotMovement.getInstance().getCurrentYLocation() >= (ApplicationPanel.SCREEN_SIZE_Y-124)) {
			BotMovement.getInstance().moveWest();
		}		
		
		if (BotMovement.getInstance().getCurrentXLocation() <= 10) {
			BotMovement.getInstance().moveNorth();
		}
		
		if (BotMovement.getInstance().getCurrentYLocation() <= 10) {
			BotMovement.getInstance().moveEast();
		}	
		
		//Test for correct rooms
		for(BoundingBoxName d : BoundingBoxName.values()) {
			if(d.ordinal() == 0) continue;
				if(BoundingBoxManager.getInstance()
						.getBoundingBox(d).getRectangleObjectUsedToDrawBoundingBox()//check for room
						.intersects(BoundingBoxManager.getInstance().getBoundingBox(BoundingBoxName.CLEANING_BOT) //room intersects with bot
						.getRectangleObjectUsedToDrawBoundingBox())) {
				DataPanel.print("I'm in the " + d + "!!");
				//DataPanel.print("I'm on " + boxManager.getFloor().getFloorType() + " Floor Type.");
				//DataPanel.print("I have " + battery.getBatteryLevel() + " battery remaining!");
			}
		}
		//here only to check bot location
		DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
		BotMovement.getInstance().getCurrentYLocation() + ")");
	}
}
