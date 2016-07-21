package StageConstructor;

import game.Button;
import graphics.Color;
import trash.GrRectangle;
import trash.RenderObject;
import trash.RoomManager;

public class MenuView extends View {
	
	
	private Button[] buttons;
	
	private GrRectangle rect;
	

	public MenuView(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void start() {
		rect = new GrRectangle(getWidth(), getHeight(), 0f);
		
		buttons = new Button[6];
		String resFolder = "res/";
		for (int i = 0; i < buttons.length; i++) {
			System.out.println(buttons.length);
			buttons[i] =  Button.createStandard256x64("SIII",i*256, 0);
			buttons[i].setTextColor(new Color(0.75f, 0.4f, 0.65f));
			addObject(buttons[i]);
		}
	}

	
	@Override
	public void render(RenderObject r) {
		super.render(r);
		
		r.drawShape(rect, new Color(0.5f, 0.5f, 0f), getX(), getY(), 0f);
	}

	
}
