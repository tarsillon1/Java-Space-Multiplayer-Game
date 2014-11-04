package com.thinkbox.sf.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.control.BiomeMap;
import com.thinkbox.sf.control.TileMap;
import com.thinkbox.sf.control.Timer;
import com.thinkbox.sf.model.Animation;
import com.thinkbox.sf.model.Chat;
import com.thinkbox.sf.model.Item;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.model.OtherPlayer;
import com.thinkbox.sf.multiplayer.Server;
import com.thinkbox.sf.multiplayer.Username;

public class Play {
	public ArrayList<Timer> timers = new ArrayList<Timer>();
	public ArrayList<OtherPlayer> players = new ArrayList<OtherPlayer>();
	public ArrayList<Username> names = new ArrayList<Username>();
	public ArrayList<Label> labels = new ArrayList<Label>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<Animation> animations = new ArrayList<Animation>();
	
	public TileMap map = new TileMap(50, 50);
	public BiomeMap bMap = new BiomeMap();
	public Chat chat;

	public Play() {
		try {
			Game.getInstance().server = new Server();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}
	
	public void update() {
		try {
			for (Item aItem : items) {
				aItem.update();
				aItem.act();
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
		
		try {
			for (OtherPlayer aPlayer : players) {
				aPlayer.update();
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
	}
}
