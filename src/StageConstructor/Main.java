package StageConstructor;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.util.ArrayList;

import game.GameRoom;
import game.IntroRoom;
import game.MenuRoom;
import graphics.GraphicsHandeler;
import graphics.GraphicsUtils;
import rooms.Room;
import rooms.RoomHandeler;
import userInput.InputHandeler;
import userInput.InputState;

public class Main {
	
	public static final int WIDTH = 1600, HEIGHT = 900;
	
	public static final int UPS = 60;
	

	private InputHandeler input;
	private GraphicsHandeler graphics;
	
	private RoomHandeler roomHandeler;
	
	private long window;

	
	public static void main( String[] args ) {
		Main program = new Main();
		program.init();
		program.start();
	}

	public void init() {
		
		window = GraphicsUtils.createWindowOpenGl(WIDTH, HEIGHT, "ZOL stage constructor");
		
		graphics = new GraphicsHandeler(window);
		input = new InputHandeler(window);
		
		roomHandeler = new RoomHandeler();
		
		ArrayList<Room> rooms = new ArrayList<>();
		rooms.add(new ConstructorRoom());
		roomHandeler.setRooms(rooms);
	}
	
	public void start() {

		roomHandeler.start();
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(!glfwWindowShouldClose(window)) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				
				update();
				
				updates++;
				delta--;
			}
			
			render();
			
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	
		glfwDestroyWindow(window);
		glfwTerminate();

	}

	
	public void update() {
		glfwPollEvents();
		
		roomHandeler.update();
	}

	private void render() {
		
		graphics.render(roomHandeler);
	}
}
