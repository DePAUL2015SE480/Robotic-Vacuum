package depaul.edu.robotic.vacuum.floorPlan;

import depaul.edu.robotic.vacuum.bounding.box.BoundingBoxName;

/**
 * Creates a default floor plan. Can be later modified to create any room
 * objects
 * 
 * @author rodri_000
 *
 */
public class FloorPlanManager {

	private static FloorPlanManager instance;
	private ComplexRoom floorPlan;
	private final int ORIGIN_X = 0;
	private final int ORIGIN_Y = 0;
	private final int WIDTH = 10;
	private final int HEIGHT = 10;

	private FloorPlanManager() {
		instance = null;
		floorPlan = new ComplexRoom();
	}

	public static FloorPlanManager getInstance() {
		if (instance == null)
			instance = new FloorPlanManager();
		return instance;
	}

	public ComplexRoom createFloorPlan() {
		// set the origin and default size for the floor plan (per student
		// guide)
		floorPlan.setOrigin(ORIGIN_X, ORIGIN_Y);
		floorPlan.setDimension(WIDTH, HEIGHT);

		// create the guest bedroom B
		SimpleRoom guest = new SimpleRoom(BoundingBoxName.GUEST_BEDROOM);
		guest.setOrigin(0, 0);
		guest.setDimension(4, 3);
		floorPlan.addRoomToList(guest);

		// create Bathroom
		SimpleRoom bath1 = new SimpleRoom(BoundingBoxName.BATHROOM);
		bath1.setOrigin(4, 0);
		bath1.setDimension(4, 1);
		floorPlan.addRoomToList(bath1);

		// create the second bathroom
		SimpleRoom bath2 = new SimpleRoom(BoundingBoxName.BATHROOM);
		bath2.setOrigin(4, 1);
		bath2.setDimension(2, 1);
		floorPlan.addRoomToList(bath2);

		// create the first closet
		SimpleRoom closet1 = new SimpleRoom(BoundingBoxName.CLOSET);
		closet1.setOrigin(0, 4);
		closet1.setDimension(2, 1);
		floorPlan.addRoomToList(closet1);

		// create the second closet
		SimpleRoom closet2 = new SimpleRoom(BoundingBoxName.CLOSET);
		closet2.setOrigin(2, 4);
		closet2.setDimension(2, 1);
		floorPlan.addRoomToList(closet2);

		// third closet
		SimpleRoom closet3 = new SimpleRoom(BoundingBoxName.CLOSET);
		closet3.setOrigin(0, 5);
		closet3.setDimension(4, 1);
		floorPlan.addRoomToList(closet3);

		// create the guest bedroom A
		SimpleRoom bed2 = new SimpleRoom(BoundingBoxName.GUEST_BEDROOM);
		bed2.setOrigin(0, 6);
		bed2.setDimension(4, 5);
		floorPlan.addRoomToList(bed2);

		// create the hallway
		SimpleRoom hallway = new SimpleRoom(BoundingBoxName.HALLWAY);
		hallway.setOrigin(4, 3);
		hallway.setDimension(2, 8);
		floorPlan.addRoomToList(hallway);

		// create the master bedroom
		SimpleRoom master = new SimpleRoom(BoundingBoxName.MASTER_BEDROOM);
		master.setOrigin(6, 2);
		master.setDimension(4, 9);
		floorPlan.addRoomToList(master);

		// add the stairs
		SimpleRoom stairs = new SimpleRoom(BoundingBoxName.STAIRS);
		stairs.setOrigin(4, 9);
		stairs.setDimension(0, 0);
		floorPlan.addRoomToList(stairs);

		// add final closet
		SimpleRoom closet4 = new SimpleRoom(BoundingBoxName.CLOSET);
		closet4.setOrigin(8, 0);
		closet4.setDimension(2, 3);
		floorPlan.addRoomToList(closet4);

		return floorPlan;
	}

	public int getFloorPlanWidth() {
		return this.WIDTH;
	}

	public int getFloorPlanHeight() {
		return this.HEIGHT;
	}

}
