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
	
	public static boolean testCircleRectBoxOverlap1(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(0.7f, 0, 1, 1);
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static boolean testCircleRectBoxOverlap2(){
		PhCircle circ = new PhCircle(1,1,1);
		PhRectangle rect = new PhRectangle(0.9f, 0.9f, 0.5f, 0.5f);
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static boolean testCircleRectBoxOverlap3(){
		PhCircle circ = new PhCircle(1,1,0.2f);
		PhRectangle rect = new PhRectangle(0.8f,0.8f, 1, 1);
		return PhysicsHandeler.isCollision(circ, rect);
	}
	
	public static void main(String[] args){
		System.out.println("Test 1, circle and rectangle coliding in corner. \nExpected: true \nResult: " + testCircleRectCorner1() +"\n");
		System.out.println("Test 2, circle and rectangle coliding in corner. \nExpected: true \nResult: " + testCircleRectCorner2() + "\n");
		System.out.println("Test 3, circle and rectangle coliding in corner. \nExpected: true \nResult: " + testCircleRectCorner3() + "\n");
		System.out.println("Test 4, circle and rectangle coliding in corner. \nExpected: true \nResult: " + testCircleRectCorner4() + "\n");
		System.out.println("Test 5, circle and rectangle coliding between corners of rectangle. \nExpected: true \nResult: " + testCircleRectBoxOverlap1() + "\n");
		System.out.println("Test 6, rectangle inside a circle. \nExpected: true \nResult: " + testCircleRectBoxOverlap2() + "\n");
		System.out.println("Test 8, circle inside a rectangle. \nExpected: true \nResult: " + testCircleRectBoxOverlap3() + "\n");

		
	}

}
