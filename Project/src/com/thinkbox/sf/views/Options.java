package com.thinkbox.sf.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.model.Button;
import com.thinkbox.sf.multiplayer.Client;

public class Options {
	public Rectangle exit = new Rectangle(
			(int) (Game.frame.getWidth() / 2) - 101,
			(int) (Game.frame.getHeight() - 100), 200, 50);

	public Button resume = new Button((Game.frame.getWidth() / 2) - 125,
			(Game.frame.getHeight() / 2) - 138, "Resume", Images.button, 250,
			76);
	public Button options = new Button((Game.frame.getWidth() / 2) - 125,
			(Game.frame.getHeight() / 2) - 38, "Settings", Images.button, 250,
			76);
	public Button menu = new Button((Game.frame.getWidth() / 2) - 125,
			(Game.frame.getHeight() / 2) + 62, "Exit", Images.button, 250, 76);
	private boolean open;

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean b) {
		open = b;
	}

	public Options() {
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
		return dimg;
	}

	public void render(Graphics g) {
		FontMetrics metrics = g
				.getFontMetrics(new Font("Arial", Font.PLAIN, 30));
		if (open) {
			g.drawImage(Images.optionsB, (Game.frame.getWidth() / 2)
					- (Images.optionsB.getWidth() / 2),
					(Game.frame.getHeight() / 2)
							- (Images.optionsB.getHeight() / 2), null);
			resume.setVisible(true);
			int textwidth = metrics.stringWidth("Resume");
			resume.setOffsetX((resume.getSizeX() / 2) - (textwidth / 2));
			resume.setFont(30);
			menu.setVisible(true);
			textwidth = metrics.stringWidth("Exit");
			menu.setOffsetX((menu.getSizeX() / 2) - (textwidth / 2));
			menu.setFont(30);
			options.setVisible(true);
			textwidth = metrics.stringWidth("Settings");
			options.setOffsetX((options.getSizeX() / 2) - (textwidth / 2));
			options.setFont(30);
			if (menu.getClicked() == true && Game.state == GameState.GAME) {
				try {
					Client.in.close();
					Client.out.close();
					Client.bw.close();
					Client.socket.close();
				} catch (IOException e) {

				}
				Game.getInstance().play.chat.getTextbox().setVisible(false);
				Client.running = false;
				Game.getInstance().play.chat.show(false);
				Game.state = GameState.MENU;
				menu.released();
			} else {
				if (menu.getClicked() == true)
					System.exit(0);
			}

			if (options.getClicked() == true) {
				open = false;
				Game.getInstance().settings.setOpen(true);
			}

			if (resume.getClicked() == true) {
				open = false;
			}
		} else {
			resume.setVisible(false);
			menu.setVisible(false);
			options.setVisible(false);
		}
	}
}


