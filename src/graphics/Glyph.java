package graphics;


public class Glyph {

	private float width, height;
	
	private Texture tex;
	private VertexArray va;
	
	
	
	public Glyph(Texture tex, VertexArray va, float width, float height) {
		this.tex = tex;
		this.va = va;
		this.width = width;
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	
	public Texture getTexture() {
		return tex;
	}
	
	public VertexArray getVertexArray() {
		return va;
	}

}
