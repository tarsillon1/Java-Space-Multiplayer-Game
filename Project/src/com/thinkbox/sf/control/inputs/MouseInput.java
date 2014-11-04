package com.thinkbox.sf.control.inputs;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.GameState;
import com.thinkbox.sf.model.Button;
import com.thinkbox.sf.model.Textbox;
import com.thinkbox.sf.multiplayer.Client;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class MouseInput extends MouseAdapter {
	public static int MOUSE_X, MOUSE_Y;
	public static Rectangle MOUSE = new Rectangle(1, 1, 1, 1);
	public static boolean noShoot = false;

	@Override
	public void mousePressed(MouseEvent e) {
		int mouse = e.getButton();
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
		
		try {
			for (Button aButton : Game.getInstance().play.buttons) {
				if(aButton.getRect().intersects(rect) && aButton.isVisible() && !aButton.getClicked()){
					aButton.clicked();
					AudioPlayer.getSound(Audio.click).play();
					break;
				}
			}
			for (Textbox aText : Game.getInstance().play.textboxs) {
				if(aText.getRect().intersects(rect)){
					aText.clicked();
					AudioPlayer.getSound(Audio.click).play();
				}
			}
		} catch (ConcurrentModificationException a) {
		} catch (NoSuchElementException a) {
		} catch (NullPointerException a) {
		}
		
		if (mouse == MouseEvent.BUTTON1) {
			if (Game.getInstance().options.getOpen()) {
				if (Game.getInstance().options.exit.intersects(MOUSE)) {
					AudioPlayer.getSound(Audio.click).play();
					Game.getInstance().options.setOpen(false);
				}
			}

			switch (Game.state) {
			case GAME:
				if(Game.getInstance().client.setup == false && !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()){
					Game.getInstance().play.player.shoot();
				}
				break;
			case MENU:
				if (MOUSE.intersects(Game.getInstance().menu.play) && Game.getInstance().play.popups.isEmpty() 
						&& !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()) {
					Game.state = GameState.GAME;
					Game.getInstance().play.map.setCurrent(
							GameConstants.STARTX, GameConstants.STARTY);
					Game.getInstance().client = new Client();
					GameConstants.server = false;
				}
				if (MOUSE.intersects(Game.getInstance().menu.options) && Game.getInstance().play.popups.isEmpty()
						&& !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()) {
					Game.getInstance().options.setOpen(true);
					AudioPlayer.getSound(Audio.click).play();
				}

				if (rect.intersects(Game.getInstance().menu.quit) && Game.getInstance().play.popups.isEmpty()
						&& !Game.getInstance().options.getOpen() && !Game.getInstance().settings.getOpen()) {
					AudioPlayer.getSound(Audio.click).play();
					System.exit(0);
				}
				break;
			case PAUSE:
				break;
			case LOADING:
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		MOUSE = new Rectangle(MOUSE_X, MOUSE_Y, 1, 1);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		switch (Game.state) {
		case GAME:
			if(Game.getInstance().client.setup == false){
				Game.getInstance().play.player.shoot();
			}
		case MENU:
		case PAUSE:
			break;
		default:
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			for (Button aButton : Game.getInstance().play.buttons) {
				if(aButton.getClicked()){
					aButton.released();
				}
			}
		} catch (ConcurrentModificationException a) {
		} catch (NoSuchElementException a) {
		} catch (NullPointerException a) {
		}
	}
}
