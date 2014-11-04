package com.thinkbox.sf.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Item;

public class Biome {
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean active;
	private int size;
	private int x;
	private int y;
	
	public Biome(int x, int y, int s, BiomeType types[], int BiomeProbability[]) throws CloneNotSupportedException{
		this.setX(x);
		this.setY(y);
		int total = 0;
		for(int i = 0; i < GameConstants.TYPES; i++){
			total += BiomeProbability[i];
		}
		Random rand = new Random();
		int random = rand.nextInt((total - 1) + 1) + 1;
		
		int param1 = 0;
		int param2 = 0;
		int type = 0;
		for(int i = 0; i < GameConstants.TYPES; i++){
			param1 += BiomeProbability[i];
			if(param1 >= random && param2 < random){
				type = i;
			}
			param2 += BiomeProbability[i];
		}
		size = s;
		items.addAll(types[type].generate(x, y, size));
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
	
	public void setActive(){
		if(active == false){
			try {
				for (Item aItem : items) {
						aItem.setActive(true);
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
			active = true;
		}
	}
	
	public void setUnactive(){
		if(active == true){
			try {
				for (Item aItem : items) {
						aItem.setActive(false);
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
			active = false;
		}
	}

	public boolean isActive() {
		return active;
	}
}
