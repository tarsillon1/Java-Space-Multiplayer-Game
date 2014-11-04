package com.thinkbox.sf.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.multiplayer.ImageConverter;

public class BufferedImageLoader {
	private BufferedImage image;
	public static ImageConverter converter = new ImageConverter(GameConstants.IMAGES);

	public BufferedImage loadImage(String imagePath) throws IOException {
		image = ImageIO
				.read(new File(GameConstants.SPRITE_LOCATION + imagePath));
		converter.createConversion(image);
		return image;
	}
}
