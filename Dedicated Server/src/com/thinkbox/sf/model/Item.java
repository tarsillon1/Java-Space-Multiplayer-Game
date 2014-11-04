package com.thinkbox.sf.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.constants.WeaponConstants;
import com.thinkbox.sf.multiplayer.Entity;
import com.thinkbox.sf.utils.Audio;

public class Item implements Cloneable  {
    public Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }
    
    private int x = 0;
    private int y = 0;
    private int tileX = 0;
    private int tileY = 0;
    private int rotation = 0;
    private boolean active;
	private BufferedImage b;
	private Entity entity;
	private UniqueIdentifier u;
	private boolean intersects;
	private boolean light;
	private double lightR;
	private double lightB;
	private int time;
	private int xCharge;
	private int yCharge;
	private double scale = 0.5;
	private int layer;
	private String type;
	private int health;
	private boolean shootable;
	private ArrayList<String> restrictions = new ArrayList<String>();
    
    public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public UniqueIdentifier getU() {
		return u;
	}

	public void setU(UniqueIdentifier u) {
		this.u = u;
	}
	
	public void setLight(boolean l, double r, double b){
		light = l;
		lightR = r;
		lightB = b;
	}
	
	public void set(int x, int y, int r, boolean b){
    	this.setX(x % GameConstants.WIDTH);
    	this.setY(y % GameConstants.HEIGHT);
    	tileX = x / GameConstants.WIDTH;
    	tileY = y / GameConstants.HEIGHT;
    	setRotation(r);
    	Game.getInstance().play.items.add(this);
    	active = b;
    }
    
	public Item(BufferedImage b, boolean i, String type, int l, int health, boolean s){
		this.setB(b);
		this.intersects = i;
		this.type = type;
		layer = l;
		this.health = health;
		shootable = s;
	}

	public void update() {
		if (isActive()) {
			try {
				// ///////////////////////////////////////////////////////////////////////
				ServerConstants.entities.get(getU().getId()).set(getX(),
						getY(), getB(), getRotation(), scale, tileX, tileY,
						WeaponConstants.playerWeapon1, false, null, intersects,
						false, 0, layer, health, shootable, "Server");
				ServerConstants.entities.get(getU().getId()).setLight(light, lightR, lightB);
				// ////////////////////////////////////////////////////////////////////////
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
		}
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if(active == true && this.active == false){
			u = new UniqueIdentifier();
			entity = new Entity(u.getId());
			ServerConstants.entities.put(u.getId(), entity);

		}
		
		if(active == false && this.active == true){
			ServerConstants.entities.remove(u.getId());
		}
		
		this.active = active;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
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

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public BufferedImage getB() {
		return b;
	}

	public void setB(BufferedImage b) {
		this.b = b;
	}
	//AI////////////////
	public void act() {
		
		if(isActive() && type == "asteriod"){
			if(health <= 0){
				ServerConstants.entities.remove(u.getId());
				active = false;
				BufferedImage[] images = new BufferedImage[100];
				images[0] = Images.asteriodDeath1;
				images[1] = Images.asteriodDeath2;
				images[2] = Images.asteriodDeath3;
				images[3] = Images.asteriodDeath4;
				images[4] = Images.asteriodDeath5;
				images[5] = Images.asteriodDeath6;
				ServerConstants.sound.add(Audio.dirtExplosion);
				@SuppressWarnings("unused")
				Animation death = new Animation(x, y, tileX, tileY, rotation, 1, layer, 6, images);
			}
			Random rand = new Random();
	    
	    	if(xCharge == 0 || yCharge == 0){
	    		xCharge = rand.nextInt((1 + 1) + 1) - 1;
	    		yCharge = rand.nextInt((1 + 1) + 1) - 1;
	    	}
	    	if(scale == 0.5){
	    		scale = 0.2 + (0.6 - 0.2) * rand.nextDouble();
	    	}
	    
			time ++;
			if(time == 5){
				setRotation(getRotation() + 1);
				setX(getX() + xCharge);
				setY(getY() + yCharge);
				time = 0;
				ServerConstants.entities.get(getU().getId()).set(getX(),
						getY(), getB(), getRotation(), scale, tileX, tileY,
						WeaponConstants.playerWeapon1, false, null, intersects,
						false, 0, layer, health, shootable, "Server");
				ServerConstants.entities.get(getU().getId()).setLight(light, lightR, lightB);
				for(Entity aBullet: ClientConstants.temp){
					if(!restrictions.contains(aBullet.getID()) && aBullet.isBullet() && aBullet.getRect().intersects(ServerConstants.entities.get(getU().getId()).getRect())){
						health -= aBullet.getW().getDamage();
						restrictions.add(aBullet.getID());
						ServerConstants.remove.add(aBullet.getID());
					}
				}
			}
		}
		
		
		
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
}
