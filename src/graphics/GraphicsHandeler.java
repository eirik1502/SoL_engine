package graphics;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;


import java.util.ArrayList;


import maths.Matrix4f;
import maths.Vector3f;
import maths.Vector4f;


public class GraphicsHandeler {


	public static float WINDOW_WIDTH, WINDOW_HEIGHT;

	private long window;

	//private RenderObject renderObject;
	private Shader shader;
	
	private final Camera nullCamera;
	

	
	public GraphicsHandeler(long window, float width, float height) {
		WINDOW_WIDTH = width;
		WINDOW_HEIGHT = height;
		this.window = window;
		//renderObject = new RenderObject();
		shader = new Shader("shaders/shader.vert", "shaders/shader.frag");
		shader.bind();
		Matrix4f pr_matrix = Matrix4f.orthographic(0, width, height, 0, -1.0f, 1.0f);
		shader.setUniformMat4f("pr_matrix", pr_matrix);
		shader.unbind();
		
		nullCamera = new Camera(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, WINDOW_WIDTH, WINDOW_HEIGHT);
	}


	public long getWindow() {
		return window;
	}

	public boolean windowCloseRequest() {
		return glfwWindowShouldClose(window);
	}
	
	public void render(ArrayList<? extends GraphicsEntity> entities) {
		render(nullCamera, entities);
	}
	
	public void render(Camera camera, ArrayList<? extends GraphicsEntity> entities) { 

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		float addX = camera.getX()-WINDOW_WIDTH/2;
		float addY = camera.getY()-WINDOW_HEIGHT/2;
		for (GraphicsEntity e : entities) {
			renderEntity(e, addX, addY);
		}
		
		glfwSwapBuffers(window);
		
		int error = glGetError();
		if (error != GL_NO_ERROR)
			System.err.println("GL error: " + error);
	}
	
	
	private void renderEntity(GraphicsEntity entity, float addX, float addY) {
		for (Sprite s : entity.getSprites()) {
			this.renderSprite(s, addX, addY);
		}
		for (Text t : entity.getTexts()) {
			this.renderText(t, addX, addY);
		}
	}
	

	
//	private void renderEntity( Camera camera, GraphicsEntity e) {
//		float dx = e.getX();
//		float dy = e.getY();
//		float rotation = e.getRotation();
//		
//		Sprite s = e.getSprite();
//		VertexArray va = s.getVertexArray();
//		Texture tex = s.getTexture();
//		float cx = s.getCenterX();
//		float cy = s.getCenterY();
//		
//		shader.bind();
//		tex.bind();
//		va.bind();
//		Vector3f cameraPos = new Vector3f(camera.getX()-WINDOW_WIDTH/2, camera.getY()-WINDOW_HEIGHT/2, 0f);
//		shader.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(dx, dy, 0.0f).subtract( cameraPos ) ));
//		Matrix4f modelTranslation = Matrix4f.translate(new Vector3f(-cx, -cy, 0) );
//		Matrix4f modelRotation = Matrix4f.rotate(rotation);
//		shader.setUniformMat4f("ml_matrix", modelRotation.multiply(modelTranslation)  );
//		shader.setUniform4f("absoluteColor", new Vector4f(-1.0f, 0f, 0f, 0f));
//		va.draw();
//		
//		va.unbind();
//		tex.unbind();
//		shader.unbind();
//	}
	
	public void renderSprite(Sprite sprite, float addX, float addY) {
		float cx = sprite.getCenterX();
		float cy = sprite.getCenterY();
		float depth = sprite.getDepth();
		
		float dx = sprite.getX() + addX;
		float dy = sprite.getY() + addY;
		float rotation = sprite.getRotation();
		VertexArray va = sprite.getVertexArray();
		Texture tex = sprite.getTexture();
		
		Matrix4f modelMat = Matrix4f.rotate(rotation).multiply( Matrix4f.translate(new Vector3f(-cx, -cy, depth)) );
		Matrix4f viewMat = Matrix4f.translate(new Vector3f(dx, dy, 0.0f));
		
		shader.bind();
		tex.bind();
		va.bind();
		shader.setUniformMat4f("ml_matrix", modelMat);
		shader.setUniformMat4f("vw_matrix", viewMat);
		shader.setUniform4f("absoluteColor", new Vector4f(-1.0f, 0f, 0f, 0f));
		va.draw();
		
		va.unbind();
		tex.unbind();
		shader.unbind();
		
	}
	
	
	private void renderText(Text text, float addX, float addY) {
		String string = text.getString();
		Font font = text.getFont();
		Color color = Color.BLACK();
		float dx = text.getX() + addX;
		float dy = text.getY() + addY;
		float rotation = text.getRotation();
		int textHeight = text.getSize();
		
		int charSpacing = 2;
		//int charSpacing = 16;
		
		//int spaceWidth = charSpacing -2;
		
		int rowNumber = 0;
		float rowSpacing = 0;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == ' ') {
				rowSpacing += font.getSpaceLength();
			}
			else if (c == '\n') {
				rowNumber++;
				rowSpacing = -charSpacing;
			}
			else if (font.containsChar(c)) {
				Glyph glyph = font.getChar(c);
				drawTextCharacter(glyph, color, dx +rowSpacing, dy +rowNumber*textHeight, rotation);
				rowSpacing += glyph.getWidth();
			}
			rowSpacing += charSpacing;
		}
	}
	
	private void drawTextCharacter(Glyph object, Color color, float dx, float dy, float rotation) {
		VertexArray va = object.getVertexArray();
		Texture tex = object.getTexture();
		
		float cx, cy;
		cx = cy = 0;
		
		shader.bind();
		tex.bind();
		va.bind();
		shader.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(dx, dy, 0.0f)));
		Matrix4f modelTranslation = Matrix4f.translate(new Vector3f(-cx, -cy, 0));
		Matrix4f modelRotation = Matrix4f.rotate(rotation);
		shader.setUniformMat4f("ml_matrix", modelRotation.multiply(modelTranslation)  );
		shader.setUniform4f("absoluteColor", color.color);
		va.draw();
		
		va.unbind();
		tex.unbind();
		shader.unbind();
	}
	
//	private void drawObjectAbsoluteColor(GrObject object, Color color, float dx, float dy, float rotation) {
//		VertexArray va = object.getVertexArray();
//		Texture tex = object.getTexture();
//		
//		float cx, cy;
//		cx = cy = 0;
//		
//		shader.bind();
//		tex.bind();
//		va.bind();
//		shader.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(dx, dy, 0.0f)));
//		Matrix4f modelTranslation = Matrix4f.translate(new Vector3f(-cx, -cy, 0));
//		Matrix4f modelRotation = Matrix4f.rotate(rotation);
//		shader.setUniformMat4f("ml_matrix", modelRotation.multiply(modelTranslation)  );
//		shader.setUniform4f("absoluteColor", color.color);
//		va.draw();
//		
//		va.unbind();
//		tex.unbind();
//		shader.unbind();
//	}
	
	


}
