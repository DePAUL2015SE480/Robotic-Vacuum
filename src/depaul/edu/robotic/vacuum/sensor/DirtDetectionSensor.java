package depaul.edu.robotic.vacuum.sensor;

/**
 * 
 * @author Deonte Johnson
 *
 * This class will assist with surface detection and
 * calculating the appropriate surface values
 * 
 */
public class DirtDetectionSensor {

	private static DirtDetectionSensor instance;
	private double dirtCapacity = 50;
	private FloorType currentSurfaceType = FloorType.NO_FLOOR;
	
	public static DirtDetectionSensor getInstance() {
		if(instance == null) 
			instance = new DirtDetectionSensor();
		return instance;
	}
	
	public void reduceDirtCapacity(FloorType surface) {
		if(currentSurfaceType == FloorType.NO_FLOOR) {
			this.dirtCapacity -= surface.ordinal();
			this.currentSurfaceType = surface;
		}
		else if(currentSurfaceType != FloorType.NO_FLOOR) {
			this.dirtCapacity -= ((double)(currentSurfaceType.ordinal() + surface.ordinal()) / 2);
			this.currentSurfaceType = surface;
		}
		else {
			this.dirtCapacity-=surface.ordinal();
		}
	}
	
	public void replenishDirtCapacity() {
		this.dirtCapacity = 50;
	}
	
	public double getDirtCapacity() {
		return this.dirtCapacity;
	}
	
	public FloorType getCurrentSurfaceType() {
		return currentSurfaceType;
	}
	
	public static  void main(String[] args) {
		DirtDetectionSensor s = new DirtDetectionSensor();
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		s.reduceDirtCapacity(FloorType.HIGH_PILE);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		s.reduceDirtCapacity(FloorType.BARE_FLOOR);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		s.reduceDirtCapacity(FloorType.BARE_FLOOR);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		
		s.reduceDirtCapacity(FloorType.LOW_PILE);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		s.reduceDirtCapacity(FloorType.HIGH_PILE);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		s.reduceDirtCapacity(FloorType.HIGH_PILE);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		
		s.reduceDirtCapacity(FloorType.BARE_FLOOR);
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		
		s.replenishDirtCapacity();
		System.out.println("dirty capacity replenished!");
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		
		s.reduceDirtCapacity(FloorType.HIGH_PILE);
		
		System.out.println("dirty capacity: " + s.getDirtCapacity());
		System.out.println("Current Surfaces: " + s.getCurrentSurfaceType());
		
	}
}
