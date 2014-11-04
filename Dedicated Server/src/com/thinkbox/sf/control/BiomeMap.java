package com.thinkbox.sf.control;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Item;
import com.thinkbox.sf.model.OtherPlayer;

public class BiomeMap {
	private ArrayList<Biome> biomes = new ArrayList<Biome>();
	private boolean[][] genMap = new boolean[GameConstants.MAPSIZE][GameConstants.MAPSIZE];
	private Biome[][] biomeMap = new Biome[GameConstants.MAPSIZE][GameConstants.MAPSIZE];
	private BiomeType[] types = new BiomeType[GameConstants.TYPES];
	private int[] BiomeProbability = new int[GameConstants.TYPES];
	private int current;
	
	public BiomeMap(){
	}
	
	public void generate() throws CloneNotSupportedException{
		int[] squares = new int[4];
		squares[1] = 1;
		squares[2] = 4;
		squares[3] = 9;
		int random;
		for(int i = 0; i < GameConstants.MAPSIZE - 1; i++){
			for(int x = 0; x < GameConstants.MAPSIZE - 1; x++){
				if(!genMap[x][i]){
					random = 1 + (int)(Math.random() * ((2 - 1) + 1));
					boolean check = false;
					boolean find = false;
					while(find == false){
						for(int z = 1; z < Math.sqrt(squares[random]) - 1; z++){
							if(genMap[x + z][i]){
								check = true;
							}
						}
						if(check == true)
							random -= 1;
							else
								find = true;
					}
					Biome addBiome = new Biome(GameConstants.WIDTH * x, GameConstants.HEIGHT * i, squares[random], types, BiomeProbability);
					for(int p = 0; p < Math.sqrt(squares[random]) - 1; p++){
						for(int o = 0; o < Math.sqrt(squares[random]) - 1; o++){
							genMap[x + o][i + p] = true;
							biomeMap[x + o][i + p] = addBiome;
						}
					}
					biomes.add(addBiome);
				}
			}
		}
	}
	
	public void addBiomeType(BiomeType t, int p){
		types[current] = t;
		BiomeProbability[current] = p;
		current += 1;
	}
	
	public void check(){
		ArrayList<Biome> actives = new ArrayList<Biome>();
		try {
			for(OtherPlayer aPlayer: Game.getInstance().play.players){
				for (Biome aBiome : biomes) {
					for(Item aItem : aBiome.getItems()){
						if(Math.sqrt(Math.abs(Math.pow((aPlayer.getX() + (aPlayer.getTileX() * GameConstants.WIDTH)) - (aItem.getX() + (aItem.getTileX() * GameConstants.WIDTH)), 2) + Math.pow((aPlayer.getY()  + (aPlayer.getTileY() * GameConstants.HEIGHT)) - (aItem.getY() + (aItem.getTileY() * GameConstants.HEIGHT)), 2))) <= 2000){
							aBiome.getItems().remove(aItem);
							aItem.setTileX(aItem.getTileX() + (aItem.getX() / GameConstants.WIDTH));
							aItem.setX(aItem.getX() % GameConstants.WIDTH);
							aItem.setTileY(aItem.getTileY() + (aItem.getY() / GameConstants.HEIGHT));
							aItem.setY(aItem.getY() % GameConstants.HEIGHT);
							actives.add(aBiome);
							aBiome.setActive();
						}
					}
				}
			}
			
			try {
				for (Biome aBiome : biomes) {
					if(!actives.contains(aBiome))
							aBiome.setUnactive();
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}
		} catch (ConcurrentModificationException e) {
		}
	}

	public ArrayList<Biome> getBiomes() {
		return biomes;
	}

	public void setBiomes(ArrayList<Biome> biomes) {
		this.biomes = biomes;
	}
	
}
