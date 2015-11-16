package depaul.edu.robotic.vacuum.bounding.box;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import depaul.edu.robotic.vacuum.navigation.BotMovement;
import depaul.edu.robotic.vacuum.navigation.Floor;

/**
 * 
 * @author Deonte Johnson
 * 
 * This class will manage all bounding box in the 
 * application
 *
 */
public class BoundingBoxManager {

	private List<BoundingBox> collectionOfBoundingBoxes;
	private BoundingBox[][] boxArray;
	private static BoundingBoxManager instance;

	private BoundingBoxManager() {
		collectionOfBoundingBoxes = 
				new ArrayList<BoundingBox>();
		//TODO: Get rid of magic numbers here
		boxArray = new BoundingBox[10][10];
		instance = null;
	} 

	/**
	 * 
	 * @return a single instance of this object
	 */
	public static BoundingBoxManager getInstance() {
		if(instance == null)
			instance = new BoundingBoxManager();
		return instance;
	}

	/**
	 * 
	 * @param nameOfBoundingBox of the bounding box being created
	 * @param rectangle being used to create the box 
	 * @param rectangleColor of bounding box
	 * @param edges of the bounding box and whether they're obstacles or not
	 * @param x coordinate
	 * @param y coordinate
	 * 
	 */
	public void createAndAddBoundingBoxToCollection(BoundingBoxName nameOfBoundingBox, 
			Rectangle rectangle, Color rectangleColor, int x, int y) {
		BoundingBox boundingBox = new BoundingBox(nameOfBoundingBox, rectangle, rectangleColor);
		if (nameOfBoundingBox != BoundingBoxName.CLEANING_BOT)
			this.boxArray[x][y] = boundingBox;
		this.collectionOfBoundingBoxes.add(boundingBox);
		//for (BoundingBox z : collectionOfBoundingBoxes){
		//System.out.println("Floor Name is: " + z.getNameGivenToBoundingBox());
		//System.out.println("Floor Color is: " + z.getColor());
		//}
	}

	/**
	 * 
	 * @param BoundingBoxObject
	 */
	public void removeBoundingBoxObj(BoundingBox BoundingBoxObject) {
		this.collectionOfBoundingBoxes.remove(BoundingBoxObject);
	}

	/**
	 * 
	 * @param nameOfBoundingBoxToRetrieve
	 * @return
	 */
	public BoundingBox getBoundingBox(BoundingBoxName nameOfBoundingBoxToRetrieve) { 
		for(BoundingBox boundingBox : this.collectionOfBoundingBoxes)
			if(boundingBox.getNameGivenToBoundingBox() == nameOfBoundingBoxToRetrieve)
				return boundingBox;
		return null;
	}

	public ArrayList<BoundingBox> getCollectionOfBoundingBoxes() {
		return (ArrayList<BoundingBox>) this.collectionOfBoundingBoxes;
	}

	public BoundingBox[][] getGridOfBoxes() {
		return this.boxArray;
	}
	
	public Floor getFloor(){
		BoundingBox bot = getBoundingBox(BoundingBoxName.CLEANING_BOT);
		int botY = bot.getRectangleObjectUsedToDrawBoundingBox().y;
		int botX = bot.getRectangleObjectUsedToDrawBoundingBox().x;
		for(BoundingBox z : this.collectionOfBoundingBoxes){
			if(z.getRectangleObjectUsedToDrawBoundingBox().y == botY &&
					z.getRectangleObjectUsedToDrawBoundingBox().x == botX && z.getNameGivenToBoundingBox() 
					!= BoundingBoxName.CLEANING_BOT){
//				System.out.println("Cleaning Bot is: " + bot.getNameGivenToBoundingBox());
//				System.out.println("CURRENT Floor Name is: " + z.getNameGivenToBoundingBox());
//				System.out.println("Floor Location: (" + botX + ", " + botY + ")");
				return z.getFloor();
			}
		}
		System.out.println("VACUUM IS OFF THE MAP");
		return null;
	}

	public Floor getFloor(BoundingBoxEdge direction){
		BoundingBox bot = getBoundingBox(BoundingBoxName.CLEANING_BOT);
		switch(direction){

		case NORTH:
			int botY = bot.getRectangleObjectUsedToDrawBoundingBox().y + BotMovement.getInstance().getVelocity();
			int botX = bot.getRectangleObjectUsedToDrawBoundingBox().x;
			for(BoundingBox z : this.collectionOfBoundingBoxes){
				if(z.getRectangleObjectUsedToDrawBoundingBox().y == botY &&
						z.getRectangleObjectUsedToDrawBoundingBox().x == botX && z.getNameGivenToBoundingBox() 
						!= BoundingBoxName.CLEANING_BOT){
//					System.out.println("NORTH Floor Name is: " + z.getNameGivenToBoundingBox());
//					System.out.println("Floor Location: (" + botX + ", " + botY + ")");
					return z.getFloor();	
				}
			}

		case EAST:
			int botY1 = bot.getRectangleObjectUsedToDrawBoundingBox().y;
			int botX1 = bot.getRectangleObjectUsedToDrawBoundingBox().x + BotMovement.getInstance().getVelocity();
			for(BoundingBox z : this.collectionOfBoundingBoxes){
				if(z.getRectangleObjectUsedToDrawBoundingBox().y == botY1 &&
						z.getRectangleObjectUsedToDrawBoundingBox().x == botX1 && z.getNameGivenToBoundingBox() 
						!= BoundingBoxName.CLEANING_BOT){
//					System.out.println("EAST Floor Name is: " + z.getNameGivenToBoundingBox());
//					System.out.println("Floor Location: (" + botX1 + ", " + botY1 + ")");
					return z.getFloor();	
				}
			}

		case SOUTH:
			int botY2 = bot.getRectangleObjectUsedToDrawBoundingBox().y - BotMovement.getInstance().getVelocity();
			int botX2 = bot.getRectangleObjectUsedToDrawBoundingBox().x;
			for(BoundingBox z : this.collectionOfBoundingBoxes){
				if(z.getRectangleObjectUsedToDrawBoundingBox().y == botY2 &&
						z.getRectangleObjectUsedToDrawBoundingBox().x == botX2 && z.getNameGivenToBoundingBox() 
						!= BoundingBoxName.CLEANING_BOT){
//					System.out.println("SOUTH Floor Name is: " + z.getNameGivenToBoundingBox());
//					System.out.println("Floor Location: (" + botX2 + ", " + botY2 + ")");
					return z.getFloor();	
				}
			}

		case WEST:
			int botY3 = bot.getRectangleObjectUsedToDrawBoundingBox().y;
			int botX3 = bot.getRectangleObjectUsedToDrawBoundingBox().x - BotMovement.getInstance().getVelocity();
			for(BoundingBox z : this.collectionOfBoundingBoxes){
				if(z.getRectangleObjectUsedToDrawBoundingBox().y == botY3 &&
						z.getRectangleObjectUsedToDrawBoundingBox().x == botX3 && z.getNameGivenToBoundingBox() 
						!= BoundingBoxName.CLEANING_BOT){
//					System.out.println("WEST Floor Name is: " + z.getNameGivenToBoundingBox());
//					System.out.println("Floor Location: (" + botX3 + ", " + botY3 + ")");
					return z.getFloor();	
				}
			}}

		System.out.println("VACUUM IS OFF THE MAP");
		return null;	
	}


}
