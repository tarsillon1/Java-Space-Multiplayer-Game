package com.thinkbox.sf.multiplayer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.BiomeTypeConstants;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.constants.WeaponConstants;
import com.thinkbox.sf.model.Chat;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.model.OtherPlayer;


public class Server extends Thread{
	
	static ServerSocket serverSocket;
	static Socket socket;
	static OutputStreamWriter out;
	static InputStreamReader in;
	public boolean running = true; 
	Label label;
	public static boolean setup;
	static User[] user = new User[ServerConstants.PLAYERS];

	
	public Server() throws InterruptedException{
		start();
	}
	
	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}
	
	public void run() {
			setup = false;
			System.out.println("Starting server...");
			try {
				Game.getInstance().play.bMap.addBiomeType(BiomeTypeConstants.asteriods, 1);
				Game.getInstance().play.bMap.addBiomeType(BiomeTypeConstants.none, 1);
				Game.getInstance().play.bMap.generate();
			} catch (CloneNotSupportedException e) {
			}
			try {
				serverSocket = new ServerSocket(ServerConstants.SOCKET);
			} catch (IOException e1) {

			}
			Game.getInstance().play.chat = new Chat();
			System.out.println("Server started");

			while (running) {
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
				}
				System.out.println("Connection from: "
						+ socket.getInetAddress());
				for (int i = 0; i < ServerConstants.PLAYERS; i++) {
					try {
						out = new OutputStreamWriter(socket.getOutputStream());
					} catch (IOException e) {
					}
					try {
						in = new InputStreamReader(socket.getInputStream());
					} catch (IOException e) {
					}
					if (user[i] == null) {
						try {
							user[i] = new User(out, in, user, i,
									new OtherPlayer(
											WeaponConstants.playerWeapon1,
											GameConstants.WIDTH / 2,
											GameConstants.HEIGHT / 2,
											GameConstants.STARTX,
											GameConstants.STARTY));
						} catch (IOException e) {
						}
						Thread thread = new Thread(user[i]);
						thread.start();
						break;
					}
				}
			}
		}
	}

