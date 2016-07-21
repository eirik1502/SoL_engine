package StageConstructor;

import graphics.Color;
import game.Button;
import maths.Vector4f;
import rooms.Updateable;
import trash.RenderObject;
import trash.Renderable;
import trash.RoomManager;
import trash.RoomObject;

public class MenuBar extends RoomObject implements Updateable, Renderable{

	
	
	public MenuBar() {

	}
	
	@Override
	public void start() {
		buttons = new Button[6];
		String resFolder = "res/";
		for (int i = 0; i < buttons.length; i++) {
			System.out.println(buttons.length);
			buttons[i] =  Button.createStandard256x64("SIII",i*256, 0);
			buttons[i].setTextColor(new Color(0.75f, 0.4f, 0.65f));
			RoomManager.addRoomObject(buttons[i]);
		}
	}

	@Override
	public void render(RenderObject graphics) {

		graphics.drawText("haha%%%&&&&&&%%##", 100, 400);
		graphics.drawText("haha%%%&&&&&&%%%%", new Color(new Vector4f(0f,0f,0f,0.5f)), 100, 420);
	}

	@Override
	public void update() {

		
	}
}
