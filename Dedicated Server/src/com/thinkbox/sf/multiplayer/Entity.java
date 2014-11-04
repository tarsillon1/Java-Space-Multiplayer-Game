package com.thinkbox.sf.multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.WeaponConstants;
import com.thinkbox.sf.control.ModularLocation;
import com.thinkbox.sf.model.Weapon;
import com.thinkbox.sf.utils.BufferedImageLoader;

public class Entity{

	private int x;
	private int y;
	private int health;
	private int tileX;
	private int tileY;
	private int e;
	private boolean isUser;
	private int w;
	private double velocity;
	private int rotation;
	private double scale;
	private String name; 
	private int remove;
	private String ID;
	private boolean intersects;
	private boolean isBullet;
	private int drawLevel;
	private boolean light;
	private double lightR;
	private double lightB;
	private boolean shootable;
	private String owner;
	
	public void setShootable(boolean shootable) {
		this.shootable = shootable;
	}

	public Entity(){}
	
	public Rectangle getRect(){
		return new Rectangle(ModularLocation.convertTileX((int) (x + Game.getInstance().play.map.getDrawX() - ((BufferedImageLoader.converter.getImage(e).getWidth() * (scale + 0.2))  / 2)), tileX), ModularLocation.convertTileY((int) (y + Game.getInstance().play.map.getDrawY() - ((BufferedImageLoader.converter.getImage(e).getHeight() * (scale + 0.2)) / 2)), tileY), (int) ((BufferedImageLoader.converter.getImage(e).getWidth() * (scale + 0.2))), (int)((BufferedImageLoader.converter.getImage(e).getHeight() * (scale + 0.2))));
	}
	
	public Entity(String id){
		ID = id;
	}
	
	public void setLight(boolean l, double lightR2, double lightB2){
		light = l;
		setLightR(lightR2);
		setLightB(lightB2);
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

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public int getRemove() {
		return remove;
	}

	public void setRemove(int remove) {
		this.remove = remove;
	}

	public void draw(Graphics g) {
			if (ModularLocation.isVisibleTile(tileX, tileY) && isUser == false) {
				Graphics2D g2d = (Graphics2D) g;
				AffineTransform at = new AffineTransform();
				at.translate(ModularLocation.convertTileX(x + Game.getInstance().play.map.getDrawX(), tileX), ModularLocation.convertTileY(y + Game.getInstance().play.map.getDrawY(), tileY));
				at.rotate(Math.toRadians(rotation));
				at.scale(scale, scale);
				at.translate(-BufferedImageLoader.converter.getImage(e).getWidth() / 2, -BufferedImageLoader.converter.getImage(e).getHeight() / 2);
				g2d.drawImage(BufferedImageLoader.converter.getImage(e), at,
						null);
			}
			
			if (ModularLocation.isVisibleTile(tileX, tileY) && isUser == true) {	
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString(name, ModularLocation.convertTileX(x + Game.getInstance().play.map.getDrawX(), tileX) , ModularLocation.convertTileY(y + Game.getInstance().play.map.getDrawY(), tileY));
			}
			
	}

	public void set(int x, int y, BufferedImage e, int r, double d, int tx, int ty, Weapon w, boolean b, String name, boolean b1, boolean b2, double f, int draw, int health, boolean shootable, String o) {
		this.x = x;
		this.y = y;
		this.e = BufferedImageLoader.converter.getConvertion(e);
		tileX = tx;
		tileY = ty;
		rotation = r;
		scale = d;
		this.w = WeaponConstants.wConverter.convertWeapon(w);
		isUser = b;
		intersects = b1;
		isBullet = b2;
		velocity = f;
		setDrawLevel(draw);
		this.name = name;
		this.health = health;
		this.shootable = shootable;
		owner = o;
	}

	public boolean isIntersects() {
		return intersects;
	}

	public void setIntersects(boolean intersects) {
		this.intersects = intersects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return x + ", " + y + ", " + tileX + ", " + tileY;
	}

	public Weapon getW() {
		return WeaponConstants.wConverter.getWeapon(w);
	}

	public boolean isBullet() {
		return isBullet;
	}

	public String getID() {
		return ID;
	}

	public double getVelocity() {
		return velocity;
	}

	public boolean isLight() {
		return light;
	}

	public void setLight(boolean light) {
		this.light = light;
	}

	public double getLightR() {
		return lightR;
	}

	public void setLightR(double lightR2) {
		this.lightR = lightR2;
	}

	public double getLightB() {
		return lightB;
	}

	public void setLightB(double lightB2) {
		this.lightB = lightB2;
	}

	public int getDrawLevel() {
		return drawLevel;
	}

	public void setDrawLevel(int drawLevel) {
		this.drawLevel = drawLevel;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isShootable() {
		return shootable;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}