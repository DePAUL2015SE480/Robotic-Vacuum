package depaul.edu.robotic.vacuum.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxManager;
import depaul.edu.robotic.vacuum.navigation.BotMovement;
import depaul.edu.robotic.vacuum.navigation.Floor;
import depaul.edu.robotic.vacuum.navigation.Vertex;

public class Distance {
	private final static BoundingBoxManager boxManager = BoundingBoxManager.getInstance();
	private ConcurrentHashMap<Floor, Double> distMap = new ConcurrentHashMap<>();
	private double currentDist;

	//TODO Throw an error if floor.hasCharger is false
	public Distance(){
		//Floor currentFloor = boxManager.getFloor();
		//if (currentFloor.hasCharger()) 
			this.currentDist = 0.0;
	}
	
	//TODO Used when sensor detects a charger up to two spaces away
	public Distance(Floor currentFloor, Floor chargerFloor){
		currentDist = (currentFloor.getFloorType().getValue() + chargerFloor.getFloorType().getValue())/2.0;
	}
	
	public double getDistance(){
		return currentDist;
	}
	
	
	//Everytime vacuum senses a cell it should call this method
	public void updateDist(Floor floor){
		Floor currentFloor = boxManager.getFloor();
		double weight = calculateWeight(floor);
		if (floor.hasCharger()) {
			System.out.println("THIS FLOOR HAS A CHARGER!!!");
			currentDist = 0.0;
			distMap.put(floor, 0.0);
		}
		if (!distMap.containsKey(floor))
			distMap.put(floor, weight + currentDist);
		else if (distMap.get(floor) < currentDist + weight);
		currentDist = distMap.get(floor);
	}

	public void returnToCharger(){
		Graph map = Graph.getInstance();
		Floor currentFloor = boxManager.getFloor();
		Floor goFloor = currentFloor;
		while (!currentFloor.hasCharger()){
			ArrayList<Edge> list = map.getMap().get(currentFloor);
			double distance = currentDist;
			for (Edge e : list){
				Floor floor = e.adjFloor();
				if (distance > distMap.get(floor)){
					distance = distMap.get(floor);
					goFloor = floor;
				}
			}
			currentDist -= distance;
			moveToCharger(currentFloor, goFloor);
			currentFloor = boxManager.getFloor();
		}
	}
	
	//TODO verify the directions are correct
	private void moveToCharger(Floor currentFloor, Floor goFloor){
		Vertex current = currentFloor.getVertex();
		Vertex adj = goFloor.getVertex();
		int xDif = current.getX() - adj.getX();
		int yDif = current.getY() - adj.getY();
		if (xDif == 0){
			if (yDif > 0) BotMovement.getInstance().moveSouth();
			else BotMovement.getInstance().moveNorth();
		}
		else {
			if (xDif > 0) BotMovement.getInstance().moveEast();
		else BotMovement.getInstance().moveWest();
		}
	}


		public double calculateWeight(Floor floor){
			int currentFloorWeight = boxManager.getFloor().getFloorType().getValue();
			int adjacentFloorWeight = floor.getFloorType().getValue();
			return (currentFloorWeight + adjacentFloorWeight) / 2.0; 
		}
	}
