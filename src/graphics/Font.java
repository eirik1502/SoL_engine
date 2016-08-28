package graphics;

import java.util.HashMap;

import utils.VertexArrayUtils;

public class Font {

	
	private static Font basicFont18 = new Font("res/font_18.png", 4,  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890|§.,:;-_'*~^!#¤%&/()=?`@£${[]}+´\\");

	
	//private GrObject[] chars;
	private HashMap<Character, Glyph> charMappings = new HashMap<>();
	
	private int spaceLength;
	
	
	public Font(String imagePath, int spaceLength, String chars) {
		Texture[] charTextures = Texture.loadFontTexture(imagePath);
		if (charTextures.length != chars.length()) throw new IllegalArgumentException("number of chars given does'nt match number in font image");
		
		this.spaceLength = spaceLength;
		
		for (int i = 0; i < chars.length(); i++) {
			Texture tex = charTextures[i];
			VertexArray va = VertexArrayUtils.createRectangle(tex.getWidth(), tex.getHeight(), 0.1f);
			Glyph glyph = new Glyph(tex, va, (float)tex.getWidth(), (float)tex.getHeight());
			charMappings.put(chars.charAt(i), glyph);
		}
	}
	
	public static Font getStandardFont() {
		return Font.basicFont18;
	}
	
	public boolean containsChar(char c) {
		return charMappings.containsKey(c);
	}
	
	public Glyph getChar(char c) {
		if (! charMappings.containsKey(c)) throw new IllegalArgumentException("trying to get a character that doesn't exsist from a font");
		return charMappings.get(c);
	}
	
	public Glyph[] getChars(String s) {
		Glyph[] objects = new Glyph[s.length()];
		for (int i = 0; i < s.length(); i++) {
			objects[i] = getChar(s.charAt(i));
		}
		return objects;
	}
	
	public int getSpaceLength() {
		return spaceLength;
	}
	
	
}
