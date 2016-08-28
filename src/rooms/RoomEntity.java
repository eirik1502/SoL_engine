package rooms;

import graphics.GraphicsEntity;

public abstract class RoomEntity extends GraphicsEntity {

	
	public abstract void start(Room r);
	public abstract void update();
	
}
