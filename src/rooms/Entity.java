package rooms;

import graphics.Sprite;

public abstract class Entity implements Updateable {

	
	protected float x, y;
	
	protected float rotation;
	
	//protected Sprite sprite;
	
	protected Room room;
	
	/**
	 * Subclasses should not use their constructor
	 * @param sprite
	 * @param x
	 * @param y
	 * @param rotation
	 */
	public Entity(float x, float y, float rotation) {
		//this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	public void roomInit(Room room) {
		this.room = room;
	}
	
	/**
	 * room properties are set
	 */
	public void start() {
		
	}
	
//	public Sprite getSprite() {
//		return sprite;
//	}
	
	@Override
	public void update() {
		//sprite.update();
	}


	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	
	
	
}