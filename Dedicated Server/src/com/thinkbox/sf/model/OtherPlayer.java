package com.thinkbox.sf.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.multiplayer.Entity;
import com.thinkbox.sf.multiplayer.Username;
import com.thinkbox.sf.utils.BufferedImageLoader;

public class OtherPlayer {
	
		private int x;
		private int y;
		private int rotation;
		private boolean attack;
		private Weapon weapon;
		private boolean cool;
		private int counter;
		private Username name;
		private int aCounter;
		private int tileY;
		private int tileX;
		private UniqueIdentifier u;
		private Entity entity;
		private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		private double acceleration = 0;
		private BufferedImage actor;
		private int health;
		private String ID;
		
		public int getHealth() {
			return health;
		}

		public void setHealth(int health) {
			this.health = health;
		}

		public OtherPlayer(Weapon w, int x, int y, int tx, int ty){
			this.x = x;
			this.y = y;
			tileX = tx;
			tileY = ty;
			weapon = w;
			u = new UniqueIdentifier();
			actor = Images.exit;
			entity = new Entity(u.getId());
			ServerConstants.entities.put(u.getId(), entity);
		}
		
		public Username getName() {
			return name;
		}

		public void setName(Username name) {
			this.name = name;
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
		
		public void set(int x1, int y1, int tx, int ty, int r, int a, Weapon weapon, double d, String ID){
			x = x1;
			y = y1;
			tileX = tx;
			tileY = ty;
			rotation = r;
			this.weapon = weapon;
			acceleration = d;
			actor = BufferedImageLoader.converter.getImage(a);
			this.ID = ID;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int getRotation(){
			return rotation;
		}
		
		public void setRotation(int r){
			 rotation = r;
		}
		
		public void setAcceletion(int i){
			acceleration = i;
		}
		
		public void cooldown() {
			if (cool) {
				counter += 1;
				if (counter >= getWeapon().getSpeed()) {
					cool = false;
					counter = 0;
				}
			}
		}
		
		public void setWeapon(Weapon w){
			weapon = w;
		}
		
		public Weapon getWeapon(){
			return weapon;
		}
		
		public double getAcceleration(){
			return acceleration;
		}
		
		public void attack(){
				attack = true;
		}
		
		public boolean getAttack(){
			return attack;
		}
		
		public static double getBetween(int x1, int y1, int x2, int y2) {
			double xDiff = x2 - x1;
			double yDiff = y2 - y1;
			return Math.toDegrees(Math.atan2(yDiff, xDiff));
		}
		
		public ArrayList<Bullet> getList(){
			return bullets;
		}
		
		public void update(){
			try {
				/////////////////////////////////////////////////////////////////////////////////
				ServerConstants.entities.get(u.getId()).set(x, y, actor, rotation, 0.5, tileX, tileY, weapon, false, null, true, false, acceleration, 9, health, true, ID);
				
				name.set(getX() - (name.getName() * 5), getY() - 50, getTileX(),
						getTileY());
				/////////////////////////////////////////////////////////////////////////////////
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
		}

		public void aTick() {
			aCounter += 1;
			
		}

		public int getTick() {
			return aCounter;
		}

		public void aReset() {
			aCounter = 0;
		}

		public void remove() {
			ServerConstants.entities.remove(u.getId());
		}

		public String getKey() {
				return u.getId();
		}

		public Entity getEntity() {
			return entity;
		}
	}

