package com.thinkbox.sf.control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ScreenImage {
	private int current;
	private BufferedImage message;
	private int x;
	private int y;

	public ScreenImage(int c, int x1, int y1, BufferedImage m) {
		current = c;
		message = m;
		x = x1;
		y = y1;
	}

	public void newMessage(BufferedImage m, int c, int x1, int y1) {
		message = m;
		current = c;
		x = x1;
		y = y1;
	}

	public void draw(Graphics g) {
		current -= 10;
		if (current >= 0) {
			g.drawImage(message, x, y, null);
		} else
			current = 0;
	}
}
