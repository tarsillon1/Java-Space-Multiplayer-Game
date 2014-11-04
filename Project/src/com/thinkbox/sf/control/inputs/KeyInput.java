package com.thinkbox.sf.control.inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.model.Textbox;

public class KeyInput extends KeyAdapter {
	public static boolean W = false;
	public static boolean A = false;
	public static boolean S = false;
	public static boolean D = false;

	public KeyInput() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (Game.state) {
		case GAME:
			
			try {
				for (Textbox aText : Game.getInstance().play.textboxs) {
					aText.keyPressed(e);
				}
			} catch (ConcurrentModificationException g) {
			} catch (NoSuchElementException g) {
			} catch (NullPointerException g) {
			}
			
			if (key == KeyEvent.VK_W && !Game.getInstance().play.chat.getTextbox().isVisible()) {
				W = true;
				Game.getInstance().play.player.setAction("moving");
			}
			
			if (key == KeyEvent.VK_ENTER) {
				Game.getInstance().play.chat.focus();
			}

			if (key == KeyEvent.VK_ESCAPE && Game.getInstance().client.setup == false && Game.getInstance().play.popups.isEmpty()) {
				if (Game.getInstance().options.getOpen() || Game.getInstance().settings.getOpen()) {
					Game.getInstance().options.setOpen(false);
					Game.getInstance().settings.setOpen(false);
				} else {
					Game.getInstance().options.setOpen(true);
					try {
						for (Textbox aText : Game.getInstance().play.textboxs) {
							if (aText.isSelected()) {
								aText.setSelected(false);
							}
						}
					} catch (ConcurrentModificationException a) {
					} catch (NoSuchElementException a) {
					} catch (NullPointerException a) {
					}
				}
			}
			
			break;
		case MENU:
			if (key == KeyEvent.VK_ESCAPE) {
				if (Game.getInstance().options.getOpen() || Game.getInstance().settings.getOpen()) {
					Game.getInstance().options.setOpen(false);
					Game.getInstance().settings.setOpen(false);
				} else {
					Game.getInstance().options.setOpen(true);
					try {
						for (Textbox aText : Game.getInstance().play.textboxs) {
							if (aText.isSelected()) {
								aText.setSelected(false);
							}
						}
					} catch (ConcurrentModificationException a) {
					} catch (NoSuchElementException a) {
					} catch (NullPointerException a) {
					}
				}
			}
			break;
		case PAUSE:
			break;
		default:
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (Game.state) {
		case GAME:
			if (key == KeyEvent.VK_W) {
				W = false;
				Game.getInstance().play.player.setAction("stand");
			}
			
			break;
		case MENU:
			break;
		case PAUSE:
			break;
		default:
			break;

		}
	}
}
