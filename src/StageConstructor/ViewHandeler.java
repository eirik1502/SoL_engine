package StageConstructor;

import java.util.ArrayList;
import java.util.List;

import physics.PhRectangle;
import physics.PhysicsHandeler;
import rooms.Container;
import rooms.Updateable;
import trash.RenderObject;
import trash.Renderable;
import trash.RoomObject;
import trash.RoomObjectParent;
import userInput.InputState;

public class ViewHandeler extends RoomObject implements Updateable, Renderable{

	
	private List<View> views = new ArrayList<>();
	private View activeView;
	
	
	public ViewHandeler(View... views) {
		for (View v : views) {
			this.views.add(v);
		}
	}
	
	public void start() {
		if (views.size() == 0) throw new IllegalStateException("Need at least one view to start view container");
		
		activeView = pickActiveView();
		if (activeView == null) {
			activeView = views.get(0);
		}
		
		views.forEach(v -> v.start());
	}
	
	@Override
	public void render(RenderObject r) {
		views.forEach(v -> v.render(r));
		
	}

	@Override
	public void update() {
		View v = pickActiveView();
		if (v != null) setActiveView(v);
		activeView.update();
	}

	private View pickActiveView() {
		PhRectangle cursorBox = new PhRectangle(InputState.getMouseX(), InputState.getMouseY(), 1, 1);
		for (View v : views) {
			PhRectangle viewBox = new PhRectangle(v.getX(), v.getY(), v.getWidth(), v.getHeight());
			if (PhysicsHandeler.isCollision(viewBox, cursorBox)) {
				return v;
			}
		}
		return null;
		//if none found, active view will remaind
	}
	
	private void setActiveView(View view) {
		this.activeView.setActive(false);
		this.activeView = view;
		view.setActive(true);
	}
	
}
