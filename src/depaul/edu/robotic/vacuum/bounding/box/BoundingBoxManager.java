package depaul.edu.robotic.vacuum.bounding.box;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    	boxArray = new BoundingBox[8][8];
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
    		Rectangle rectangle, Color rectangleColor, HashMap<BoundingBoxEdge, Boolean> edges, int x, int y) {
    	BoundingBox boundingBox = new BoundingBox(nameOfBoundingBox, rectangle, rectangleColor);
    	this.establishEdges(boundingBox, edges);
    	if (nameOfBoundingBox != BoundingBoxName.CLEANING_BOT)
    		this.boxArray[x][y] = boundingBox;
        this.collectionOfBoundingBoxes.add(boundingBox);
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
    
    public List<BoundingBox> getCollectionOfBoundingBoxes() {
    	return this.collectionOfBoundingBoxes;
    }
    
    public BoundingBox[][] getGridOfBoxes() {
    	return this.boxArray;
    }
    
    /**
     * Establishes all the edges of a bounding box
     * @param nameOfBoundingBoxToRetrieve
     * @param edges
     */
    public void establishEdges(BoundingBox box, HashMap<BoundingBoxEdge, Boolean> edges) {
    	for (BoundingBoxEdge edge : edges.keySet()) {
			box.getEdges().put(edge, edges.get(edge));
		}
    }
}
