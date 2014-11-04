package com.thinkbox.sf.control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.Fog_Particle;
import com.thinkbox.sf.model.Player;
import com.thinkbox.sf.multiplayer.Entity;

public class TileMap {
	private int currentTileX;
	private int currentTileY;
	private boolean move;
	private int drawX;
	private int drawY;
	private BufferedImage[][] tiles = new BufferedImage[GameConstants.MAPSIZE][GameConstants.MAPSIZE];
	private boolean locked;
	
	public void enterTile(){
		spawnTile(currentTileX, currentTileY, 0, 0);
		spawnTile(currentTileX, currentTileY + 1, 0, -ServerConstants.Y);
		spawnTile(currentTileX, currentTileY - 1, 0, ServerConstants.Y);
		spawnTile(currentTileX + 1, currentTileY, ServerConstants.X, 0);
		spawnTile(currentTileX - 1, currentTileY, -ServerConstants.X, 0);
		spawnTile(currentTileX + 1, currentTileY + 1, ServerConstants.X, -ServerConstants.Y);
		spawnTile(currentTileX - 1, currentTileY + 1, -ServerConstants.X, -ServerConstants.Y);
		spawnTile(currentTileX + 1, currentTileY - 1, ServerConstants.X, ServerConstants.Y);
		spawnTile(currentTileX - 1, currentTileY + 1, -ServerConstants.X, ServerConstants.Y);
	}
	///////////////////////////////////////////////////////////////////////
	public void spawnTile(int x, int y, int offX, int offY){
	}
	////////////////////////////////////////////////////////////////////////
	public boolean getLock() {
		return locked;
	}

	public void setLock(boolean b) {
		locked = b;
	}

	public TileMap(int cx, int cy, Player h) {
		currentTileX = cx;
		currentTileY = cy;
		for(int i = 0; i < GameConstants.MAPSIZE; i++){
			for(int i2 = 0; i2 < GameConstants.MAPSIZE; i2++){
				setTile(i, i2, Images.tile);
			}
		}
	}
	
	public int getX() {
		return currentTileX;
	}

	public int getY() {
		return currentTileY;
	}

	public BufferedImage getTile(int x, int y) {
		return tiles[x][y];
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
		return dimg;
	}

	public boolean isMoving() {
		return move;
	}

	public void setTile(int x, int y, BufferedImage b) {
		tiles[x][y] = b;
	}

	public void draw(Graphics g) {
		try {
			//Middle
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX - 1, drawY - 1);
			//Up
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX - 1, drawY - ServerConstants.Y - 1);
			//Down
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX- 1, drawY + ServerConstants.Y- 1);
			//Left
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX - ServerConstants.X- 1, drawY- 1);
			//Right
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX + ServerConstants.X- 1, drawY- 1);
			//SouthEast
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX + ServerConstants.X - 1, drawY + ServerConstants.Y - 1);
			//NorthWest
			resize(Images.tile,
					ServerConstants.X+ 2,
					ServerConstants.Y+ 2, g, drawX - ServerConstants.X - 1, drawY - ServerConstants.Y - 1);
			//NorthEast
			resize(Images.tile,
					ServerConstants.X + 2,
					ServerConstants.Y + 2, g, drawX + ServerConstants.X - 1, drawY - ServerConstants.Y - 1);
			//SouthWest
			resize(Images.tile,
					ServerConstants.X + 2,
					ServerConstants.Y + 2, g, drawX - ServerConstants.X- 1, drawY + ServerConstants.Y - 1);
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void setCurrent(int x, int y) {
		currentTileX = x;
		currentTileY = y;
		enterTile();
	}

	public int getDrawY() {
		return drawY;
	}

	public int getDrawX() {
		return drawX ;
	}

	public void setCenter(int x, int y) {
		boolean m = false;
		int oldX = drawX;
		int oldY = drawY;
		drawY = y;
		drawX = x;
		for(Entity aEntity: ClientConstants.collisions){
			if(aEntity.isIntersects() && aEntity.getRect().intersects(Game.getInstance().play.player.getRect()) && aEntity.getID() != Game.getInstance().play.player.getID()){
				m = true;
			}
		}
		for(Entity aEntity: ServerConstants.entities.values()){
			if(aEntity.isIntersects() && aEntity.getRect().intersects(Game.getInstance().play.player.getRect()) && aEntity.getID() != Game.getInstance().play.player.getID()){
				m = true;
			}
		}
		
		if(m == true){
			drawX = oldX;
			drawY = oldY;
		}
	}

	public void setMove(boolean b) {
		move = b;
	}
	public void setNegX(boolean b) {
		try {
			
			for (Fog_Particle aParticle : Game.getInstance().play.particles) {
				aParticle.setNegX(b);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NullPointerException e) {
		} catch (NoSuchElementException e) {
		}	
	}
	
	public void setNegY(boolean b) {
		try {
			
			for (Fog_Particle aParticle : Game.getInstance().play.particles) {
				aParticle.setNegY(b);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NullPointerException e) {
		} catch (NoSuchElementException e) {
		}	
	}

}
