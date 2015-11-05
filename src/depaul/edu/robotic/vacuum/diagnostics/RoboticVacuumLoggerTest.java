package depaul.edu.robotic.vacuum.diagnostics;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.junit.Test;

import depaul.edu.robotic.vacuum.RoboticVacuumMain;
import depaul.edu.robotic.vacuum.display.ApplicationDisplayFrame;



public class RoboticVacuumLoggerTest {

	

	@Test
	public void test() {
		final Logger logger = Logger.getLogger(RoboticVacuumMain.class.getName());
		SwingUtilities.invokeLater(new Runnable( ) {
			
			@Override
			public void run() {		
				new ApplicationDisplayFrame().runRoboticVacuumApp();
			}
		});	
		logger.setLevel(Level.INFO);
		logger.info("LOGGER Test");
		String string = "LOGGER Test";
		assertEquals("LOGGER Test", string);
	}	
	
}	
	

