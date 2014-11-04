package com.thinkbox.sf.constants;

import com.thinkbox.sf.model.Weapon;
import com.thinkbox.sf.multiplayer.WeaponConverter;

public class WeaponConstants {
	public static WeaponConverter wConverter = new WeaponConverter(2);
	public static final Weapon playerWeapon1 = new Weapon(10, Images.bullet1, 200,
			"Double Laser Gun", null);
}
