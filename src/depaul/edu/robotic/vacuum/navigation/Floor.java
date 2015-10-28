package depaul.edu.robotic.vacuum.navigation;

import java.util.HashMap;
import java.util.Random;
import depaul.edu.robotic.vacuum.bounding.box.*;
import depaul.edu.robotic.vacuum.display.DataPanel;

/**
 * Designate if a floor "cell" is clean, and the floor type.  
 * When the CleanSweep recognizes that an adjacent cell is not 
 * an obstacle, it will create a Floor object isClean is false and floorType UNKNOWN 
 * and add it to the Graph tracking system.
 * @author Briant Becote
 *
 */
public class Floor {
	private Integer vertex;
	private boolean isClean;
	private FloorType floorType;
	//private HashMap<BoundingBox, Floor> floorplan; for correlating BoundingBox and Floor?
	private int dirtValue;
	private static int floorCount;
	
	//CONSTRUCTOR
	public Floor(int vertex){
		this.vertex = vertex;
		this.isClean = false;
		this.floorType = FloorType.UNKNOWN;
		this.dirtValue = 10; //Assumed max until determined otherwise
		this.floorCount ++;
	}
	
	//Used to create the random Floor in BoundingBox class vertex set to -100 temporarily for troubleshooting
	//Does not use floorCount because this method is not part of the vacuum based software (belongs strictly to BoundingBox)
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
	
	/**
	 * numerical representation of a floor cell in graph form.  
	 * @return Integer
	 */
	public Integer getVertex() {
		return vertex;
	}
	
	/**
	 * enum FloorType of a floor cell.  
	 * @return FloorType enum
	 */
	public FloorType getFloorType(){
		return floorType;
	}
	
	/**
	 * numerical representation of the amount of dirt on a floor cell.
	 * Updates boolean isClean if the dirtValue is zero.  
	 * @return int
	 */
	public int getDirtValue(){
		if (dirtValue == 0) isClean = true;
		return dirtValue;
	}
	
	public int getFloorCount(){
	return Floor.floorCount;
	}
}
