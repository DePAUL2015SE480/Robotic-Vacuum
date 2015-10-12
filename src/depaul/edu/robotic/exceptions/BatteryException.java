package depaul.edu.robotic.exceptions;

/**
 * Throws exceptions for various Battery methods 
 * @author Briant Becote
 */
@SuppressWarnings("serial")
public class BatteryException extends Exception {
	
	public BatteryException(){
		System.out.println("Invalid Battery Operation");
	}

	public BatteryException(String message) {
		super(message);
	}

	public BatteryException(String message, Throwable throwable){
		super(message, throwable);
	}
	
	public BatteryException(String message, Throwable throwable, Exception e){
		super(message, e);
	}
}
