package com.thinkbox.sf.multiplayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.utils.AudioPlayer;


public class ClientInput implements Runnable{

	InputStreamReader in;
	BufferedReader br;
	
	public ClientInput(InputStreamReader in) throws IOException{
		this.in = in;
		br = new BufferedReader(in);
	}
	
	@Override
	public void run() {
		while(true){
			try {
	            String inJson = br.readLine();
	            ArrayList<Entity> e = new Gson().fromJson(inJson, new TypeToken<ArrayList<Entity>>(){}.getType());
	            ClientConstants.temp = new ArrayList<Entity>(e);
	            ClientConstants.collisions = new ArrayList<Entity>(e);
	            String inJson2 = br.readLine();
	            Label[] t = new Gson().fromJson(inJson2, new TypeToken<Label[]>(){}.getType());
	            Game.getInstance().play.chat.set(t);
	            String inJson3 = br.readLine();
	            ArrayList<String> s = new Gson().fromJson(inJson3, new TypeToken<ArrayList<String>>(){}.getType());
	            for(String aString: s){
	            	if(!ClientConstants.removeSound.contains(aString)){
	            		AudioPlayer.getSound(aString).play();
	            	}
	            }
	            ClientConstants.removeSound.addAll(s);
	            if(s.isEmpty()){
	            	ClientConstants.removeSound.removeAll(ClientConstants.removeSound);
	            }
	            String inJson4 = br.readLine();
	            ArrayList<String> r = new Gson().fromJson(inJson4, new TypeToken<ArrayList<String>>(){}.getType());
	            for(String aString: r){
	            	ServerConstants.entities.remove(aString);
	            }
			} catch (IOException e) {
				break;
			}catch (NullPointerException n) {
			}
			
		}
	}

}
