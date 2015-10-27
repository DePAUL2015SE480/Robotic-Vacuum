package depaul.edu.robotic.vacuum.navigation;

import java.util.HashMap;
import java.util.Random;
import depaul.edu.robotic.vacuum.bounding.box.*;

/**
 * Designate if a floor "cell" is clean, and the floor type.  When the CleanSweep recognizes that an
 * adjacent cell is not an obstacle, it will create a Floor object isClean is false and floorType UNKNOWN 
 * and add it to the Graph tracking system.
 * @author B
 *
 */
public class Floor {
	private Integer vertex;
	private boolean isClean;
	private FloorType floorType;
	//private HashMap<BoundingBox, Floor> floorplan; for correlating BoundingBox and Floor?
	private int dirtValue;
	private static int floorCount;
	
	public Floor(int vertex){
		this.vertex = vertex;
		this.isClean = false;
		this.floorType = FloorType.UNKNOWN;
		this.dirtValue = 10; //Assumed max until determined otherwise
		this.floorCount ++;
	}
	
	//Used to create the random Floor in BoundingBox class vertex set to -100 temporarily for troubleshooting
	//Does not use floorCount because it's not part of the vacuum based software
	public Floor(){
    	Random random = new Random();
    	int floorValue = random.nextInt(3) + 1;
    	switch (floorValue){
    		case 1: this.floorType = FloorType.BARE_FLOOR; break;
    		case 2: this.floorType = FloorType.LOW_PILE; break;
    		case 3: this.floorType = FloorType.HIGH_PILE; break;
    	}
    	this.dirtValue = random.nextInt(11);
    	this.vertex = -100;
    	this.isClean = false;
	}

	public int getVertex() {
		return vertex;
	}
	
	public FloorType getFloorType(){
		return floorType;
	}
	
	public int getDirtValue(){
		if (dirtValue == 0) isClean = true;
		return dirtValue;
	}
}
