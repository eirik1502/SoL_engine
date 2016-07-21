package rooms;

import graphics.Font;

public class Text {

	
	private float x, y, rotation;
	
	private Font font;
	private int size;
	private String string;
	
	
	public Text(String string, Font font, int size, float x, float y, float rotation) {
		if (size != 18) throw new IllegalArgumentException("only size 18 is supported");
		this.string = string;
		this.font = font;
		this.size = size;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		
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
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
}
