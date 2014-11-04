package com.thinkbox.sf;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.thinkbox.sf.constants.BiomeTypeConstants;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.control.timers.AnimationTimer;
import com.thinkbox.sf.control.timers.CheckTimer;
import com.thinkbox.sf.multiplayer.Server;
import com.thinkbox.sf.utils.ResourceLoader;
import com.thinkbox.sf.views.Play;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -8381964091870710794L;

	public static JFrame frame = new JFrame();
	private static Game game = new Game();
	public static GameState state = GameState.LOADING;
	public boolean running = false;
	private Thread thread;
	public Play play;
	public Server server;
	public static GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static GraphicsDevice device = env.getDefaultScreenDevice();

	private int time;
	private int counter = 0;

	public void tick() {
		if (Game.state == GameState.LOADING) {
			time--;
			if (time <= 0) {
				load();
				time = 1;
			}
		}
		else
			Game.getInstance().play.update();
	}

	public static Game getInstance() {
		return game;
	}

	public void load() {
		switch (counter) {
		case 0:
			ResourceLoader.loadImages();
			counter++;
			return;
		case 1:
			counter++;
			return;
		case 2:
			counter++;
			return;
		case 3:
			play = new Play();
			BiomeTypeConstants.setTypes();
			counter++;
			return;
		case 4:
			new CheckTimer().start();
			new AnimationTimer().start();
			counter++;
			return;
		case 5:
			counter++;
			return;
		case 6:
			counter++;
			state = GameState.PLAY;
			return;
		}
	}

	@SuppressWarnings("static-access")
	public void run() {
		while (running) {
			tick();
			try {
				new Thread().sleep(GameConstants.GAMELOOP_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
		stop();
	}
	
    public static void setFullScreen(java.awt.DisplayMode displayMode, JFrame g){
        device.setFullScreenWindow(frame);

        if(displayMode!=null && device.isDisplayChangeSupported()){
            try {
            	device.setDisplayMode(displayMode);
            } catch (Exception e){}
        }
    }

	public static void main(String args[]) {
		game.start();
	}

	private synchronized void start() {
		if (running)
			return;
		else
			running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		else
			running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
		System.exit(1);
	}
}


