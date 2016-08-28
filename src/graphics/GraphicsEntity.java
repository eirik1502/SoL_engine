package graphics;

import java.util.ArrayList;

public abstract class GraphicsEntity {


	
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private ArrayList<Text> texts = new ArrayList<>();
	//private ArrayList<Shape> shapes;
	
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	public ArrayList<Text> getTexts() {
		return texts;
	}

	
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	public void addText(Text text) {
		texts.add(text);
	}
	
}
