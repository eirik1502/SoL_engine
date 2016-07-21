package StageConstructor;

import trash.RoomObjectParent;

public class CollisionsWorldLayer extends RoomObjectParent {

	
	private Grid grid;
	@Override
	public void start() {
		grid = new Grid(32, 32, 20, 20);
		
		addObject(grid);
	}

	
}
