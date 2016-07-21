package userInput;


public class InputState {


    private static float mouseX;
    private static float mouseY;

    private static boolean[] keyHeld = new boolean[256*2];
    private static boolean[] mouseButtonHeld = new boolean[16];


    static {
        mouseX = 0;
        mouseY = 0;

        for (int i = 0; i < keyHeld.length; i++) {
           keyHeld[i] = false;
        }
        for (int i = 0; i < mouseButtonHeld.length; i++) {
            mouseButtonHeld[i] = false;
         }
    }


    public static float getMouseX() {
        return mouseX;
    }
    public static float getMouseY() {
        return mouseY;
    }
    public static boolean isMousePressed( int mouseButton ) {
        return mouseButtonHeld[mouseButton];
    }
    public static boolean isKeyboardPressed( int keyCode) {
        return keyHeld[keyCode];
    }


    static void setMouseX( float x ) {
        mouseX = x;
    }
    static void setMouseY( float y ) {
        mouseY = y;
    }

    static void setMousePressed( int mouseButton, boolean value ) {
        mouseButtonHeld[mouseButton] = value;
    }
    static void setKeyboardPressed( int keyCode, boolean value ) {
        keyHeld[keyCode] = value;
    }


}
