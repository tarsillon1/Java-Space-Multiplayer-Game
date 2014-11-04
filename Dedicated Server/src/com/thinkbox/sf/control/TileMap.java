package com.thinkbox.sf.control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.ServerConstants;

public class TileMap {
	private int currentTileX;
	private int currentTileY;
	private boolean move;
	private int drawX;
	private int drawY;
	private BufferedImage[][] tiles = new BufferedImage[GameConstants.MAPSIZE][GameConstants.MAPSIZE];
	private boolean locked;
	
	public TileMap(int i, int j) {
		currentTileX = i;
		currentTileY = j;
	}
	
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

	public void setMove(boolean b) {
		move = b;
	}

}
