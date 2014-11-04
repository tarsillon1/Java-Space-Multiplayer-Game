package com.thinkbox.sf.multiplayer;

import java.awt.image.BufferedImage;

public class ImageConverter {
	private int totalImages;
	private int current;
	private BufferedImage[] images;
	
	public ImageConverter(int t){
		totalImages = t;
		images = new BufferedImage[totalImages];
	}
	
	public void createConversion(BufferedImage b){
		current += 1;
		images[current] = b;
	}
	
	public int getConvertion(BufferedImage b){
		for(int i = 0; i <= current; i++){
			if(images[i] == b){
				return i;
			}
		}
		return 0;
	}
	
	public BufferedImage getImage(int i){
				return images[i];
	}
}
