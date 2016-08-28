package rooms;

import java.util.ArrayList;
import java.util.LinkedList;

import graphics.Camera;
import graphics.GraphicsEntity;
import graphics.Sprite;
import graphics.Text;


public abstract class Room implements Updateable {

	
	//private RoomObjectsContainer roomObjectsContainer;
	private ArrayList<RoomEntity> entities = new ArrayList<>();
	
	private LinkedList<RoomEntity> addEntityBuffer = new LinkedList<>();
	private LinkedList<RoomEntity> removeEntityBuffer = new LinkedList<>();
	protected boolean updatingEntities = false;
	
	private float width, height;
	private Camera camera;
	
	private RoomHandeler roomHandeler;
	
	
	public Room(float width, float height) {
		this.width = width;
		this.height = height;
		camera = new Camera(0,0,width, height);
		//this.roomObjectsContainer = new RoomObjectsContainer();
	}
	
	public void init(RoomHandeler handeler) {
		this.roomHandeler = handeler;
	}
	
	public abstract void load();
	public abstract void start();
	public abstract void stop();
	public abstract void unload();
	

	
	@Override
	public void update( ) {
		updatingEntities = true;
		entities.forEach(e -> e.update());
		updatingEntities = false;
		
		while(!addEntityBuffer.isEmpty()) {
			addEntity(addEntityBuffer.poll());
		}
		while(!removeEntityBuffer.isEmpty()) {
			removeEntity(removeEntityBuffer.poll());
		}
	}
	
	public Camera getCamera() {
		return camera;
	}
	public ArrayList<? extends GraphicsEntity> getGraphicsEntities() {
		return entities;
	}

	
	public void addEntity(RoomEntity e) {
		if (updatingEntities) { //not added at all
			addEntityBuffer.add(e);
			return;
		}
		else {
			entities.add(e);
			e.start(this);
		}
	}
	public void removeEntity(RoomEntity e) {
		if (updatingEntities) { //not added at all
			removeEntityBuffer.add(e);
			return;
		}
		else {
			entities.remove(e);
		}
	}
	
	public void gotoNextRoom() {
		roomHandeler.gotoNextRoom();
	}
	public void gotoPreviousRoom() {
		roomHandeler.gotoPreviousRoom();
	}
	
	
}
