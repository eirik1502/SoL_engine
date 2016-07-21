package graphics;

import maths.Vector3f;
import maths.Vector4f;

public class Color {

	
	public Vector4f color;
	
	
	public Color(float r, float g, float b) {
		this( r, g, b, 1.0f);
	}
	public Color(float r, float g, float b, float a) {
		this( new Vector4f(r,g,b,a));
	}
	public Color(Vector4f rgba) {
		this.color = rgba;
	}
	public Color(Vector3f rgb) {
		color = new Vector4f(rgb.x, rgb.y, rgb.z, 1.0f);
	}
	
	public static Color BLACK() { return new Color( 0,0,0 ); }
	public static Color WHITE() { return new Color( 1,1,1 ); }
	public static Color RED() { return new Color( 1,0,0 ); }
	public static Color GREEN() { return new Color( 0,1,0 ); }
	public static Color BLUE() { return new Color( 0,0,1 ); }
	public static Color AA() { return new Color( 1,1,0 ); }
	public static Color BB() { return new Color( 0,1,1 ); }
	public static Color PURPLE() { return new Color( 1,0,1 ); }
	
}
