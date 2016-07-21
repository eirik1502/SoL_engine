package StageConstructor;

import graphics.Color;
import maths.Vector3f;
import trash.GrRectangle;
import trash.RenderObject;

public class WorldView extends View{

	
	private CollisionsWorldLayer collisionsLayer;
	private GrRectangle rect;
	
	public WorldView(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void start() {
		rect = new GrRectangle(getWidth(), getHeight(), 0f);
		collisionsLayer = new CollisionsWorldLayer();
		super.addObject(collisionsLayer);
	}
	
	@Override
	public void render(RenderObject r) {
		super.render(r);
		
		r.drawShape(rect, new Color(0f, 0.5f, 0f), getX(), getY(), 0f);
	}

}
