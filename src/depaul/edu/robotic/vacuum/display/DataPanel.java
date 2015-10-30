package depaul.edu.robotic.vacuum.display;

import java.awt.TextArea;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import depaul.edu.robotic.vacuum.RoboticVacuumMain;

/**
 * 
 * @author Deonte Johnson
 *
 */
public class DataPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	
	private static final Logger logger = Logger.getLogger(RoboticVacuumMain.class.getName());
	private static final long serialVersionUID = 1L;

	private static TextArea dataDisplay;
	private final int WIDTH = ApplicationPanel.SCREEN_SIZE_X >>3;
	private final int HEIGHT = ApplicationPanel.SCREEN_SIZE_Y >>4;
	


	public DataPanel() {
		initizeTextArea();
		
		this.setBackground(Color.BLACK);
		this.add(dataDisplay);
		this.setVisible(true);
	}
	
	private void initizeTextArea() {
		dataDisplay = new TextArea(HEIGHT, WIDTH);
		dataDisplay.setBackground(Color.BLACK);
		dataDisplay.setForeground(Color.WHITE);
	}
	
	/**
	 * Display all data printed using this.print() to the 
	 * screen
	 */
	public static void displayData() {
		dataDisplay.getText();
		logger.log(Level.INFO, dataDisplay.getText());
		
	}

	/**
	 * 
	 * @param msg to be printed to data panel
	 */
	public static void print(String msg) {
		dataDisplay.append(msg + "\n");
	}


}
