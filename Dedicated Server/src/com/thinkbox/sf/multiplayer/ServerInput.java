package com.thinkbox.sf.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.OtherPlayer;


public class ServerInput implements Runnable{

	InputStreamReader in;
	BufferedReader br;
	private OtherPlayer player;
	private Username name;
	private ArrayList<Entity> exclude;
	
	public ServerInput(InputStreamReader in, OtherPlayer p) throws IOException{
		this.in = in;
		br = new BufferedReader(in);
		player = p;
	}
	
	@Override
	public void run() {
		 br = new BufferedReader(in);
         String userName;
		try {
			userName = br.readLine();
			System.out.println("USERNAME:" + userName); 
			Game.getInstance().play.chat.add(userName + " has joined the game!");
			name = new Username(userName);
	        player.setName(name);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
       
         
		while(Game.getInstance().server.running){
			try {
	               String inJson = br.readLine();
	               Entity e = new Gson().fromJson(inJson, new TypeToken<Entity>(){}.getType());
	               player.set(e.getX(), e.getY(), e.getTileX(), e.getTileY(), e.getRotation(), e.getE(), e.getW(), e.getVelocity(), e.getOwner());
	               String inJson2 = br.readLine();
	               ArrayList<Entity> a = new Gson().fromJson(inJson2, new TypeToken<ArrayList<Entity>>(){}.getType());
		           ClientConstants.temp = new ArrayList<Entity>(a);
		           exclude = new ArrayList<Entity>(a);
		           String inJson3 = br.readLine();
		           String s = new Gson().fromJson(inJson3, new TypeToken<String>(){}.getType());
		           if(!s.equals("") && s != null)
		        	   Game.getInstance().play.chat.add(s);
		           String inJson4 = br.readLine();
		           ArrayList<String> r = new Gson().fromJson(inJson4, new TypeToken<ArrayList<String>>(){}.getType());
		           ServerConstants.sound.removeAll(r);
			} catch (IOException e) {
			} catch(JsonSyntaxException e){
				try {
					br.close();
					break;
				} catch (IOException e1) {
				}
			}catch (NullPointerException e){
				
			}
			
		}
	}

	public ArrayList<Entity> getExclude() {
		return exclude;
	}

	public Username getName() {
		return name;
	}

}
