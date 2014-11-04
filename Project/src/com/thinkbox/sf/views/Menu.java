package com.thinkbox.sf.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Menu {
	public Rectangle play, options, quit;
	public boolean[] lastColor = new boolean[4];

	public Menu() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		play = new Rectangle((int) ((width / 2) - (Images.play.getWidth() / 2)), 250, Images.play.getWidth(), Images.play.getHeight() - 90);
		options = new Rectangle((int) ((width / 2) - (Images.options.getWidth() / 2)), 370, Images.options.getWidth(), Images.play.getHeight() - 90);
		quit = new Rectangle((int) ((width / 2) - (Images.exit.getWidth() / 2)), 490, Images.exit.getWidth(), Images.play.getHeight() - 90);
	}
	
	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		resize(Images.mainBack, Game.frame.getWidth() + 100, Game.frame.getHeight() + 50, g, -50 , 0);
		g.drawImage(Images.title, (Game.frame.getWidth() / 2) - (Images.title.getWidth() / 2), 40, null);
		if (MouseInput.MOUSE.intersects(play) && MouseInput.MOUSE != null
				&& !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()) {
			if (lastColor[1] == false) {
				AudioPlayer.getSound(Audio.hover).play();
				lastColor[1] = true;
			}
			g.drawImage(Images.hover, (Game.frame.getWidth() / 2) - (Images.hover.getWidth() / 2), 200, null);
		} else {
			lastColor[1] = false;
		}
		g.drawImage(Images.play, (Game.frame.getWidth() / 2) - (Images.play.getWidth() / 2), 200, null);
		if (MouseInput.MOUSE.intersects(options) && MouseInput.MOUSE != null
				&& !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()) {
			if (lastColor[2] == false) {
				AudioPlayer.getSound(Audio.hover).play();
				lastColor[2] = true;
			}
			g.drawImage(Images.hover, (Game.frame.getWidth() / 2) - (Images.hover.getWidth() / 2), 320, null);
		} else {
			lastColor[2] = false;
		}
		g.drawImage(Images.options, (Game.frame.getWidth() / 2) - (Images.options.getWidth() / 2), 320, null);
		if (MouseInput.MOUSE.intersects(quit) && MouseInput.MOUSE != null
				&& !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()) {
			if (lastColor[3] == false) {
				AudioPlayer.getSound(Audio.hover).play();
				lastColor[3] = true;
			}
			g.drawImage(Images.hover, (Game.frame.getWidth() / 2) - (Images.hover.getWidth() / 2), 440, null);
		} else {
			lastColor[3] = false;
		}
		g.drawImage(Images.exit, (Game.frame.getWidth() / 2) - (Images.exit.getWidth() / 2), 440, null);
	}

	public void playMusic() {
		AudioPlayer.getSound(Audio.menuMusic).loop();
	}
}
