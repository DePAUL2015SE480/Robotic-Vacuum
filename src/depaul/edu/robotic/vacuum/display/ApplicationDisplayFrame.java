package depaul.edu.robotic.vacuum.display;

import javax.swing.JFrame;

/**
 * 
 * @author Deonte Johnson
 *
 */
public class ApplicationDisplayFrame extends JFrame {

	private ApplicationPanel graphicalPanelToGoOnJavaFrame;
	private static final long serialVersionUID = 1L;
	
	public ApplicationDisplayFrame() {
		graphicalPanelToGoOnJavaFrame = new ApplicationPanel();
	}
	
	public void runRoboticVacuumApp() {
		
		//start thread process before adding panel to frame
		new Thread(graphicalPanelToGoOnJavaFrame).start();
		
		//add a component to the frame
		add(graphicalPanelToGoOnJavaFrame);
		
		//set the frame to the size of the panel added
		pack();
		
		//place frame in the center of the screen depending on OS
		setLocationRelativeTo(null);
		
		//window can not be resized
		setResizable(false);
		
		//allow window to be closed by clicking x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//allow the frame to be shown on screen
		setVisible(true);
	}
}
