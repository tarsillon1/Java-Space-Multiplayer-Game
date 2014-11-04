package com.thinkbox.sf.utils;


import java.io.IOException;

import com.thinkbox.sf.constants.Images;



public class ResourceLoader {
	private static BufferedImageLoader imageLoader = new BufferedImageLoader();

	public static void loadImages() {
		try {
			Images.loading = imageLoader.loadImage("loading.png");
			Images.logo = imageLoader.loadImage("logo.png");
			Images.check = imageLoader.loadImage("check.png");
			Images.noCheck = imageLoader.loadImage("noCheck.png");
			Images.title = imageLoader.loadImage("title.png");
			Images.play = imageLoader.loadImage("play.png");
			Images.options = imageLoader.loadImage("options.png");
			Images.optionsB = imageLoader.loadImage("optionsback.png");
			Images.exit = imageLoader.loadImage("exit.png");
			Images.fog = imageLoader.loadImage("Smoke.png");
			Images.tile = imageLoader.loadImage("genericTile.png");
			Images.ship1Stand = imageLoader.loadImage("ship1Stand.png");
			Images.ship1Move = imageLoader.loadImage("ship1Move.png");
			Images.fire = imageLoader.loadImage("fire.png");
			Images.bullet1 = imageLoader.loadImage("bullet.png");
			Images.mainBack = imageLoader.loadImage("hostBack.png");
			Images.clientBack = imageLoader.loadImage("clientBack.png");
			Images.hover = imageLoader.loadImage("hover.png");
			Images.textbox = imageLoader.loadImage("textbox.png");
			Images.button = imageLoader.loadImage("button.png");
			Images.button2 = imageLoader.loadImage("button2.png");
			Images.star = imageLoader.loadImage("star.png");
			Images.biome1 = imageLoader.loadImage("biome1.png");
			Images.loadingBack = imageLoader.loadImage("loadingback.png");
			Images.light = imageLoader.loadImage("light.png");
			Images.dark = imageLoader.loadImage("dark.png");
			Images.asteriod = imageLoader.loadImage("asteriod.png");
			Images.asteriodDeath1 = imageLoader.loadImage("asteriodDeath1.png");
			Images.asteriodDeath2 = imageLoader.loadImage("asteriodDeath2.png");
			Images.asteriodDeath3 = imageLoader.loadImage("asteriodDeath3.png");
			Images.asteriodDeath4 = imageLoader.loadImage("asteriodDeath4.png");
			Images.asteriodDeath5 = imageLoader.loadImage("asteriodDeath5.png");
			Images.asteriodDeath6 = imageLoader.loadImage("asteriodDeath6.png");
			
		} catch (IOException e) {
		}
	}
}
