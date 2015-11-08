package depaul.edu.robotic.vacuum.navigation;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBox;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

public class Vertex {
    	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
    	private int x;
    	private int y;
    	
    	//Method WORKS IN RELATION TO POSITION OF CLEANING BOT - SEE HARD CODE BOT VARIABLE BELOW
    	public Vertex(){
    		BoundingBox bot = boxManager.getBoundingBox(BoundingBoxName.CLEANING_BOT);
    		this.x = bot.getRectangleObjectUsedToDrawBoundingBox().x;
    		this.y = bot.getRectangleObjectUsedToDrawBoundingBox().y;
    	}
    	
    	public Vertex(BoundingBox box){
        		this.x = box.getRectangleObjectUsedToDrawBoundingBox().x;
        		this.y = box.getRectangleObjectUsedToDrawBoundingBox().y;
        	}
    	
    	public int getX(){
    		return x;
    	}
    	
    	public int getY(){
    		return y;
    	}
    	
    	public String toString(){
    		return ("(" + x + ", " + y + ")");
    	}
    }

