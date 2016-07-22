package graphics;



public class GraphicsEntity {

	protected float x, y;
	
	protected float rotation;
	
	protected Sprite sprite;
	
	
	public GraphicsEntity(Sprite sprite, float x, float y, float rotation) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void addX(float x) {
		this.x += x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void addY(float y) {
		this.y += y;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
}
