package com.thinkbox.sf.model;

import java.awt.Color;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;


public class Chat {
	private Label[] messages = new Label[9];
	private boolean focus = true;
	private Textbox text;

	public Chat() {
		text = new Textbox(47, Game.frame.getHeight() - 80, Images.textbox,
				100, Images.textbox.getWidth() + 450,
				Images.textbox.getHeight());
		messages[0] = new Label(50, Game.frame.getHeight() - 100, "");
		messages[1] = new Label(50, Game.frame.getHeight() - 120, "");
		messages[2] = new Label(50, Game.frame.getHeight() - 140, "");
		messages[3] = new Label(50, Game.frame.getHeight() - 160, "");
		messages[4] = new Label(50, Game.frame.getHeight() - 180, "");
		messages[5] = new Label(50, Game.frame.getHeight() - 200, "");
		messages[6] = new Label(50, Game.frame.getHeight() - 220, "");
		messages[7] = new Label(50, Game.frame.getHeight() - 240, "");
		messages[8] = new Label(50, Game.frame.getHeight() - 260, "");
		text.setVisible(false);
	}

	public void focus() {
		if (focus == true) {
			text.setVisible(true);
			text.setSelected(true);
			focus = false;
		}

		else if (focus == false) {
			if (text.getText().equals("")
					|| text.getText().replaceAll(" ", "") == "") {
				text.setVisible(false);
				text.setSelected(false);
				focus = true;
			} else {
				text.setSelected(true);
				enter();
			}

		}
	}

	public void show(boolean b) {
		if(b == false){
			for(Label aLabel: messages){
				aLabel.setShow(false);
			}
		}
		else
			for(Label aLabel: messages){
				aLabel.setShow(true);
			}
	}

	public void add(String s) {
		messages[0].setColor(Color.WHITE);
		for (int i = 8; i > 0; i--) {
			messages[i].setColor(Color.WHITE);
			messages[i].setText(messages[i - 1].getText());
		}
		messages[0].setText(s);
	}

	public void enter() {
		if (GameConstants.server == true) {
			messages[0].setColor(Color.WHITE);
			for (int i = 8; i > 0; i--) {
				messages[i].setColor(Color.WHITE);
				messages[i].setText(messages[i - 1].getText());
			}
			messages[0].setText(ServerConstants.username + ": "
					+ text.getText());
			text.setText("");
		} else{
			ClientConstants.texts = ServerConstants.username + ": " + text.getText();
			text.setText("");
		}
	}

	public Textbox getTextbox() {
		return text;
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

	public void clear() {
		for (int i = 0; i < 7; i++) {
			messages[i].setText("");
			text.setText("");
		}
	}

	public void set(Label[] t) {
		for (int i = 0; i < 8; i++) {
			messages[i].setColor(Color.WHITE);
			messages[i].setText(t[i].getText());
		}
	}
}
