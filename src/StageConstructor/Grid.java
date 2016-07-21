package StageConstructor;

import graphics.Color;
import rooms.Updateable;
import trash.GrRectangle;
import trash.RenderObject;
import trash.Renderable;
import trash.RoomObject;
import userInput.InputState;

import org.lwjgl.glfw.*;


public class Grid extends RoomObject implements Renderable, Updateable{

	private float width, height;
	private int cellWidth, cellHeight;
	private int gridWidth, gridHeight;
	
	private Boolean[][] grid;
	
	
	private boolean showGrid = true;
	
	private GrRectangle cellRect;
	private GrRectangle gridRectHor;
	private GrRectangle gridRectVer;
	
	
	public Grid(int cellWidth, int cellHeight, int gridWidth, int gridHeight) {
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		width = gridWidth * cellWidth;
		height = gridHeight * cellHeight;
		
		grid = new Boolean[gridWidth][gridHeight];
		fillGrid(false);
		
		cellRect = new GrRectangle((float)cellWidth, (float)cellHeight, 0f);
		gridRectHor = new GrRectangle(width, 1, 0);
		gridRectVer = new GrRectangle(1, height, 0);
	}
	
	@Override
	public void start() {
		
		
	}
	
	private void fillGrid(boolean value) {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				grid[i][j] = value;
			}
		}
	}

	@Override
	public void update() {
		boolean leftPressed = InputState.isMousePressed(GLFW.GLFW_MOUSE_BUTTON_LEFT);
		boolean rightPressed = InputState.isMousePressed(GLFW.GLFW_MOUSE_BUTTON_RIGHT);
		float mx = InputState.getMouseX();
		float my = InputState.getMouseY();
		
		if (isInsideGrid(mx, my)) {
			if (leftPressed) {
				setCell(true, mx, my);
			}
			else if (rightPressed) {
				setCell(false, mx, my);
			}
		}
	}

	@Override
	public void render(RenderObject graphics) {
		Color colorBlack = Color.BLACK();
		if (showGrid) {
			
			for (int i = 0; i < gridHeight; i++) {
				graphics.drawShape(gridRectHor, colorBlack, 0, i*cellHeight, 0);
			}
			for (int i = 0; i < gridWidth; i++) {
				graphics.drawShape(gridRectVer, colorBlack, i*cellWidth, 0, 0);
			}
		}
		
		//graphics.drawShape(rect, color, 200, 200, 0);
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				if (grid[i][j]) {
					float x = i*cellWidth;
					float y = j*cellHeight;
//					System.out.println("cell size: " + cellWidth + ", " + cellHeight);
//					System.out.println("grid size: " + gridWidth + ", " + gridHeight);
//					System.out.println("i/j: " + i + ", " + j);
//					System.out.println("rendering cell at: " + x + ", " + y);
					graphics.drawShape(cellRect, colorBlack, x, y, 0);
				}
			}
		}
		
	}
	
	public void setCell(boolean value, float globalX, float globalY) {
		int cellX = getCellX(globalX);
		int cellY = getCellY(globalY);
		grid[cellX][cellY] = value;
	}
	public Boolean getCell(float globalX, float globalY) {
		int cellX = getCellX(globalX);
		int cellY = getCellY(globalY);
		boolean cell = grid[cellX][cellY];
		return cell;
	}
	private int getCellX(float globalX) {
		return (int)Math.floor(globalX / cellWidth);
	}
	private int getCellY(float globalY) {
		return (int)Math.floor(globalY / cellHeight);
	}
	private boolean isInsideGrid(float globalX, float globalY) {
		return (globalX >= 0 && globalX < width && globalY >= 0 && globalY < height );
	}


	
}
