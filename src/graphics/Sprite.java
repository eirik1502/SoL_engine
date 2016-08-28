package graphics;

import java.util.Arrays;

import utils.VertexArrayUtils;

public class Sprite extends Renderable {
	
	
	private static final int UPS = 60;
	
	

	private float width;
	private float height;
	private float depth;
	
	private float centerX;
	private float centerY;
	
	
	private VertexArray vertexArray;
	private Texture[] textures;
	private int imageCount;
	private int imagesPerSec = 30;
	
	//private float imageChangeTime = ((float)UPS)/((float)imagesPerSec);
	private float imageSpeed = 1f;
	
	private float imageTimer = 0;
	private int imageIndex = 0;
	
	
	private AnimationListener animationEndListener = null;
	
	public Sprite(String imageFilename, int imageCount, float centerX, float centerY) {
		this(imageFilename, imageCount, centerX, centerY, 0f, 0f, 0f, 0f);
	}
	public Sprite(String imageFilename, int imageCount, float centerX, float centerY, float depth) {
		this(imageFilename, imageCount, centerX, centerY, 0f, 0f, 0f, depth);
	}
	public Sprite(String imageFilename, int imageCount, float centerX, float centerY, float startX, float startY, float rotation, float depth) {
		super(startX, startY, rotation, depth);
		
		textures = Texture.loadImageStrip(imageFilename, imageCount);
		width = textures[0].getWidth();
		height = textures[0].getHeight();
		
		this.centerX = centerX;
		this.centerY = centerY;
		
		vertexArray = VertexArrayUtils.createRectangle( width, height, 0);
		
		this.imageCount = imageCount;
	}
	


	
	
	public VertexArray getVertexArray() {
		return vertexArray;
	}
	
	public Texture getTexture() {
		return textures[imageIndex];
	}
	
	//private int updates = 0;
	public void update() {
		//System.out.println("Image timer: " + imageTimer);
		if (imageTimer >= UPS) {
			//System.out.println("Changing image after updates: " + updates);
			if (++imageIndex == imageCount) {
				imageIndex = 0;
				//System.out.println("Updates for animation: " + updates);
				//updates = 0;
				animationEnd();
			}
			imageTimer = 0;
		}
		imageTimer += ((float)imagesPerSec)*imageSpeed;
		//if (imageSpeed > 0) updates++;
	}
	
	
	public void setOnAnimationEnd(AnimationListener animationEndListener) {
		this.animationEndListener = animationEndListener;
	}
	private void animationEnd() {
		if (animationEndListener != null) {
			animationEndListener.onAction(this);
		}
	}
	
	
	public int getImageCount() {
		return imageCount;
	}
	public void setImageIndex(int index) {
		imageIndex = index;
	}
	public int getImageIndex() {
		return imageIndex;
	}
	public void setImageSpeed(float speed) {
		imageSpeed = speed;
	}
	public float getImageSpeed() {
		return imageSpeed;
	}
	
	public float getCenterX() {
		return centerX;
	}
	public float getCenterY() {
		return centerY;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	public float getDepth() {
		return depth;
	}
	
}
