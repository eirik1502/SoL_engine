package rooms;

import java.util.ArrayList;
import java.util.LinkedList;


import graphics.Sprite;

public abstract class Room implements Updateable {

	
	//private RoomObjectsContainer roomObjectsContainer;
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Text> texts = new ArrayList<>();
	
	private LinkedList<Entity> addEntityBuffer = new LinkedList<>();
	private LinkedList<Entity> removeEntityBuffer = new LinkedList<>();
	protected boolean updatingEntities = false;
	
	private RelevantInputState inputState;
	
	
	public Room() {
		//this.roomObjectsContainer = new RoomObjectsContainer();
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
	
	void setInputState( RelevantInputState inputState ) {
		this.inputState = inputState;
	}
	public RelevantInputState getInputState() {
		return inputState;
	}
	
	public Entity[] getEntities() {
		return entities.toArray(new Entity[0]);
	}
	
	public Text[] getTexts() {
		return texts.toArray(new Text[0]);
	}

	
	public void addEntity(Entity e) {
		if (updatingEntities) { //not added at all
			addEntityBuffer.add(e);
			return;
		}
		else {
			entities.add(e);
			e.roomInit(this);
			e.start();
		}
	}
	public void removeEntity(Entity e) {
		if (updatingEntities) { //not added at all
			removeEntityBuffer.add(e);
			return;
		}
		else {
			entities.remove(e);
		}
	}
	
	public void addText(Text t) {
		texts.add(t);
	}
	public void removeText(Text t) {
		texts.remove(t);
	}
	
	
}
