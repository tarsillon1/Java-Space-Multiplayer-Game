package com.thinkbox.sf.control;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.model.Button;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.model.PopUp;
import com.thinkbox.sf.model.Textbox;

public class Renderer {
	public void act() {
		switch (Game.state) {
		case GAME:
			break;
		case MENU:
			break;

		case PAUSE:
			Game.getInstance().running = false;
			break;
		default:
			break;
		}
	}

	public void renderBackround(Graphics g) {
		switch (Game.state) {
		case GAME:
			Game.getInstance().play.render(g);
			break;
		case MENU:
			Game.getInstance().menu.render(g);
			break;
		case PAUSE:
			Game.getInstance().running = false;
			break;
		default:
			g.setColor(Color.RED);
			g.drawString("UNKNOWN GAMESTATE", 150, 150);
			break;
		}
		Game.getInstance().options.render(g);
		Game.getInstance().settings.render(g);
		//UI////////////////////////////////////////////////////////////////////////////////
		try {
			for (PopUp aPop : Game.getInstance().play.popups) {
				aPop.draw(g);
			}
			for (Textbox aText : Game.getInstance().play.textboxs) {
				aText.draw(g);
			}
			for (Label aLabel : Game.getInstance().play.labels) {
				aLabel.draw(g);
			}
			for (Button aButton : Game.getInstance().play.buttons) {
				aButton.draw(g);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
	}

	public void renderForeground(Graphics g) {
	}
}
