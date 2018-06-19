package mainPackage;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 16, height = 16;
	
	public static BufferedImage grass, dirt, stone, water, lava;
	
	public static BufferedImage player;
	
	public static void init() {
		//tiles
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		grass = sheet.crop(width * 3, 0, width, height);
		dirt = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width, 0, width, height);
		water = sheet.crop(width * 22, height * 3, width, height);
		lava = sheet.crop(width * 21, height * 3, width, height);
		
		//entities
		SpriteSheet entitySheet = new SpriteSheet(ImageLoader.loadImage("/textures/entitySheet.png"));
		player = entitySheet.crop(width * 7, 0, width, height);
	}
}
