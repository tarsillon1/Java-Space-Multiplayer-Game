package com.thinkbox.sf.multiplayer;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.google.gson.Gson;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.Button;
import com.thinkbox.sf.model.Chat;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.model.PopUp;
import com.thinkbox.sf.model.Textbox;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;


public class Client extends Thread{
	public static Socket socket;
	public static InputStreamReader  in;
	public static OutputStreamWriter  out;
	public static BufferedWriter bw;
	Textbox text;
	Label label;
	public Button button;
	Button buttonBack;
	public static boolean running = true; 
	public static String user = "Player";
	public boolean setup;
	public Client(){
		setup = true;
		text = new Textbox((Game.frame.getWidth() / 2) - (Images.textbox.getWidth() / 2), (Game.frame.getHeight() / 2) - 80, Images.textbox, 30, Images.textbox.getWidth(), Images.textbox.getHeight());
		label = new Label((Game.frame.getWidth() / 2) - 103, (Game.frame.getHeight() / 2) - 90, "Connect To: ");
		label.setColor(Color.WHITE);
		button = new Button((Game.frame.getWidth() / 2) - (200 / 2), (Game.frame.getHeight() / 2) - 40, "Enter", Images.button, 205, 35);
		button.setOffsetX(80);
		button.setColor(Color.BLACK);
		buttonBack = new Button((Game.frame.getWidth() / 2) - (200 / 2), (Game.frame.getHeight() / 2), "Back", Images.button, 205, 35);
		buttonBack.setOffsetX(82);
		buttonBack.setColor(Color.BLACK);
		start();
	}
	
	public void run(){
		while (!button.getClicked() && !buttonBack.getClicked()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		
		if(buttonBack.getClicked()){
			Game.state = GameState.MENU;
			AudioPlayer.getSound(Audio.mainMusic).stop();
			text.dispose();
			button.dispose();
			label.dispose();
			buttonBack.dispose();
			setup = false;
		}
		
		if (button.getClicked()) {
			ServerConstants.username = user;
			Username name = new Username(user);
			Game.getInstance().play.player.setName(name);
			AudioPlayer.getSound(Audio.menuMusic).stop();
			AudioPlayer.getSound(Audio.mainMusic).stop();
			AudioPlayer.getSound(Audio.mainMusic).play();
			ServerConstants.HOST = text.getText();
			text.dispose();
			button.dispose();
			label.dispose();
			buttonBack.dispose();
			button = null;
			setup = false;
			Game.getInstance().loading.setVisible(true);
			try {
				socket = new Socket(ServerConstants.HOST,
						ServerConstants.SOCKET);
			} catch (UnknownHostException e) {
			} catch (IOException e) {
			}
			try {
				in = new InputStreamReader(socket.getInputStream());
			} catch (IOException e) {
			} catch (NullPointerException e) {
				Game.state = GameState.MENU;
				PopUp popup = new PopUp((Game.frame.getWidth() / 2) - 100, (Game.frame.getHeight() / 2) - 45, "Could not Connect to Host.", 200, 100);
				popup.setBackColor(Color.LIGHT_GRAY);
				popup.setColor(Color.GRAY);
				AudioPlayer.getSound(Audio.mainMusic).stop();
				AudioPlayer.getSound(Audio.menuMusic).play();
				Game.frame.setVisible(true);
				setup = false;
			}

			if (in != null) {
				System.out.println("Connected");
				if(Game.getInstance().play.chat == null)
					Game.getInstance().play.chat = new Chat();
				Game.frame.setLocation(0, 0);
				setup = false;
			}

			try {
				out = new OutputStreamWriter(socket.getOutputStream());
				bw = new BufferedWriter(out);
			} catch (IOException e) {
			}

			try {
				bw.write(user + "\n");
			} catch (IOException x) {
			}

			ClientInput input = null;
			try {
				input = new ClientInput(in);
			} catch (IOException e1) {
			}
			Thread thread = new Thread(input);
			thread.start();
			running = true;
			while (running) {
				try {
					String sendJson = new Gson()
							.toJson(Game.getInstance().play.player.getEntity());
					ArrayList<Entity> stuff = new ArrayList<Entity>();
					for (Entity aEntity : ServerConstants.entities.values()) {
						if (aEntity.isBullet()) {
							stuff.add(aEntity);
						}
					}
					String sendJson1 = new Gson().toJson(stuff);
					String sendJson2 = new Gson().toJson(ClientConstants.texts);
					String sendJson3 = new Gson().toJson(ClientConstants.removeSound);
					ClientConstants.texts = "";
					bw.write(sendJson + "\n");
					bw.write(sendJson1 + "\n");
					bw.write(sendJson2 + "\n");
					bw.write(sendJson3 + "\n");
					Thread.sleep(1);
				} catch (IOException e) {
					Game.state = GameState.MENU;
					AudioPlayer.getSound(Audio.mainMusic).stop();
					AudioPlayer.getSound(Audio.menuMusic).play();
					Game.frame.setVisible(true);
					PopUp popup = new PopUp((Game.frame.getWidth() / 2) - 100, (Game.frame.getHeight() / 2) - 45, "Host has disconnected.", 200, 100);
					popup.setBackColor(Color.LIGHT_GRAY);
					popup.setColor(Color.GRAY);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}catch (ConcurrentModificationException e) {
				}
				
			}
		}
	}

}
