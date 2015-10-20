package depaul.edu.robotic.vacuum.navigation;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;
import depaul.edu.robotic.vacuum.display.ApplicationPanel;
import depaul.edu.robotic.vacuum.display.DataPanel;

public class NavigationSystem {

	private static Boolean moveR = true;
	
	public static void navigateBot() {
		//this is only test data
		if (moveR) {
			BotMovement.getInstance().moveRight();
		}
		
		if (BotMovement.getInstance().getCurrentXLocation() >= (ApplicationPanel.SCREEN_SIZE_X - BoundingBoxManager
				.getInstance().getBoundingBox(BoundingBoxName.CLEANING_BOT)
				.getRectangleObjectUsedToDrawBoundingBox().getWidth()-10)) {
			moveR = false;
			BotMovement.getInstance().moveDown();
		}
		
		if (BotMovement.getInstance().getCurrentYLocation() >= (ApplicationPanel.SCREEN_SIZE_Y-124)) {
			BotMovement.getInstance().moveLeft();
		}		
		
		if (BotMovement.getInstance().getCurrentXLocation() <= 10) {
			BotMovement.getInstance().moveUp();
		}
		
		if (BotMovement.getInstance().getCurrentYLocation() <= 10) {
			BotMovement.getInstance().moveRight();
		}	
		
		//here only to check bot location
		DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
		BotMovement.getInstance().getCurrentYLocation() + ")");
	}
}
