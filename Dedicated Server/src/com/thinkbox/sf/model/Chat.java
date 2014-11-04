package com.thinkbox.sf.model;

import java.awt.Color;

import com.thinkbox.sf.constants.GameConstants;


public class Chat {
	private Label[] messages = new Label[9];
	private boolean focus = true;

	public Chat() {
		messages[0] = new Label(50, GameConstants.HEIGHT - 100, "");
		messages[1] = new Label(50, GameConstants.HEIGHT - 120, "");
		messages[2] = new Label(50, GameConstants.HEIGHT - 140, "");
		messages[3] = new Label(50, GameConstants.HEIGHT - 160, "");
		messages[4] = new Label(50, GameConstants.HEIGHT - 180, "");
		messages[5] = new Label(50, GameConstants.HEIGHT - 200, "");
		messages[6] = new Label(50, GameConstants.HEIGHT - 220, "");
		messages[7] = new Label(50, GameConstants.HEIGHT - 240, "");
		messages[8] = new Label(50, GameConstants.HEIGHT - 260, "");
	}

	public void add(String s) {
		messages[0].setColor(Color.WHITE);
		for (int i = 8; i > 0; i--) {
			messages[i].setColor(Color.WHITE);
			messages[i].setText(messages[i - 1].getText());
		}
		messages[0].setText(s);
	}

	public boolean getFocus() {
		return focus;
	}

	public Label[] getList() {
		return messages;
	}

	public String getString(int i) {
		return messages[i].getText();
	}

	public void set(Label[] t) {
		for (int i = 0; i < 8; i++) {
			messages[i].setColor(Color.WHITE);
			messages[i].setText(t[i].getText());
		}
	}
}
