package com.thinkbox.sf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;

public class Button {
	private int x;
	private int y;
	private int font = 14;
	private String text;
	private BufferedImage actor;
	private int sizeX;
	private int sizeY;
	private int offsetX;
	private Color color = Color.BLACK;
	private boolean clicked;
	private boolean visible = true;
	
	public Button(int x, int y, String t, BufferedImage b, int sizeX, int sizeY){
		this.setX(x);
		this.setY(y);
		this.setSizeX(sizeX);
		this.setSizeY(sizeY);
		setText(t);
		setActor(b);
		Game.getInstance().play.buttons.add(this);
	}
	
	public void dispose(){
		Game.getInstance().play.buttons.remove(this);
	}
	
	public void clicked(){
		clicked = true;
	}
	public void released(){
		clicked = false;
	}
	
	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, sizeX, sizeY);
	}
	
	public void draw(Graphics g){
		if(isVisible()){
			resize(actor, sizeX, sizeY, g, x, y);
			g.setColor(color);
			g.setFont(new Font("Arial", Font.PLAIN, font));
			g.drawString(text, x + offsetX, y + (sizeY / 2) + 3);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BufferedImage getActor() {
		return actor;
	}

	public void setActor(BufferedImage actor) {
		this.actor = actor;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public void setColor(Color white) {
		color = white;
	}

	public boolean getClicked() {
		return clicked;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getFont() {
		return font;
	}

	public void setFont(int font) {
		this.font = font;
	}
}
