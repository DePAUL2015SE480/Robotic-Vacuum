package depaul.edu.robotic.vacuum;

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
	
	public static void main(String[] args) {
		//Queue the event
		SwingUtilities.invokeLater(new Runnable( ) {
							
			@Override
			public void run() {		
				//instantiate & run a robotic vacuum application Thread
				new ApplicationDisplayFrame().runRoboticVacuumApp();
			}
		});	
	}	
}
