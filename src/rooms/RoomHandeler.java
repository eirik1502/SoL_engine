package rooms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import graphics.GraphicsHandeler;
import userInput.InputHandeler;
import userInput.InputState;

public class RoomHandeler  {
	
	
	private ArrayList<Room> rooms = new ArrayList<>();
	private int currentRoomNumber;
	private Room currentRoom;

	
	public RoomHandeler() {
		
		//RoomManager.setRoomHandeler(this);
	}
	
	/**
	 * Should be called before start
	 * @param rooms
	 */
	public void setRooms( ArrayList<Room> rooms ) {
		this.rooms = rooms;
	}
	
	public void start() {
		if (rooms.size() == 0) throw new IllegalStateException("Cannot start RoomHandeler because no rooms are set");
		gotoStartingRoom();
	}
	
	public void update(RelevantInputState inputState) {
		currentRoom.setInputState(inputState);
		currentRoom.update();
	}
	
	public Entity[] getEntities() {
		return currentRoom.getEntities();
	}
	public Text[] getTexts() {
		return currentRoom.getTexts();
	}
	
	
	private void gotoStartingRoom() {
		if (rooms.size() == 0) throw new IllegalStateException("There are no rooms set, cannot start first room");
		Room newRoom = getRoomFromNumber(0);
		currentRoom = newRoom;
		currentRoomNumber = 0;
		currentRoom.load();
		currentRoom.start();
	}
	public void gotoRoom(int roomNumber) {
		if (roomNumber < 0 || roomNumber >= rooms.size()) throw new IllegalArgumentException("No rooms correspond to the roomNumber given");
		Room newRoom = getRoomFromNumber(roomNumber);
		currentRoom.stop();
		currentRoom.unload();
		currentRoom = newRoom;
		currentRoomNumber = roomNumber;
		currentRoom.load();
		currentRoom.start();
	}
	public void gotoNextRoom() {
		int nextRoomNumber = ++currentRoomNumber;
		if (nextRoomNumber >= rooms.size()) throw new IllegalStateException("Cannot go to next room, it doesn't exsist");
		gotoRoom(nextRoomNumber);
	}
	public void gotoPreviousRoom() {
		int prevRoomNumber = --currentRoomNumber;
		if (prevRoomNumber < 0) throw new IllegalStateException("Cannot go to previous room, it doesn't exsist");
		gotoRoom(prevRoomNumber);
	}
	
	private Room getRoomFromNumber(int number) {
		return rooms.get(number);
	}
	
	
//	public void currentRoomAddObject(RoomObject o) {
//		this.currentRoom.addObject(o);
//	}
//	public void currentRoomRemoveObject(RoomObject o) {
//		this.currentRoom.removeObject(o);
//	}
	
//	private Room getRoomFromNumber(int roomNumber) {
//		try {
//			Class<? extends Room> roomClass = roomClasses.get(roomNumber);
//			Constructor<? extends Room> roomConstructor = roomClass.getConstructor();
//			Room newRoom = roomConstructor.newInstance();
//			return newRoom;
//			
//		}
//		catch (NoSuchMethodException | SecurityException e1) {
//			e1.printStackTrace();
//			System.err.println("Could not get constructor");
//			
//		}
//		catch (IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//			System.err.println("Constructor with given arguments doesn't exist");
//		}
//		catch (IllegalAccessException | InstantiationException e) {
//			e.printStackTrace();
//			System.err.println("Could not create room instance");
//		}
//		System.exit(-1);
//		return null;
//	}
}
