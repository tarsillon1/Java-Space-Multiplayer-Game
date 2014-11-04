package com.thinkbox.sf.control;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ServerConstants;

public class ModularLocation {
	public static int getX(){
		if(Game.getInstance().play.map != null)
			return convertX(Game.getInstance().play.map.getDrawX(), Game.getInstance().play.map.getX(), true);
		return 0;
	}
	
	public static int getY(){
		if(Game.getInstance().play.map != null)
			return convertY(Game.getInstance().play.map.getDrawY(), Game.getInstance().play.map.getY(), true);
		return 0;
	}
	
	public static int convertTileX(int i, int t){
		int convert = t - Game.getInstance().play.map.getX();
		convert = convert * ServerConstants.X;
		convert = i + convert;
		return convert;
	}
	
	public static int convertTileY(int i, int t){
		int convert = Game.getInstance().play.map.getY() - t;
		convert = convert * ServerConstants.Y;
		convert = i + convert;
		return convert;
	}
	
	public static int convertX(int i, int t, boolean b){
	if(Game.getInstance().play.map.getX() % 3 == 2){
		return i + ((t - 1) * ServerConstants.X);
	}
	else if(Game.getInstance().play.map.getX() % 3 == 1)
		return i + ((t) * ServerConstants.X );
	else
		return i + ((t - 2) * ServerConstants.X);
	}
	
	public static int convertY(int i, int t, boolean b){
		if(Game.getInstance().play.map.getY() % 3 == 2){
			return i + ((t - 1) * ServerConstants.Y);
		}
		else if(Game.getInstance().play.map.getY() % 3 == 1 && b == false)
			return i + ((t) * ServerConstants.Y);
		else if(Game.getInstance().play.map.getY() % 3 == 1 && b == true)
			return i + ((t - 1) * ServerConstants.Y);
		else if(b == false)
			return i + ((t - 2) * ServerConstants.Y);
		else
			return i + ((t) * ServerConstants.Y);
	}
	
	public static boolean isVisibleTile(int tx, int ty){
		if((tx == Game.getInstance().play.map.getX() || tx == Game.getInstance().play.map.getX() + 1 || tx == Game.getInstance().play.map.getX() - 1) && (ty == Game.getInstance().play.map.getY() || ty == Game.getInstance().play.map.getY() + 1 || ty == Game.getInstance().play.map.getY() - 1)){
			return true;
		}
		else
			return false;
	}
	
	public static boolean isVisible(int x, int y){
		if(x > convertX(-ServerConstants.X, Game.getInstance().play.map.getX(), true) && x < convertX(ServerConstants.X * 2, Game.getInstance().play.map.getX(), false) && y > convertY(-ServerConstants.Y, Game.getInstance().play.map.getY(), false) && y < convertY(ServerConstants.Y * 2, Game.getInstance().play.map.getY(), false)){
			return true;
		}
		else
			return false;
	}
	
	public static int convertToScreenX(int i){
		int x = i % (ServerConstants.X * 3);
		return x - ServerConstants.X;
	}
	
	public static int convertToScreenY(int i){
		int y = i % (ServerConstants.Y * 3);
		return y - ServerConstants.Y;
	}
}
