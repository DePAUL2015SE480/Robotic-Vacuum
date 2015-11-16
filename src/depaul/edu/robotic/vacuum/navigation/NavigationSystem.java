package depaul.edu.robotic.vacuum.navigation;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxEdge;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;
import depaul.edu.robotic.vacuum.display.ApplicationPanel;
import depaul.edu.robotic.vacuum.display.DataPanel;
import depaul.edu.robotic.vacuum.power.management.BatteryException;
import depaul.edu.robotic.vacuum.power.management.BatteryManager;

public class NavigationSystem {
	private final static BatteryManager battery = BatteryManager.getInstance();
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	
	
	private static Boolean moveR = true;
	
	public static void navigateBot() {
		while(battery.getBatteryLevel() > 50){
			Floor currentFloor = boxManager.getFloor();
			
			//TODO VACUUM SENSOR NEEDS TO REMOVE DIRT FROM FLOOR VALUE
			int dirtValue = currentFloor.getDirtValue();
			//System.out.println("Dirt Levels are :" + dirtValue + " Floor Type: " + currentFloor.getFloorType());
			while (dirtValue > 0) {
				battery.batteryVacuum(currentFloor.getFloorType());
				dirtValue -= 1;
				//TODO Vacuum should update the floor.dirtValue to 0
			}
			
			Floor northFloor = boxManager.getFloor(BoundingBoxEdge.NORTH);
			Floor eastFloor = boxManager.getFloor(BoundingBoxEdge.EAST);
			Floor southFloor = boxManager.getFloor(BoundingBoxEdge.SOUTH);
			Floor westFloor = boxManager.getFloor(BoundingBoxEdge.WEST);
			
			if (eastFloor != null && eastFloor.getDirtValue() > 0){
				BotMovement.getInstance().moveEast();
			DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
					BotMovement.getInstance().getCurrentYLocation() + ")");
			}
			else if (southFloor != null && southFloor.getDirtValue() > 0){
				BotMovement.getInstance().moveSouth();
				DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
						BotMovement.getInstance().getCurrentYLocation() + ")");
				}
			else if (northFloor != null && northFloor.getDirtValue() > 0){
			BotMovement.getInstance().moveNorth();
			DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
					BotMovement.getInstance().getCurrentYLocation() + ")");
			}
			else if (westFloor != null && westFloor.getDirtValue() > 0){
				BotMovement.getInstance().moveWest();
				DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
						BotMovement.getInstance().getCurrentYLocation() + ")");
			}
			//TODO This is where the graph should be access to determine where to vacuum next
			else System.out.println("THIS IS THE END OF THE LINE");
		}
		
		
		
		//this is only test data
//		if (moveR) {
//			BotMovement.getInstance().moveEast();
//		}
//		
//		if (BotMovement.getInstance().getCurrentXLocation() >= (ApplicationPanel.SCREEN_SIZE_X - BoundingBoxManager
//				.getInstance().getBoundingBox(BoundingBoxName.CLEANING_BOT)
//				.getRectangleObjectUsedToDrawBoundingBox().getWidth()-10)) {
//			moveR = false;
//			BotMovement.getInstance().moveSouth();
//		}
//		
//		if (BotMovement.getInstance().getCurrentYLocation() >= (ApplicationPanel.SCREEN_SIZE_Y-124)) {
//			BotMovement.getInstance().moveWest();
//		}		
//		
//		if (BotMovement.getInstance().getCurrentXLocation() <= 10) {
//			BotMovement.getInstance().moveNorth();
//		}
//		
//		if (BotMovement.getInstance().getCurrentYLocation() <= 10) {
//			BotMovement.getInstance().moveEast();
//		}	
//		
//		//Test for correct rooms
//		for(BoundingBoxName d : BoundingBoxName.values()) {
//			if(d.ordinal() == 0) continue;
//				if(BoundingBoxManager.getInstance()
//						.getBoundingBox(d).getRectangleObjectUsedToDrawBoundingBox()//check for room
//						.intersects(BoundingBoxManager.getInstance().getBoundingBox(BoundingBoxName.CLEANING_BOT) //room intersects with bot
//						.getRectangleObjectUsedToDrawBoundingBox())) {
//				//DataPanel.print("I'm in the " + d + "!!");
//				//System.out.println("I'm on " + boxManager.getFloor().getFloorType() + " Floor Type.");
//				//DataPanel.print("I have " + battery.getBatteryLevel() + " battery remaining!");
//			}
//		}
		//here only to check bot location
		//DataPanel.print("Bot Location: (" + BotMovement.getInstance().getCurrentXLocation() + ", " +
		//BotMovement.getInstance().getCurrentYLocation() + ")");
	}
}
