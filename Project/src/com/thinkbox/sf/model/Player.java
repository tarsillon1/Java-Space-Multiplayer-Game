package com.thinkbox.sf.model;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;

import org.newdawn.slick.geom.Circle;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.control.ModularLocation;
import com.thinkbox.sf.control.inputs.MouseInput;
import com.thinkbox.sf.multiplayer.Entity;
import com.thinkbox.sf.multiplayer.Username;
import com.thinkbox.sf.utils.AudioPlayer;

public class Player {
	private int animation = 0;
	private int health = 100;
	private boolean back;
	private String action = "stand";	
	private boolean attack;
	private Weapon weapon;
	private boolean cool;
	private int counter;
	private Username name;
	private int aCounter;
	private BufferedImage baseImage;
	private UniqueIdentifier u;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private double acceleration = GameConstants.MAX_INTERVAL;
	private BufferedImage actor;
	private Entity entity;
	private int xO;
	private int yO;
	private int goX;
	private int goY;
	private int t;
	private int count;
	private double speed = 1.5;
	private int xD;
	private int yD;
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getGoX() {
		return goX;
	}

	public void setGoX(int goX) {
		this.goX = goX;
		xD = Game.getInstance().play.map.getDrawX();
		yD = Game.getInstance().play.map.getDrawY();
		xO = ((ServerConstants.X) / 2) -  (actor.getWidth() / 2);
		t = 0;
	}
	
	public int getGoY() {
		return goY;
	}

	public void setGoY(int goY) {
		this.goY = goY;
		yO = ((ServerConstants.Y) / 2) -  (actor.getHeight() / 2);
		t = 0;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Username getName() {
		return name;
	}

	public void setName(Username name) {
		if(this.name != null)
			this.name.dispose();
		this.name = name;
	}

	public Player(Weapon w){
		weapon = w;
		actor = Images.exit;
		baseImage = Images.ship1Stand;
		u = new UniqueIdentifier();
		entity = new Entity(u.getId());
		ServerConstants.entities.put(u.getId(), entity);
	}
	
	public void setAcceletion(double d){
		acceleration = d;
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
	
	public void shoot() {
		if (!cool && !Game.getInstance().loading.isVisible()) {
			AudioPlayer.getSound(getWeapon().getSound()).play();
			Bullet b = new Bullet(getX() - Game.getInstance().play.map.getDrawX(), getY() - Game.getInstance().play.map.getDrawY(), MouseInput.MOUSE_X  - Game.getInstance().play.map.getDrawX(),
					MouseInput.MOUSE_Y - Game.getInstance().play.map.getDrawY(), bullets, getID(), Game.getInstance().play.map.getX(), Game.getInstance().play.map.getY());
			bullets.add(b);
			cool = true;
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
	
	public Rectangle getRect(){
		return new Rectangle(((ServerConstants.X ) / 2) - 30 - (baseImage.getWidth() / 4), ((ServerConstants.Y / 2) - 30- (baseImage.getHeight() / 4)), baseImage.getWidth() / 2, baseImage.getHeight() /2);
	}
	
	public Circle getCircle(){
		return new Circle((((ServerConstants.X) / 2) - 30) + (actor.getWidth() / 2), (((ServerConstants.Y / 2) - 30) + (actor.getHeight() / 2)), (float)(actor.getWidth() / 2));
	}
	
	public Circle getPos(){
		return new Circle((((ServerConstants.X) / 2) - 30), (((ServerConstants.Y  / 2) - 30)), 1);
	}
	
	public void attack(){
			animation = 0;
			back = false;
			attack = true;
	}
	
	public void setAction(String s){
		action = s;
	}
	
	public void changeAnimation(){
		if(back == false)
			animation += 1;
		if(back == true)
			animation -= 1;
		if(animation == 2){
			attack = false;
			back = true;
		}
		if(animation == 0){
			back = false;
		}
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
	
	public void draw(Graphics g){
		try {
			Graphics2D g2d = (Graphics2D) g;
			for (Bullet aBullet : bullets) {
				aBullet.getEntity().set(aBullet.getX(), aBullet.getY(), getWeapon().getActor(), (int) getBetween(aBullet.getOX(),
						aBullet.getOY(), aBullet.getMX(), aBullet.getMY()), 0.5, aBullet.getTileX(), aBullet.getTileY(), weapon, false, null, false, true, 2, 8, 0, true, Game.getInstance().play.player.getID());
				if (aBullet.isActive() && ModularLocation.isVisible(ModularLocation.convertX(aBullet.getX(), Game.getInstance().play.map.getX(), false), ModularLocation.convertY(aBullet.getY(), Game.getInstance().play.map.getY(), false))) {
					AffineTransform bul = new AffineTransform();
					bul.translate(ModularLocation.convertTileX(aBullet.getX() + Game.getInstance().play.map.getDrawX(), aBullet.getTileX()), ModularLocation.convertTileY(aBullet.getY() + Game.getInstance().play.map.getDrawY(), aBullet.getTileY()));
					bul.rotate(Math.toRadians(getBetween(aBullet.getOX(),
							aBullet.getOY(), aBullet.getMX(), aBullet.getMY())));
					bul.scale(0.5, 0.5);
					bul.translate(-getWeapon().getActor().getWidth() / 2,
							-getWeapon().getActor().getHeight() / 2);
					g2d.drawImage(getWeapon().getActor(), bul, null);
				}
			}

			if (action == "stand") {
				actor = Images.ship1Stand;
			} else
				actor = Images.ship1Move;
			/////////////////////////////////////////////////////////////////////////
			ServerConstants.entities.get(u.getId()).set(getX() - Game.getInstance().play.map.getDrawX(), getY() - Game.getInstance().play.map.getDrawY(), actor, (int) getBetween(
					((Game.frame.getWidth()) / 2) - 30,
					(Game.frame.getHeight() / 2) - 30, MouseInput.MOUSE_X,
					MouseInput.MOUSE_Y), 0.5, Game.getInstance().play.map.getX(), Game.getInstance().play.map.getY(), weapon, false, null, true, false, getAcceleration(), 9, health, true, Game.getInstance().play.player.getID());
			if(name != null)
				name.set(getX() - Game.getInstance().play.map.getDrawX() - (name.getName() * 5), getY() - Game.getInstance().play.map.getDrawY()  - 50, Game.getInstance().play.map.getX(),
					Game.getInstance().play.map.getY());
			//////////////////////////////////////////////////////////////////////////
				AffineTransform at = new AffineTransform();
				at.translate(((Game.frame.getWidth()) / 2) -  30,
						((Game.frame.getHeight()) / 2) - 30);
				at.rotate(Math.toRadians(getBetween(
						((Game.frame.getWidth()) / 2) - 30,
						((Game.frame.getHeight()) / 2) - 30, MouseInput.MOUSE_X,
						MouseInput.MOUSE_Y)));
				at.scale(0.5, 0.5);
				at.translate(-actor.getWidth() / 2, -actor.getHeight() / 2);
				g2d.drawImage(actor, at, null);
		} catch (ConcurrentModificationException e) {
		} catch (NullPointerException e) {
		} catch (NoSuchElementException e) {
		}
	}

	public void move() {
		t += 1;
		double angle = getBetween(xO, yO, goX, goY);
		if (angle <= 0) {
			angle += 360;
		}
		int charge = 1;
		if (angle >= 0 && angle <= 90) {
			angle = angle - 180;
			charge = -1;
		} else {
			if (angle >= 90 && angle <= 180) {
				angle = angle - 180;
				charge = -1;
			}
		}
		int x = (int) (xO + (speed * 1.5622)
				* (t * GameConstants.MOVEMENT_INTERVAL)
				* Math.cos(Math.toRadians(angle)));
		int y = (int) (yO + speed * (t * GameConstants.MOVEMENT_INTERVAL)
				* Math.sin(Math.toRadians(angle)));
		Game.getInstance().play.map.setCenter(
				(int) (xD - (((x - xO) * charge))),
				(int) (yD - ((y - yO) * charge)));
		count += 1;
		if (count == 5 && Game.getInstance().settings.getCheck2()) {
			Random rand = new Random();
			int randomValueY = rand.nextInt((3 + 3) + 1) - 3;
			int randomValueX = rand.nextInt((3 + 3) + 1) - 3;
			new Fog_Particle(Images.fire,
					(getX() - Game.getInstance().play.map.getDrawX())
							+ randomValueX,
					(getY() - Game.getInstance().play.map.getDrawY())
							+ randomValueY, false, 0.025, 0.1);
		}
		if (count == 5)
			count = 0;
		// ///////////////////////////////////////////////////////////////////////
		ServerConstants.entities.get(u.getId()).set(
				getX() - Game.getInstance().play.map.getDrawX(),
				getY() - Game.getInstance().play.map.getDrawY(),
				actor,
				(int) getBetween(((Game.frame.getWidth()) / 2) - 30,
						(Game.frame.getHeight() / 2) - 30, MouseInput.MOUSE_X,
						MouseInput.MOUSE_Y), 0.5,
				Game.getInstance().play.map.getX(),
				Game.getInstance().play.map.getY(), weapon, false, null, true,
				false, getAcceleration(), 9, health, true, Game.getInstance().play.player.getID());
		if (name != null)
			name.set(
					getX() - Game.getInstance().play.map.getDrawX()
							- (name.getName() * 5), getY()
							- Game.getInstance().play.map.getDrawY() - 50,
					Game.getInstance().play.map.getX(),
					Game.getInstance().play.map.getY());
		// ////////////////////////////////////////////////////////////////////////
	}

	public int getY() {
		return ((ServerConstants.Y) / 2) - 30;
	}

	public int getX() {
		return ((ServerConstants.X) / 2) - 30;
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

	public String getID() {
		return u.getId();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
