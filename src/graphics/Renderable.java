package graphics;

public abstract class Renderable {
	
	private float x;
	private float y;
	private float rotation;
	
	private float depth;

	
	public Renderable(float x, float y, float rotation, float depth) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.depth = depth;
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


	public float getDepth() {
		return depth;
	}


	public void setDepth(float depth) {
		this.depth = depth;
	}
	
	
}
