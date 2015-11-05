package depaul.edu.robotic.vacuum.diagnostics;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RoboticVacuumLogger {
	
	private static final Logger logger = Logger.getLogger(RoboticVacuumLogger.class.getName());
	public static void main(String[] args) {
		logger.setLevel(Level.INFO);
		logger.info("LOGGER Test");
		
	}
}