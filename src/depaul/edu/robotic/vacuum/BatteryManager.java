package depaul.edu.robotic.vacuum;

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
	
    /**
     * 
     * @return a double representation of current battery life
     */
	public double getBatteryLevel(){
		return battery.batteryLife();
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
	//TODO Takes input of Enum - currently a String type
	public void batteryVacuum(String floorType) throws BatteryException{
		battery.batteryDrain(floorType); 
	}
	
    /**
     * Should be called whenever the battery travels. Reduces battery life.
     * @param floorType1 floor type under the CleanSweep
     * @param floorType2 floor type CleanSweep is traveling to
     * @exception if an unknown floor type is provided
     */
	//TODO Takes input of Enum - currently a String type
	public void batteryTravel (String floorType1, String floorType2) throws BatteryException{
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

		//TODO Takes input of Enum - currently a String type
		private void batteryDrain(Object floor) throws BatteryException {
		if (floor == "BARE") batteryLife = batteryLife -1;
		if (floor == "LOWPILE") batteryLife = batteryLife -2;
		if (floor == "HIGHPILE") batteryLife = batteryLife - 3;
		else throw new BatteryException ("Unknown floor type provided");
		}

		//TODO Takes input of Enum - currently a String type
		private void batteryDrain(Object floorType1, Object floorType2) throws BatteryException  {
			if (floorType1 == floorType2) batteryDrain(floorType1);	
			
			if (floorType1 == "BARE" || floorType2 == "BARE") {
				if (floorType1 == "LOWPILE" || floorType2 == "LOWPILE") batteryLife = batteryLife - 1.5;
				if (floorType1 == "HIGHPILE" || floorType2 == "HIGHPILE")batteryLife = batteryLife - 2;
				else throw new BatteryException ("Unknown floor type provided");
			}
			
			if (floorType1 == "LOWPILE" || floorType2 == "LOWPILE" && floorType1 == "HIGHPILE" || floorType2 == "HIGHPILE") 
				batteryLife = batteryLife - 2.5;
			else throw new BatteryException ("Unknown floor type provided");
		}
		
		private double batteryLife() {
			return batteryLife;
		}

		private void batteryCharging(){
			this.batteryLife = 100;
		}
	}
}

