package com.thinkbox.sf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;

public class Textbox extends KeyAdapter {
	private int x;
	private int y;
	private String text = "";
	private BufferedImage actor;
	private boolean show = true;
	private boolean visible = true;
	private boolean selected = false;
	private FontMetrics font;
	private int sizeX;
	private int sizeY;
	private int c;
	private int maxChars;
	
	public Textbox(int x, int y, BufferedImage b, int m, int xs, int ys){
		this.setX(x);
		this.setY(y);
		setActor(b);
		setMaxChars(m);
		sizeX = xs;
		sizeY = ys;
		Game.getInstance().play.textboxs.add(this);
	}
	
	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}
	
	public void dispose(){
		Game.getInstance().play.textboxs.remove(this);
	}

	public void draw(Graphics g){
		if(visible == true){
			resize(actor, sizeX, sizeY, g, x, y);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 11));
			g.drawString(text, x + 5, y + (actor.getHeight() / 2) + 4);
			font = g.getFontMetrics(new Font("Arial", Font.PLAIN, 11));
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, sizeX, sizeY);
	}
	
	
	
	public void clicked(){
		selected = true;
		try {
			for (Textbox aText : Game.getInstance().play.textboxs) {
				if(aText != this){
					aText.setSelected(false);
				}
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(selected == true && show == true && e.getKeyCode() != KeyEvent.VK_ENTER){
			int textwidth = font.stringWidth(text);
			if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE)
				if(textwidth < this.getRect().getWidth() - 10  && e.getKeyChar() != KeyEvent.CHAR_UNDEFINED){
					text = text + e.getKeyChar();
					c += 1;
				}
				else{
				}
			else if(c > 0){
				text = text.substring(0, text.length() - 1);
				c -= 1;
			}
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public boolean isVisible(){
		return visible;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
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

	public void setVisible(boolean b) {
		visible = b;
	}

	public int getMaxChars() {
		return maxChars;
	}

	public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}
	
	
}
