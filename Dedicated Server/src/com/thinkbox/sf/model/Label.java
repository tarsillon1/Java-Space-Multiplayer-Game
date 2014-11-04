package com.thinkbox.sf.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.thinkbox.sf.Game;

public class Label {
	private int x;
	private int y;
	private String text;
	private int size = 14;
	private boolean show;
	private Color color = Color.BLACK;
	
	public Label(int x, int y, String text){
		this.setX(x);
		this.setY(y);
		this.text = text;
		Game.getInstance().play.labels.add(this);
	}
	
	public void dispose(){
		Game.getInstance().play.labels.remove(this);
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.setFont(new Font("Arial", Font.PLAIN, size));
		g.drawString(text, x, y);
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
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setText(String t) {
		text = t;
	}

	public String getText() {
		return text;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
