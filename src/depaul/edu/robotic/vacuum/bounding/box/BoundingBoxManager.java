package depaul.edu.robotic.vacuum.bounding.box;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
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
    private static BoundingBoxManager instance;
   
    private BoundingBoxManager() {
    	collectionOfBoundingBoxes = 
    			new ArrayList<BoundingBox>();
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
     * @param _color of bounding box
     */
    public void createAndAddBoundingBoxToCollection(BoundingBoxName nameOfBoundingBox, 
    		Rectangle rectangle, Color rectangleColor) {
    	BoundingBox boundingBox = new BoundingBox(nameOfBoundingBox, rectangle, rectangleColor);
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
}
