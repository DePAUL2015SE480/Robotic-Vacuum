package depaul.edu.robotic.vacuum.power.management;

import depaul.edu.robotic.vacuum.navigation.Floor;
import depaul.edu.robotic.vacuum.navigation.FloorType;

/**
 * Singleton class that provides API for CleanSweep battery operations and private Battery class
 * Battery life is 100 units max. Each operation requires units of charge depending on surfaces being traversed or cleaned
 * Bare floor - 1 unit
 * Low-pile carpet 2 units
 * High-pile carpet 3 units
 * Movement between two types of floor requires the average of the two surfaces
 * @author Briant Becote
 */

public class BatteryManager {
	private static BatteryManager instance;
	private Battery battery;
	private BatteryManager(){
	this.battery = new Battery();	
	}
	
    /**
     * 
     * @return a single instance of this object
     */
	public static BatteryManager getInstance(){
		if (instance == null)
			instance = new BatteryManager();
		return instance;
	}
	
//	private enum Floor {
//		UNKNOWN(500), BARE_FLOOR(1), LOW_PILE(2), HIGH_PILE(3);
//		private int value;
//		private Floor (int value){
//			this.value = value;
//		}
//	}
	
    /**
     * 
     * @return a double representation of current battery life
     */
	public double getBatteryLevel(){
		return battery.getBatteryLife();
	}
	
    /**
     * 
     * Should be called when the CleanSweep senses it is current on a charging station
     */
	public void batteryCharging(){
		battery.batteryCharging();
	}
	
    /**
     * Should be called whenever the battery cleans the floor. Reduces battery life.
     * @param floorType current floor type
     * @exception if an unknown floor type is provided
     */

	public void batteryVacuum(FloorType floorType) throws BatteryException{
		battery.batteryDrain(floorType); 
	}
	
    /**
     * Should be called whenever the battery travels. Reduces battery life.
     * @param floorType1 floor type under the CleanSweep
     * @param floorType2 floor type CleanSweep is traveling to
     * @exception if an unknown floor type is provided
     */
	
	public void batteryTravel (FloorType floorType1, FloorType floorType2) throws BatteryException{
		battery.batteryDrain(floorType1, floorType2);
	}
	
	
    /**
     * Private class that maintains battery life and encapsulated battery operations
     */
	private class Battery{
		private double batteryLife;
		
		//Assumes all CleanSweeps come to life with 100% battery
		private Battery(){
			this.batteryLife = 100;
		}

		
		private void batteryDrain(FloorType floorType) throws BatteryException {
			if (floorType.getValue() > 3 || floorType.getValue() < 1) throw new BatteryException ("Unknown floor type provided");
			batteryLife -= floorType.getValue();
		}

		private void batteryDrain(FloorType floorType1, FloorType floorType2) throws BatteryException  {	
			double floorValue = (floorType1.getValue() + floorType2.getValue())/2;
			if (floorValue < 1 || floorValue > 3)throw new BatteryException ("Unknown floor type provided");
			batteryLife -= floorValue;
		}
		
		private double getBatteryLife() {
			return batteryLife;
		}

		private void batteryCharging(){
			this.batteryLife = 100;
		}
	}
}

