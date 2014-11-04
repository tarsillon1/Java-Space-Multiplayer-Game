package com.thinkbox.sf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;

public class PopUp {
	private int x;
	private int y;
	private String text;
	private boolean show;
	private Label label;
	private Rectangle rect;
	private FontMetrics font;
	private Button button;
	private Color backColor = Color.GRAY;
	private Color borderColor = Color.BLACK;
	private Color labelColor = Color.WHITE;
	
	
	public PopUp(int x, int y, String text, int sX, int sY){
		this.setX(x);
		this.setY(y);
		this.text = text;
		rect = new Rectangle(x, y, sX, sY);
		font = Game.frame.getFontMetrics(new Font("Arial", Font.PLAIN, 14));
		int textwidth = font.stringWidth(text);
		label = new Label(x + (sX / 2) - (textwidth / 2), y + (sY / 2) + 7, text);
		button = new Button(x - 4, y, "", Images.button2, 42, 20);
		Game.getInstance().play.popups.add(this);
	}
	
	public void dispose(){
		Game.getInstance().play.popups.remove(this);
	}
	
	public void draw(Graphics g){
		label.setColor(labelColor);
		g.setColor(backColor);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(borderColor);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
		if(button.getClicked()){
			button.dispose();
			label.dispose();
			dispose();
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

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Color getColor() {
		return borderColor;
	}

	public void setColor(Color color) {
		this.borderColor = color;
	}

	public void setText(String t) {
		text = t;
	}

	public String getText() {
		return text;
	}

	public Color getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}
	
	
}
