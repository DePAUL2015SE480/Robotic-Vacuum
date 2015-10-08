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
   
    private void buildBoxStructure(Graphics2D graphicsTool, Rectangle _rec, int thickness, Color _color) {
        // Draw top line
    	graphicsTool.setColor(_color);
        graphicsTool.drawRect((int)_rec.getX(), (int)_rec.getY(), (int)_rec.getWidth(), thickness);

        // Draw left line
        graphicsTool.setColor(_color);
        graphicsTool.drawRect((int)_rec.getX(), (int)_rec.getY(), thickness, (int)_rec.getHeight());

        // Draw right line
        graphicsTool.setColor(_color);
        graphicsTool.drawRect((int)_rec.getX() + ((int)_rec.getWidth() - thickness), 
        		(int)_rec.getY(), thickness, (int)_rec.getHeight());
        
        // Draw bottom line
        graphicsTool.setColor(_color);
        graphicsTool.drawRect((int)_rec.getX(), (int)_rec.getY() + (int)_rec.getHeight() - thickness, 
        		(int)_rec.getWidth(), thickness); 
    } 
    
    /**
     * 
     * @param _name used to identify object on screen
     * @param _rect used to create bounding box
     * @param _color is option, if color scheme is needed
     */
    BoundingBox(BoundingBoxName _name, Rectangle _rect, Color _color) {
    	this.nameGivenToBoundingBox = _name;
        this.rectangleObjectUsedToDrawBoundingBox = _rect;
        this.color = _color;
    }
    
    @Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
    	this.buildBoxStructure(g2d, this.rectangleObjectUsedToDrawBoundingBox, 0, this.color);
	}
    
    /**
     * 
     * @param _color is set to color value given
     */
    @Override
    public void setColor(Color _color) { 
    	color = _color;
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
