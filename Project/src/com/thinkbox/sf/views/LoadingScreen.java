package com.thinkbox.sf.views;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.model.Label;

public class LoadingScreen {
	private BufferedImage b;
	private boolean visible = false;
	private Label label;
	private int step;
	private int number;

	public LoadingScreen(BufferedImage b) {
		this.b = b;
		label = new Label(50, Game.frame.getHeight() - 75, "");
		label.setShow(false);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public BufferedImage getImage() {
		return b;
	}

	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}

	public void draw(Graphics g) {
		try {
			if (isVisible() && step <= 20)
				resize(Images.clientBack, Game.frame.getWidth(),
						Game.frame.getHeight(), g, 0, 0);
			if ((step * 0.05) < 1 && step <= 20) {
				Graphics2D g2d = (Graphics2D) g;
				float opacity = (float) (step * 0.05);
				g2d.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, opacity));
				label.setOpacity(opacity);
			}
			step += 1;
			if (isVisible()) {
				Game.getInstance().loading.getLabel().setColor(Color.white);
				Game.getInstance().loading.getLabel().setSize(40);
				if (step % 10 == 0) {
					number += 1;
					if (number == 4) {
						number = 0;
					}
					String dots = "";
					for (int i = 0; i < number; i++) {
						dots = dots + ".";
					}
					Game.getInstance().loading.getLabel().setText(
							"Loading World" + dots);
				}
				if (((step - 280) * 0.05) < 1 && step > 280) {
					Graphics2D g2d = (Graphics2D) g;
					float opacity = (float) (1 - ((step - 280) * 0.05));
					g2d.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, opacity));
					label.setOpacity(opacity);
				}
				Game.getInstance().play.chat.show(false);
				if (step == 300) {
					setVisible(false);
					Game.getInstance().play.chat.show(true);
				} else {
					label.setShow(true);
					resize(b, Game.frame.getWidth(), Game.frame.getHeight(), g,
							0, 0);
				}
			} else {
				label.setShow(false);
				step = 0;
			}
			Graphics2D g2d = (Graphics2D) g;
			float opacity = 1f;
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, opacity));
		} catch (NullPointerException e) {

		}
	}

	public void setImage(BufferedImage b) {
		this.b = b;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
