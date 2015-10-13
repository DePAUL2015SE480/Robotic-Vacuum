package depaul.edu.robotic.vacuum.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBox;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;
import depaul.edu.robotic.vacuum.draw.content.DrawManager;

/**
 * 
 * @author Deonte Johnson
 *
 */
public class ApplicationPanel extends JPanel implements Runnable {
	
	private final int DELAY;
	private final int SCREEN_SIZE_X;
	private final int SCREEN_SIZE_Y;
	private static final long serialVersionUID = 1L;
	private long beforeTime;
	private long timeDiff;
	private long sleep;
	
	public ApplicationPanel() {
			
		//Constant variables
		SCREEN_SIZE_X = 700;
		SCREEN_SIZE_Y = 600;
		DELAY = 25;
		
		beforeTime = 0;
		timeDiff = 0;
		sleep = 0;
	
		//set the background color of the panel
		//figure out how to set pic as background
		setBackground(Color.BLACK);
	
		//allow panel to focus on keyAdapter
		setFocusable(true);
		
		//preferred size for this particular panel
		setPreferredSize(new Dimension(SCREEN_SIZE_X+1, SCREEN_SIZE_Y+1));
		
		setDoubleBuffered(true);

		//Load application contents
		LoadContent();
			
	}
		
	@Override
	public void run() {
		
		//get current system time(game time)
		beforeTime = System.currentTimeMillis();
		
		while(true) {
			
			//update animations
			update();
			
			//repaint animations to screen
			repaint();
			
			//get time difference between current & before current time
			timeDiff = System.currentTimeMillis() - beforeTime;
			
			sleep = DELAY - timeDiff;
			
			if(sleep < 0) {
				sleep = 2;
			}
			
			try {
				
				Thread.sleep(sleep);
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}
			
			//reset beforeTime to current time in milliseconds
			beforeTime = System.currentTimeMillis();
			
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw content to the screen
		//create a  2d graphic
		Graphics2D g2d = (Graphics2D) g;
							
		//draw all components to the screen
		DrawManager.getInstance().drawAllComponents(g2d, this);
							
		//ensure that all graphics are up to date
		Toolkit.getDefaultToolkit().sync();
						
		//dispose of any resources
		g2d.dispose();	
	}
	
	private void update() {
		
		//this method is reserved for the navigation object, so that
		//robot can navigate across locations. all robot active will be
		//done in the navigation package and the navigation object will
		//be used here. Content below is just 
		//for show and will be deleted.
		BoundingBoxManager.getInstance()
		.getBoundingBox(BoundingBoxName.CLEANING_BOT)
		.getRectangleObjectUsedToDrawBoundingBox().x++;
	}

	/**
	 * Visual content will be loaded here. Boxes signifying rooms, 
	 * robot, etc will be loaded here to be shown on screen.
	 */
	private void LoadContent() {
				
		//Load cleaning robot 
		BoundingBoxManager.getInstance().createAndAddBoundingBoxToCollection(
				BoundingBoxName.CLEANING_BOT, new Rectangle(100, 100, 50, 50), Color.RED);
		
		//Load rooms
		
		//Load any obstacles
		
		//add all bounding boxes to the draw manager to be drawn to 
		//the screen
		for(BoundingBox boundingBox : BoundingBoxManager
				.getInstance().getCollectionOfBoundingBoxes()) {
			DrawManager.getInstance().addDrawComponent(boundingBox);
		}
	}
}