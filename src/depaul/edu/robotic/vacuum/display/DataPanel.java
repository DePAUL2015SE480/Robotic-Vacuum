package depaul.edu.robotic.vacuum.display;

import java.awt.TextArea;

import java.awt.Color;

/**
 * 
 * @author Deonte Johnson
 *
 */
public class DataPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static TextArea dataDisplay;
	private final int WIDTH = 50;
	private final int HEIGHT = 24;
	
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
	}

	/**
	 * 
	 * @param msg to be printed to data panel
	 */
	public static void print(String msg) {
		dataDisplay.append(msg + "\n");
	}
}
