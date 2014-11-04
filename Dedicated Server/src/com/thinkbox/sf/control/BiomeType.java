package com.thinkbox.sf.control;

import java.util.ArrayList;
import java.util.Random;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Item;

public class BiomeType {
	private int current = 0;
	private Item[] items = new Item[GameConstants.ITEMS];
	private int[] maxAmount = new int[GameConstants.ITEMS];
	private int[] minAmount = new int[GameConstants.ITEMS];
	private int[] maxRotation = new int[GameConstants.ITEMS];
	private int[] minRotation = new int[GameConstants.ITEMS];
	
	public BiomeType(){
	}
	
	public void addItem(Item item, int maxA, int minA, int maxR, int minR){
		items[current] = item;
		maxAmount[current] = maxA;
		minAmount[current] = minA;
		maxRotation[current] = maxR;
		minRotation[current] = minR;
		current += 1;
	}
	
	public ArrayList<Item> generate(int x, int y, int s) throws CloneNotSupportedException{
		ArrayList<Item> gen = new ArrayList<Item>();
		for(int i = 0; i < current + 1; i++){
			Random rand = new Random();
			int random = rand.nextInt((maxAmount[i] - minAmount[i]) + 1) + minAmount[i];
			for(int a = 0; a < random; a++){
				Item clone = items[i].clone();
				int rotation = rand.nextInt((maxRotation[i] - minRotation[i]) + 1) + minRotation[i];
				int xL = rand.nextInt(((x + (GameConstants.WIDTH * s)) - x) + 1) + x;
				int yL = rand.nextInt(((y + (GameConstants.HEIGHT * s)) - y) + 1) + y;
				clone.set(xL, yL, rotation, false);
				gen.add(clone);
			}
		}
		return gen;
	}
}
