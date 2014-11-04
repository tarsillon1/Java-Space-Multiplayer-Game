package com.thinkbox.sf.model;

import java.awt.image.BufferedImage;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.constants.WeaponConstants;
import com.thinkbox.sf.multiplayer.Entity;

public class Animation {
	private int total;
	private int current;
	private int x;
	private int y;
	private int tileX;
	private int tileY;
	private BufferedImage[] image = new BufferedImage[100];
	private UniqueIdentifier u;
	private int rotation;
	private double scale;
	private int layer;
	
	public Animation(int x, int y, int tileX, int tileY, int r, double scale, int layer, int t, BufferedImage[] i){
		Entity entity = new Entity();
		u = new UniqueIdentifier();
		ServerConstants.entities.put(u.getId(), entity);
		setTotal(t);
		setImage(i);
		this.x = x;
		this.y = y;
		this.tileX = tileX;
		this.tileY = tileY;
		rotation = r;
		this.scale = scale;
		this.layer = layer;
		Game.getInstance().play.animations.add(this);
		ServerConstants.entities.get(getU().getId()).set(getX(),
				getY(), image[current], rotation, scale, tileX, tileY,
				WeaponConstants.playerWeapon1, false, null, false,
				false, 0, layer, 0, false, "Server");
	}
	
	public void update(){
		if(current < total){
			current++;
			ServerConstants.entities.get(getU().getId()).set(getX(),
					getY(), image[current], rotation, scale, tileX, tileY,
					WeaponConstants.playerWeapon1, false, null, false,
					false, 0, layer, 0, false, "Server");
		}
		else{
			Game.getInstance().play.animations.remove(this);
			ServerConstants.entities.remove(u.getId());
		}
	}
	
	public UniqueIdentifier getU() {
		return u;
	}

	public void setU(UniqueIdentifier u) {
		this.u = u;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public BufferedImage[] getImage() {
		return image;
	}

	public void setImage(BufferedImage[] image) {
		this.image = image;
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
}
