package com.thinkbox.sf.multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.control.ModularLocation;
import com.thinkbox.sf.model.UniqueIdentifier;

public class Username {
	private String name;
	private int x;
	private int y;
	private int tileX;
	private int tileY;
	private Entity entity = new Entity();
	private UniqueIdentifier u = new UniqueIdentifier();
	public Username(String s){
		name = s;
		ServerConstants.entities.put(u.getId(), entity);
		Game.getInstance().play.names.add(this);
	}
	
	public void set(int x, int y, int tx, int ty){
		this.x = x;
		this.y = y;
		tileX = tx;
		tileY = ty;
		entity.set(x, y, null, 0, 0, tx, ty, null, true, name, false, false, GameConstants.MAX_INTERVAL, 9, 0, false, "For Later");
	}
	
	public void draw(Graphics g){
		if (ModularLocation.isVisibleTile(tileX, tileY)) {		
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString(name, ModularLocation.convertTileX(x + Game.getInstance().play.map.getDrawX(), tileX) , ModularLocation.convertTileY(y + Game.getInstance().play.map.getDrawY(), tileY));
		}
	}

	public String getKey() {
		return u.getId();
	}

	public int getName() {
		return name.length();
	}

	public String getUser() {
		return name;
	}

	public Entity getEntity() {
		return entity;
	}
}
