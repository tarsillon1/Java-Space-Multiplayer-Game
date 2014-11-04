package com.thinkbox.sf.constants;

import com.thinkbox.sf.Game;

public class GameConstants {
	public static final int CENTER_X = Game.WIDTH / 2;
	public static final int CENTER_Y = Game.HEIGHT / 2;
	public static final String RESOURCE_LOCATION = "./resources/";
	public static final String SPRITE_LOCATION = RESOURCE_LOCATION + "sprites/";
	public static final String SOUND_LOCATION = RESOURCE_LOCATION + "sounds/";
	public static int MOVEMENT_INTERVAL = 1;
	public static final int BULLET_INTERVAL = 2;
	public static final int GAMELOOP_INTERVAL = 30;
	public static final int BULLET_MULTIPLIER = 1;
	public static final int BULLET_DESTROY = 500;
	public static final int COOLDOWN_INTERVAL = 1;
	public static final int MAX_BOUND = 2000;
	public static final int CHANCE = 8;
	public static final int CRASH_SCALE = 3;
	public static final int END_WAIT = 5000;
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	public static final int STARTX = 50;
	public static final int STARTY = 50;
	public static final int CRASH_INTERVAL = 100;
	public static final int MAX_INTERVAL = 50;
	public static final int MAP_INTERVAL = 1;

	public static final int FOG_INTERVAL = 10;
	public static final int PARTICLE_INTERVAL = 90;
	public static final String TITLE = "Space MMO";
	public static final int MUSIC_INTERVAL = 188 * 1000;
	public static final int GENERATE_INTERVAL = 10;
	public static int MOVE_INTERVAL = 10;
	public static final int ACCEL_INTERVAL = 40;
	public static final int IMAGES = 39;
	public static final int TYPES = 2;
	public static final int ITEMS = 3;
	public static final int MAPSIZE = 100;
	public static final long CHECK_INTERVAL = 1;
	public static final long ANIMATION_INTERVAL = 75;
	public static int step = 0;
	public static boolean server;
}
