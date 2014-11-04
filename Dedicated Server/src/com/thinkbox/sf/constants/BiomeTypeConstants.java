package com.thinkbox.sf.constants;


import com.thinkbox.sf.control.BiomeType;
import com.thinkbox.sf.model.Item;

public class BiomeTypeConstants {
	public final static BiomeType asteriods = new BiomeType();
	public static BiomeType none = new BiomeType();
	public final static void setTypes(){
		Item galaxy = new Item(Images.biome1, false, "", 0, 1, false);
		Item asteriod = new Item(Images.asteriod, false, "asteriod", 1, 100, true);
		galaxy.setLight(true, 0.6, 0.4);
		asteriods.addItem(asteriod, 6, 3, 360, 0);
		asteriods.addItem(galaxy, 1, 1, 20, 0);
	}
	

}
