package com.thinkbox.sf.multiplayer;

import com.thinkbox.sf.model.Weapon;

public class WeaponConverter {
	private int current;
	private Weapon[] weapons;
	public WeaponConverter(int t){
		weapons = new Weapon[t];
	}
	
	public void createConvertion(Weapon w){
		current += 1;
		weapons[current] = w;
	}
	
	public int convertWeapon(Weapon w){
		for(int i = 0; i <= current; i++){
			if(weapons[i] == w){
				return i;
			}
		}
		return 0;
	}
	
	public Weapon getWeapon(int i){
		return weapons[i];
	}
}
