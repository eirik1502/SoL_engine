package physics;

public class PhysicsTest {
	
	public static boolean testCircleRectCorner1(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(1.6f, 1.6f, 5, 5);		
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static boolean testCircleRectCorner2(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(-1.6f, 1.6f, 3.2f, 5);		
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static boolean testCircleRectCorner3(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(0, 0, 0.9f, 0.9f);		
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static boolean testCircleRectCorner4(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(1.8f, 0, 2, 0.8f);		
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static boolean testCircleRectBoxOverlap(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(0.7f, 0, 1, 1);
		return PhysicsHandeler.isCollision(circ, rect);

	}
	
	public static void main(String[] args){
		System.out.println(testCircleRectCorner1());
		System.out.println(testCircleRectCorner2());
		System.out.println(testCircleRectCorner3());
		System.out.println(testCircleRectCorner4());
		System.out.println(testCircleRectBoxOverlap());
		

		
	}

}
