package graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;



import utils.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	private int width, height;
	private int textureID;
	
	
	private Texture(int textureID, int width, int height) {
		this.textureID = textureID;
		this.width = width;
		this.height = height;
	}
	public Texture(String path) {
		textureID = load(path);
	}
	
	private int load(String path) {
		int[] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] data = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}

		return genGlTexture(data, width, height);
	}
	
	public static Texture[] loadImageStrip(String imagePath, int imageCount) {
		int[][] allPixels = null;
		int width = 0;
		int height = 0;
		Texture[] textures = new Texture[imageCount];
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
			width = image.getWidth()/imageCount;
			height = image.getHeight();
			allPixels = new int[imageCount][width * height];
			for (int i = 0; i < imageCount; i++) {
				int[] currPixels = new int[width * height];
				image.getRGB(width*i, 0, width, height, currPixels, 0, width);
				allPixels[i] = currPixels;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[][] allData = new int[imageCount][width * height];
		for (int j = 0; j < imageCount; j++) {
			int[] currData = new int[width * height];
			int[] pixels = allPixels[j];
			for (int i = 0; i < width * height; i++) {
				int a = (pixels[i] & 0xff000000) >> 24;
				int r = (pixels[i] & 0xff0000) >> 16;
				int g = (pixels[i] & 0xff00) >> 8;
				int b = (pixels[i] & 0xff);

				currData[i] = a << 24 | b << 16 | g << 8 | r;
				
			}
			allData[j] = currData;
		}
		
		
		for (int i = 0; i< imageCount; i++) {	
			int[] currData = allData[i];
			textures[i] = genTexture(currData, width, height);
		}
		return textures;
	}
	
	public static Texture genCircleTexture(int radius) {
		int width = radius*2;
		int height = width;
		int radius_2 = radius * radius;
		int[] data = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			int x = i / width;
			int y = i % width;
			int pixelData = 0;
			int xCentered = radius-x;
			int yCentered = radius-y;
			if ((xCentered*xCentered) + (yCentered*yCentered) < radius_2) {
				pixelData = -1; //1x32
			}
			data[i] = pixelData;


		}
		return genTexture(data, width, height);
	}
	
	public static Texture genRectangleTexture(int width, int height) {
		int[] data = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			int pixelData = 0xffffffff; //-1
			data[i] = pixelData;
		}
		return genTexture(data, width, height);
	}

	public static Texture[] loadFontTexture(String imagePath) {
		ArrayList<Texture> charTextures = new ArrayList<>();
		
		int[] allPixels = null;
		int totWidth = 0;
		int totHeight = 0;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
			totWidth = image.getWidth();
			totHeight = image.getHeight();
			allPixels = new int[totWidth * totHeight];
			image.getRGB(0, 0, totWidth, totHeight, allPixels, 0, totWidth);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean reachedEnd = false;
		int currCol = 0;
		ArrayList<int[]> charColData = new ArrayList<>(); //vertically flipped
		
		while(true) {
			//scan vertical lines
			boolean charEnd = true;
			int[] colData = new int[totHeight];
			for (int currRow = 0; currRow < totHeight; currRow++) {
				int currPixelNumber = currRow*totWidth + currCol;
				
				if (currPixelNumber >= totWidth*totHeight) {
					reachedEnd = true;
					charEnd = true;
					break;
				}
				
				int currPixel = allPixels[currPixelNumber];
				
				int a = (currPixel & 0xff000000) >> 24;
				int r = (currPixel & 0xff0000) >> 16;
				int g = (currPixel & 0xff00) >> 8;
				int b = (currPixel & 0xff);
				
				colData[currRow] = a << 24 | b << 16 | g << 8 | r;
				
				if (a != 0) {
					charEnd = false;
					
				}
			}
			if (charEnd ) {
				if (!charColData.isEmpty()) { //if last coloumn wasn't empty
					//flip data back
					int charWidth = charColData.size();
					int[] charData = new int[charWidth * totHeight];
					int dataNumber = 0;
					for (int rowNumber = 0; rowNumber < totHeight; rowNumber++) {
						for (int[] col : charColData) {
							charData[dataNumber++] = col[rowNumber];
						}
					}
					Texture charTexture = genTexture(charData, charWidth, totHeight);
					charTextures.add(charTexture);
					charColData.clear();
				}
			}
			else {
				charColData.add(colData);
			}
			
			if (reachedEnd) {
				break;
			}
			
			++currCol;
		}

		System.out.println("Finished loading font");
		System.out.println(charTextures.size() + " characters loaded");
		
		return charTextures.toArray(new Texture[charTextures.size()]);
	}
	
	private static Texture genTexture(int[] data, int width, int height) {
		int textureID = genGlTexture(data, width, height);
		return new Texture(textureID, width, height);
	}
	private static int genGlTexture(int[] data, int width, int height) {
		int result = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, result);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
		glBindTexture(GL_TEXTURE_2D, 0);
		return result;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

}
