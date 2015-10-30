package depaul.edu.robotic.vacuum.navigation;

/**
 * Enum floor type to define 3 types of flooring and an unknown.  
 * Associated values represent battery cost for travel over
 * @author Briant
 */

//Unknown value set to 500 as a placeholder
public enum FloorType {
		UNKNOWN(500), BARE_FLOOR(1), LOW_PILE(2), HIGH_PILE(3);
		private int value;
		
		private FloorType (int value){
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}

