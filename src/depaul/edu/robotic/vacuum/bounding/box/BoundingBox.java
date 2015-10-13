package depaul.edu.robotic.vacuum.bounding.box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import depaul.edu.robotic.vacuum.draw.content.Draw;

/**
 * 
 * @author Deonte Johnson
 * 
 * This class is for bounding box creation and access. The class
 * should not be accessed outside this package, should be accessed through
 * the BoundingBoxManager singleton class.
 *
 */
public final class BoundingBox implements Box, Draw {

	private BoundingBoxName nameGivenToBoundingBox;
    private Rectangle rectangleObjectUsedToDrawBoundingBox;
    private Color color;
   
    private void buildBoxStructure(Graphics2D graphicsTool, Rectangle rectangleToCreateBox,
    		int thickness, Color rectangleColor) {
    	
        // Draw top line
    	graphicsTool.setColor(rectangleColor);
        graphicsTool.drawRect((int)rectangleToCreateBox.getX(), 
        		(int)rectangleToCreateBox.getY(), (int)rectangleToCreateBox.getWidth(), thickness);

        // Draw left line
        graphicsTool.setColor(rectangleColor);
        graphicsTool.drawRect((int)rectangleToCreateBox.getX(), 
        		(int)rectangleToCreateBox.getY(), thickness, (int)rectangleToCreateBox.getHeight());

        // Draw right line
        graphicsTool.setColor(rectangleColor);
        graphicsTool.drawRect((int)rectangleToCreateBox.getX() + ((int)rectangleToCreateBox.getWidth() - thickness), 
        		(int)rectangleToCreateBox.getY(), thickness, (int)rectangleToCreateBox.getHeight());
        
        // Draw bottom line
        graphicsTool.setColor(rectangleColor);
        graphicsTool.drawRect((int)rectangleToCreateBox.getX(), 
        		(int)rectangleToCreateBox.getY() + (int)rectangleToCreateBox.getHeight() - thickness, 
        		(int)rectangleToCreateBox.getWidth(), thickness); 
    } 
    
    /**
     * 
     * @param nameOfBoundingBox used to identify object on screen
     * @param rectangleToCreateBoxt used to create bounding box
     * @param rectangleColor is option, if color scheme is needed
     */
    BoundingBox(BoundingBoxName nameOfBoundingBox, Rectangle rectangleToCreateBoxt, Color rectangleColor) {
    	this.nameGivenToBoundingBox = nameOfBoundingBox;
        this.rectangleObjectUsedToDrawBoundingBox = rectangleToCreateBoxt;
        this.color = rectangleColor;
    }
    
    @Override
	public void drawContent(Graphics2D g2d, ImageObserver observer) {
    	this.buildBoxStructure(g2d, this.rectangleObjectUsedToDrawBoundingBox, 0, this.color);
	}
    
    /**
     * 
     * @param rectangleColor is set to color value given
     */
    @Override
    public void setColor(Color rectangleColor) { 
    	color = rectangleColor;
    }
    
    /**
     * 
     * @return the name of this bounding box
     */
    @Override
    public BoundingBoxName getNameGivenToBoundingBox() {
    	return nameGivenToBoundingBox; 
    }
    
    /**
     * 
     * @return the bounding box object
     */
    @Override
    public Rectangle getRectangleObjectUsedToDrawBoundingBox() { 
    	return rectangleObjectUsedToDrawBoundingBox; 
    }
}
