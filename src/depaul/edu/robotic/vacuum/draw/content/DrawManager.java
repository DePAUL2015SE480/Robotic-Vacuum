package depaul.edu.robotic.vacuum.draw.content;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

/**
 * 
 * @author Deonte Johnson
 *
 *	This class will only add, remove, and draw all content
 */
public final class DrawManager {
			
	private LinkedList<Draw> collectionOfDrawCompnents;
	private static DrawManager instance;
	
	//prevent additional instances of this object
	private DrawManager() {
		collectionOfDrawCompnents = new LinkedList<Draw>();
		instance = null;
	}

	/**
	 * 
	 * @return only instance of this object
	 */
	public static DrawManager getInstance() {
			
		if(instance == null) 
			instance = new DrawManager();
		return instance;
			
	}

	public void addDrawComponent(Draw drawComponent) {
		collectionOfDrawCompnents.add(drawComponent);
	}
		
	public void remove(Draw drawComponent) {
		collectionOfDrawCompnents.remove(drawComponent);
	}
		
	public void drawAllComponents(Graphics2D g2d, ImageObserver observer) {
			
		//give each component a broader stroke
		g2d.setStroke(new BasicStroke(1.5f));
			
		//create a new Rendering hint
		RenderingHints rh = 
				new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
			
		//increase rendering speed/quality
		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_SPEED);
			
		//set rendering hints for all components drawn
		g2d.setRenderingHints(rh); 

		//change collectionOfDrawCompnents into an array to avoid concurrent modification
		Draw[] collectionOfDrawCompnentsOfDrawComponent = collectionOfDrawCompnents.toArray(new Draw[0]);
		
		//draw all components inheriting drawContent
		for(Draw drawComponent : collectionOfDrawCompnentsOfDrawComponent)
			drawComponent.drawContent(g2d, observer); 
	}
		
	/**
	 * 
	 * @return number of drawable objects in this collectionOfDrawCompnents
	 */
	public int size() { 
		return this.collectionOfDrawCompnents.size(); 
	}
}
