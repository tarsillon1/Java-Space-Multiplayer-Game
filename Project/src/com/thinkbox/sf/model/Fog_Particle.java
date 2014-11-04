package com.thinkbox.sf.model;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;

public class Fog_Particle {
	private BufferedImage actor;
	private int x;
	private int y;
	private double factor;
	private double currentFactor = 1;
	private boolean remove;
	private boolean light;
	private double lightR;
	private double lightB;
	
	public Fog_Particle(BufferedImage a, int x1, int y1, boolean l, double r, double b){
		x = x1;
		y = y1;
		actor = a;
		Random ra = new Random();
		double randomValue = 0.009 + (0.023 - 0.009) * ra.nextDouble();
		factor = randomValue;
		Game.getInstance().play.particles.add(this);
		light = l;
		lightR = r;
		lightB = b;
	}
	
	public void setLoc(int x1, int y1){
		x = x1;
		y= y1;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setNegY(boolean b) {
		if(b == true){
			y = y + ServerConstants.Y;
		}
		else
			y = y - ServerConstants.Y;
		
	}

	public void setNegX(boolean b) {
		if(b == true){
			x = x + Game.frame.getWidth();
		}
		else
			x = x - Game.frame.getWidth();
	}
	
	public void act(){
		if(remove == true){
			Game.getInstance().play.particles.remove(this);
		}
	}
	
	public void draw(Graphics g){
		currentFactor -= factor;
		if(currentFactor <= 0){
			remove = true;
		}
		else{
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = new AffineTransform();
			at.translate(x + Game.getInstance().play.map.getDrawX(), y + Game.getInstance().play.map.getDrawY());
			at.scale(currentFactor, currentFactor);
			at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
			g2d.drawImage(actor, at, null);
			if(light == true && currentFactor >= .1){
				Light temp = new Light(x + Game.getInstance().play.map.getDrawX(), y + Game.getInstance().play.map.getDrawY(), 0, 0, lightR, lightB, Images.light);
				temp.drawAtPoint(g);
			}
		}
	}
}
