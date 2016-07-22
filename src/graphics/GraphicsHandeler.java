package graphics;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import maths.Matrix4f;
import maths.Vector3f;
import maths.Vector4f;
import rooms.Entity;
import rooms.Text;


public class GraphicsHandeler {




	private long window;

	
	//private RenderObject renderObject;
	private Shader shader;
	

	
	public GraphicsHandeler(long window, float width, float height) {
		this.window = window;
		//renderObject = new RenderObject();
		shader = new Shader("shaders/shader.vert", "shaders/shader.frag");
		shader.bind();
		Matrix4f pr_matrix = Matrix4f.orthographic(0, width, height, 0, -1.0f, 1.0f);
		shader.setUniformMat4f("pr_matrix", pr_matrix);
		shader.unbind();
	}



	
	public void render(ArrayList<GraphicsEntity> entities) { //, Text[] texts) {

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
//		for (Renderable renderable : renderables) {
//			renderable.render( renderObject );
//		}
		renderEntities( entities.toArray(new GraphicsEntity[0]) );
		//renderTexts(texts);
		
		glfwSwapBuffers(window);
		
		int error = glGetError();
		if (error != GL_NO_ERROR)
			System.err.println("GL error: " + error);
	}
	
	private void renderEntities(GraphicsEntity[] entities) {
		
		for (GraphicsEntity e : entities) {
			renderEntity( e);
		}
	}
	
	private void renderTexts( Text[] texts) {
		for (Text t : texts) {
			renderText(t);
		}
	}
	
	private void renderEntity( GraphicsEntity e) {
		renderSprite(e.getSprite(), e.getX(), e.getY(), e.getRotation());
	}
	
	public void renderSprite(Sprite image, float dx, float dy, float rotation) {

		//double rotationDegree = (180*rotation)/Math.PI;
		VertexArray va = image.getVertexArray();
		Texture tex = image.getTexture();
		float cx = image.getCenterX();
		float cy = image.getCenterY();
		
		shader.bind();
		tex.bind();
		va.bind();
		shader.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(dx, dy, 0.0f)));
		Matrix4f modelTranslation = Matrix4f.translate(new Vector3f(-cx, -cy, 0));
		Matrix4f modelRotation = Matrix4f.rotate(rotation);
		shader.setUniformMat4f("ml_matrix", modelRotation.multiply(modelTranslation)  );
		shader.setUniform4f("absoluteColor", new Vector4f(-1.0f, 0f, 0f, 0f));
		va.draw();
		
		va.unbind();
		tex.unbind();
		shader.unbind();
		
	}
	
	
	public void renderText(Text text) {
		String string = text.getString();
		Font font = text.getFont();
		Color color = Color.BLACK();
		float dx = text.getX();
		float dy = text.getY();
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
