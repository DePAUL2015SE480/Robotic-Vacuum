package depaul.edu.robotic.vacuum;

import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import depaul.edu.robotic.vacuum.display.ApplicationDisplayFrame;

/**
 * 
 * @author Deonte Johnson
 * 
 * Main class where robotic vacuum
 * application will be executed.
 *
 */
public class RoboticVacuumMain {
	private static final Logger logger = Logger.getLogger(RoboticVacuumMain.class.getName());
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable( ) {
							
			@Override
			public void run() {		
				new ApplicationDisplayFrame().runRoboticVacuumApp();
			}
		});	
	}	
}
