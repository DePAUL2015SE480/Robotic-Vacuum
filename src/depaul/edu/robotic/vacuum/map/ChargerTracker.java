package depaul.edu.robotic.vacuum.map;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.navigation.Floor;

public class ChargerTracker {
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	private static ArrayList<Distance> tracker;
	private static ChargerTracker instance;
	private double distanceToCharger;

	public static ChargerTracker getInstance(){
		if (instance == null) return new ChargerTracker();
		return instance;
	}
	private ChargerTracker(){
		this.tracker = new ArrayList<>();
		this.tracker.add(new Distance());
	}
	
	public ChargerTracker getInstance(Floor currentFloor, Floor floor){
		if (instance == null) return new ChargerTracker(currentFloor, floor);
		return instance;
	}
	
	private ChargerTracker(Floor currentFloor, Floor floor){
		this.tracker = new ArrayList<>();
		this.tracker.add(new Distance(currentFloor, floor));
	}
	
	public double getDistanceToCharger(){
		Floor currentFloor = boxManager.getFloor();
		System.out.println("Current Floor: \n" + currentFloor.toString());
		distToCharger(currentFloor);
		return distanceToCharger;
	}
	
	//TODO impliment adding a charger from 2 spaces away
	//This method should be called when the charger sensor detects a new charger
	public void addCharger(){
		this.tracker.add(new Distance());
	}
	
	public void distToCharger(Floor floor){
		double dist = 100.0;
		for (Distance d : tracker){
			d.updateDist(floor);
			if (d.getDistance() < dist) 
				dist = d.getDistance();
		}
			this.distanceToCharger = dist;
	}
	
}
