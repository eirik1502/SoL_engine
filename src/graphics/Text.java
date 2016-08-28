package graphics;

public class Text extends Renderable{

	
	
	private Font font;
	private int size;
	private String string;
	
	
	public Text(String string, float x, float y) {
		this(string, Font.getStandardFont(), 18, x, y, 0, 0.99f);
	}
	public Text(String string, Font font, int size, float x, float y, float rotation, float depth) {
		super(x, y, rotation, depth);
		
		if (size != 18) throw new IllegalArgumentException("only size 18 is supported");
		this.string = string;
		this.font = font;
		this.size = size;
		
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
