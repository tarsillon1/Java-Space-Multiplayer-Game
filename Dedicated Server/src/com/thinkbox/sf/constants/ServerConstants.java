package com.thinkbox.sf.constants;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.thinkbox.sf.multiplayer.Entity;

public  final class ServerConstants {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int PLAYERS = 10;
	public static String HOST = "localhost";
	public static int SOCKET = 8295;
	public static int X = 1366;
	public static int Y = 768;
	public static String username = "";
	public static ConcurrentHashMap<String,Entity> entities;
	public static ArrayList<String> sound = new ArrayList<String>();
	public static ArrayList<String> remove = new ArrayList<String>();
	
	static{
		entities = new ConcurrentHashMap<String,Entity>();
	}

	public static ConcurrentHashMap<String, Entity> getEntities() {
		return entities;
	}

	public static void setEntities(ConcurrentHashMap<String, Entity> entities) {
		ServerConstants.entities = entities;
	}

}
