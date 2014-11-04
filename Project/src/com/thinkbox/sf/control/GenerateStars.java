package com.thinkbox.sf.control;

import java.util.Random;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.Fog_Particle;

public class GenerateStars {
	private boolean create;
	private int stars;
	private int random = 10;

	public GenerateStars() {
	}

	public void setCreate(boolean b) {
		create = b;
	}

	public void generate() {
		if (create == true) {
			stars += 1;
			
			if (stars == random) {
				int randomValueX = -ServerConstants.X
						+ (int) (Math.random() * (((ServerConstants.X * 2) + Game.frame
								.getWidth()) + 1));
				int randomValueY = -ServerConstants.Y
						+ (int) (Math.random() * (((ServerConstants.Y * 2) + Game.frame
								.getHeight()) + 1));
				randomValueX = 1 + (int) (Math.random() * ((Game.frame
						.getWidth() - 1) + 1));
				randomValueY = 1 + (int) (Math.random() * ((Game.frame
						.getHeight() - 1) + 1));
				new Fog_Particle(Images.star, randomValueX, randomValueY, true, 0.04, 0.175);
				stars = 0;
				Random rand = new Random();
				random = rand.nextInt((20 - 10) + 1) + 10;
			}
		}
	}
}
