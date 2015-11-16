package depaul.edu.robotic.vacuum.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import depaul.edu.robotic.vacuum.bounding.box.*;
import depaul.edu.robotic.vacuum.display.DataPanel;
import depaul.edu.robotic.vacuum.map.Distance;

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
<<<<<<< HEAD
	private static boolean hasCharger; //TODO needs implimenting
	private Distance chargerTracker;
	private ArrayList<BoundingBoxName> accessPoints;
	private boolean hasObstacle;
	//'development' of https://github.com/DePAUL2015SE480/Robotic-Vacuum.git
=======
	private static boolean hasCharger; //TODO needs implimenting
	private ArrayList<BoundingBoxName> accessPoints;
>>>>>>> branch 'development' of https://github.com/DePAUL2015SE480/Robotic-Vacuum.git
	
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
<<<<<<< HEAD
    	this.isClean = false;
    	//this.isObstacle = false;
    	this.hasCharger = false;
    	this.accessPoints = new ArrayList<BoundingBoxName>();
	}
	
	public boolean hasCharger(){
		return hasCharger;
=======
    	this.isClean = false;
    	this.accessPoints = new ArrayList<BoundingBoxName>();
>>>>>>> branch 'development' of https://github.com/DePAUL2015SE480/Robotic-Vacuum.git
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
<<<<<<< HEAD
=======
    
    public void setAccessPoints(ArrayList<BoundingBoxName> boxNames) {
    	this.accessPoints = boxNames;
    }
>>>>>>> branch 'development' of https://github.com/DePAUL2015SE480/Robotic-Vacuum.git
    
<<<<<<< HEAD
    public void setAccessPoints(ArrayList<BoundingBoxName> boxNames) {
    	this.accessPoints = boxNames;
    }   
    
    public void setCharger(){
    	this.hasCharger = true;
    	//this.chargerTracker = new Distance();
    }
    
    public ArrayList<BoundingBoxName> getAccessPoints() {
    	return this.accessPoints;
=======
    public ArrayList<BoundingBoxName> getAccessPoints() {
    	return this.accessPoints;
>>>>>>> branch 'development' of https://github.com/DePAUL2015SE480/Robotic-Vacuum.git
    }
    
    public String toString(){
    	return ("Floor Location: " + "(" + vertex.getX() + "," + vertex.getY() + ") \n" +
    			"Floor Type: " + this.getFloorType() + "\n" + "Floor Dirtiness : " + this.getDirtValue() + " \n" +
    			"Floor Charger: " + this.hasCharger());
    }

	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}
}
