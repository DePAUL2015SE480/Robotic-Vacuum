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
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	private Vertex vertex;
	private boolean isClean;
	private FloorType floorType;
	private int dirtValue;
	private static int floorCount;
	private static boolean isObstacle; //TODO needs implimenting
	private static boolean hasCharger; //TODO needs implimenting
	
	//CONSTRUCTOR
//	public Floor(BoundingBox box){
//		this.isClean = false;
//		this.floorType = FloorType.UNKNOWN;
//		this.dirtValue = 10; //Assumed max until determined otherwise
//		this.floorCount ++;
//		this.isObstacle = false;
//		this.vertex = new Vertex(box);
//	}
	
	//Used to create the random Floor in BoundingBox class vertex set to -100 temporarily for troubleshooting
	//TODO We should eventually use the above constructor with Sensors update the floor information as the vacuum travels
	public Floor(BoundingBox box){
    	Random random = new Random();
    	int floorValue = random.nextInt(3) + 1;
    	switch (floorValue){
    		case 1: this.floorType = FloorType.BARE_FLOOR; break;
    		case 2: this.floorType = FloorType.LOW_PILE; break;
    		case 3: this.floorType = FloorType.HIGH_PILE; break;
    	}
    	this.dirtValue = random.nextInt(11);
    	this.vertex = new Vertex(box);
    	this.isClean = false;
    	this.isObstacle = false;
	}
	
	/**
	 * numerical representation of a floor cell in graph form.  
	 * @return Vertex
	 */
	public Vertex getVertex() {
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
	
    public boolean isObstacle() { 
    	return isObstacle; 
    }
    
    public void setObstacle() { 
    	isObstacle = true; 
    }   
    
    public String toString(){
    	return ("Floor Location: " + "(" + vertex.getX() + "," + vertex.getY() + ") \n" +
    			"Floor Type: " + this.getFloorType() + "\n" + "Floor Dirtiness : " + this.getDirtValue());
    }
}
