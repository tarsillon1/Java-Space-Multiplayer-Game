package com.thinkbox.sf.model;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.control.ModularLocation;

public class Light {
	private BufferedImage actor;
	private double brightness;
	private double radius;
	private int tileX;
	private int tileY;
	private int x;
	private int y;
	
	public Light(int x, int y, int tX, int tY, double r, double br, BufferedImage b){
		actor = b;
		brightness = br;
		radius = r;
		this.x = x;
		this.y = y;
		tileX = tX;
		tileY = tY;
	}
	
	
	public void draw(Graphics g){
		Random r = new Random();
		double off = -0.04 + (0.04 + 0.04) * r.nextDouble();
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		at.translate(ModularLocation.convertTileX(x + Game.getInstance().play.map.getDrawX(), tileX), ModularLocation.convertTileY(y + Game.getInstance().play.map.getDrawY(), tileY));
		at.scale(radius, radius);
		at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
		float opacity;
		if(brightness + off <= 1 && brightness + off >= 0)
			opacity = (float) ((float) brightness + off);
		else
			opacity = (float) brightness;
		g2d.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, opacity));
		g2d.drawImage(actor, at,
				null);
		g2d.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1));
	}

	public void drawAtPoint(Graphics g) {
			Random r = new Random();
			double off = -0.04 + (0.04 + 0.04) * r.nextDouble();
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = new AffineTransform();
			at.translate(x, y);
			at.scale(radius, radius);
			at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
			float opacity;
			if(brightness + off <= 1 && brightness + off >= 0)
				opacity = (float) ((float) brightness + off);
			else
				opacity = (float) brightness;
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, opacity));
			g2d.drawImage(actor, at,
					null);
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 1));
		}
}
