package com.thinkbox.sf.control;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.Fog_Particle;

public class GenerateFog {
	private boolean create;
	private int fog;

	public GenerateFog() {
	}

	public void setCreate(boolean b) {
		create = b;
	}

	public void generate() {
		if (create == true) {
			fog += 1;
			if (fog == 2) {
				int randomValueX = -ServerConstants.X
						+ (int) (Math.random() * (((ServerConstants.X * 2) + Game.frame
								.getWidth()) + 1));
				int randomValueY = -ServerConstants.Y
						+ (int) (Math.random() * (((ServerConstants.Y * 2) + Game.frame
								.getHeight()) + 1));
				new Fog_Particle(Images.fog, randomValueX, randomValueY, false, 0, 0);
				randomValueX = 1 + (int) (Math.random() * ((Game.frame
						.getWidth() - 1) + 1));
				randomValueY = 1 + (int) (Math.random() * ((Game.frame
						.getHeight() - 1) + 1));
				new Fog_Particle(Images.fog, randomValueX, randomValueY, false, 0, 0);
				fog = 0;
			}
		}
	}
}
