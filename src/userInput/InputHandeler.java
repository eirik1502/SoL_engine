package userInput;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;



public class InputHandeler {


	private long inputSource;


	public InputHandeler(long inputSource) {
		this.inputSource = inputSource;
		addInputListeners();
	}
	
	private void addInputListeners() {
		
		glfwSetMouseButtonCallback( inputSource, (window, button, action, mods) -> {
			if (action == GLFW_RELEASE) {
				InputState.setMousePressed(button, false);
			}
			else if (action == GLFW_PRESS) {
				InputState.setMousePressed(button, true);
			}
		});
		glfwSetCursorPosCallback( inputSource, (window, xpos, ypos) -> {
			InputState.setMouseX( (float)xpos );
			InputState.setMouseY( (float)ypos );
		});
        glfwSetKeyCallback(inputSource, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
            	glfwSetWindowShouldClose(window, true);
            }
            if (action == GLFW_RELEASE){
            	InputState.setKeyboardPressed(key, false);
            }
            else if (action == GLFW_PRESS){
            	InputState.setKeyboardPressed(key, true);
            }
        });

	}

}