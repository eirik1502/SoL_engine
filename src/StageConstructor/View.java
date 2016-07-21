package StageConstructor;

import rooms.Updateable;
import trash.RenderObject;
import trash.Renderable;
import trash.RoomObjectParent;

public abstract class View extends RoomObjectParent implements Updateable, Renderable {

	
	private boolean active;
	
	private float x, y, width, height;
	
	public View(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render( RenderObject r ) {
		super.render(r);
	}
	
	
	public void setActive( boolean value) {
		this.active = active;
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
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	
	
}
