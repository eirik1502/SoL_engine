package StageConstructor;

import rooms.Room;
import trash.GrRectangle;
import trash.RoomManager;

public class ConstructorRoom extends Room {
	
	public static final int WIDTH = 1600*4, HEIGHT = 1600*4;
	public static final int CELL_WIDTH = 32, CELL_HEIGHT = 32;
	public static final int GRID_WIDTH = 400, GRID_HEIGHT = 400;

	private Grid grid;
	private MenuBar menuBar;
	
	
	private ViewHandeler viewHandeler;
	
	
	@Override
	public void load() {
		//collisionGrid = new GrRectangle[GRID_WIDTH][GRID_HEIGHT];
		float menuHeight = 64;
		float toolsWidth = 256;
		View menuView = new MenuView(0,0, Main.WIDTH, menuHeight);
		View toolsView = new ToolsView(0,menuHeight, toolsWidth, Main.HEIGHT-menuHeight);
		View worldView = new WorldView(toolsWidth, menuHeight, Main.WIDTH - toolsWidth, Main.HEIGHT-menuHeight);
		viewHandeler = new ViewHandeler(menuView, toolsView, worldView);
		
		grid = new Grid(CELL_WIDTH, CELL_HEIGHT, GRID_WIDTH, GRID_HEIGHT);
		menuBar = new MenuBar();
	}

	@Override
	public void start() {
		viewHandeler.start();
		super.addObject(viewHandeler);
		
//		super.addObject(grid);
//		super.addObject(menuBar);
	}

	@Override
	public void stop() {
//		super.removeObject(grid);
//		super.removeObject(menuBar);
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}

}
