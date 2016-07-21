package utils;

import graphics.VertexArray;

public class VertexArrayUtils {

	private VertexArrayUtils() {
		
	}
	
	
	public static VertexArray createRectangle(float width, float height, float depth) {
		
		float[] vertices = new float[] {
			0.0f, 0.0f, depth,
			0.0f, height, depth,
			width, height, depth,
			width, 0.0f, depth
		};
			
		byte[] indices = new byte[] {
			0, 1, 2,
			2, 3, 0
		};
		
		float[] tcs = new float[] { //texture coordinates
			0, 0,
			0, 1,
			1, 1,
			1, 0
		};
		
		return new VertexArray( vertices, indices, tcs );
	}
}
