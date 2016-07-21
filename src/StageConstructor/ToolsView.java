package StageConstructor;

import graphics.Color;
import trash.GrRectangle;
import trash.RenderObject;

public class ToolsView extends View {

	
	private GrRectangle rect;
	private Color color;
	
	public ToolsView(float x, float y, float width, float height) {
		super(x, y, width, height);

		rect = new GrRectangle(getWidth(), getHeight(), 0f);
		color = new Color(0f,0.68f,0.333f);
	}
	

	@Override
	public void start() {
		
	}
	

	@Override
	public void render(RenderObject r) {
		super.render(r);
		
		r.drawShape(rect, color, getX(), getY(), 0f);
	}
	
	@Override
	public void update() {
		super.update();
		
		if ((color.color.x+=0.01) > 1.0f) color.color.x = 0;
	}

}
