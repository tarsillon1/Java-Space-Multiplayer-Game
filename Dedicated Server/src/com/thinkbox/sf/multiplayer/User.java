package com.thinkbox.sf.multiplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.google.gson.Gson;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.model.OtherPlayer;

public class User implements Runnable {
	private InputStreamReader in;
	private OutputStreamWriter out;
	private ObjectOutputStream objOut;
	private OtherPlayer player;
	private boolean setup;
	private int u;

	User[] users = new User[ServerConstants.PLAYERS];
	BufferedWriter bw;
	BufferedReader br;
	private ServerInput input;

	public User(OutputStreamWriter out, InputStreamReader in, User[] user,
			int u, OtherPlayer p) throws IOException {
		this.setOut(out);
		this.setIn(in);
		this.users = user;
		this.u = u;
		player = p;
		Game.getInstance().play.players.add(player);
		bw = new BufferedWriter(out);

		setup = true;
		try {
			input = new ServerInput(in, player);
		} catch (IOException e1) {
		}
		Thread thread = new Thread(input);
		thread.start();
	}

	@Override
	public void run() {
		while (Game.getInstance().server.running) {
			if (setup == true) {
				try {
					ArrayList<Entity> e = new ArrayList<Entity>();
					e.addAll(ServerConstants.entities.values());
					e.addAll(ClientConstants.temp);
					e.remove(player.getEntity());
					try {
						e.remove(input.getName().getEntity());
					} catch (NullPointerException x) {
					}
					if (input.getExclude() != null)
						e.removeAll(input.getExclude());
					String sendJson = new Gson().toJson(e);
					Label[] t = Game.getInstance().play.chat.getList();
					String sendJson2 = new Gson().toJson(t);
					String sendJson3 = new Gson().toJson(ServerConstants.sound);
					String sendJson4 = new Gson().toJson(ServerConstants.remove);
					// Send entities
					bw.write(sendJson + "\n");
					// Send Chat
					bw.write(sendJson2 + "\n");
					//Send Sound
					bw.write(sendJson3 + "\n");
					//Send Remove
					bw.write(sendJson4 + "\n");
					Thread.sleep(1);
				} catch (IOException e) {
					e.printStackTrace();
					Game.getInstance().play.chat.add(player.getName().getUser() + " left the game!");
					this.setOut(null);
					this.setIn(null);
					Server.user[u] = null;
					Game.getInstance().play.names.remove(player.getName());
					ServerConstants.entities.remove(player.getName().getKey());
					player.setName(null);
					Game.getInstance().play.players.remove(player);
					player.remove();
					player = null;
					setup = false;
					System.out.println("User has left game");
					break;
				} catch (NullPointerException b) {
					b.printStackTrace();
					this.setOut(null);
					this.setIn(null);
					Server.user[u] = null;
					Game.getInstance().play.names.remove(player.getName());
					ServerConstants.entities.remove(player.getName().getKey());
					player.setName(null);
					Game.getInstance().play.players.remove(player);
					player.remove();
					player = null;
					System.out.println("User has left game");
					break;
				} catch (InterruptedException e) {
				}catch (ConcurrentModificationException e) {
				}
			}
		}
	}

	public InputStreamReader getIn() {
		return in;
	}

	public void setIn(InputStreamReader in) {
		this.in = in;
	}

	public OutputStreamWriter getOut() {
		return out;
	}

	public void setOut(OutputStreamWriter out) {
		this.out = out;
	}

	public ObjectOutputStream getObjOut() {
		return objOut;
	}

	public void setObjOut(ObjectOutputStream objOut) {
		this.objOut = objOut;
	}

}
