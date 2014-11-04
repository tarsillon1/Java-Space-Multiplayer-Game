package com.thinkbox.sf.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.model.Button;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.utils.AudioPlayer;

public class Settings {
	private Label fog = new Label((Game.frame.getWidth() / 2) - 225, (Game.frame.getHeight() / 2) - 90, "Fog Effects:");
	private Button check1 = new Button((Game.frame.getWidth() / 2) - 55, (Game.frame.getHeight() / 2) - 125, "", Images.noCheck, 50, 50);
	private Button check2 = new Button((Game.frame.getWidth() / 2) - 55, (Game.frame.getHeight() / 2) - 65, "", Images.noCheck, 50, 50);
	private Button check3 = new Button((Game.frame.getWidth() / 2) - 55, (Game.frame.getHeight() / 2) - 5, "", Images.noCheck, 50, 50);
	private Label fire = new Label((Game.frame.getWidth() / 2) - 225, (Game.frame.getHeight() / 2) - 30, "Fire Effects:");
	private Label music = new Label((Game.frame.getWidth() / 2) - 225, (Game.frame.getHeight() / 2) + 30, "Mute Music:");
	public Button back = new Button((Game.frame.getWidth() / 2) - 100, (Game.frame.getHeight() / 2) + 80, "Back", Images.button, 200, 60);

	private boolean open;

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean b) {
		open = b;
	}

	public Settings() {
	}


	public void render(Graphics g) {
		FontMetrics metrics = g.getFontMetrics(new Font("Arial", Font.PLAIN, 20));
		if (open) {
				g.drawImage(Images.optionsB, (Game.frame.getWidth() / 2) - (Images.optionsB.getWidth() / 2), (Game.frame.getHeight() / 2) - (Images.optionsB.getHeight() / 2), null);
				fog.setColor(Color.WHITE);
				fire.setColor(Color.WHITE);
				music.setColor(Color.WHITE);
				fog.setSize(30);
				fire.setSize(30);
				music.setSize(30);
				fog.setShow(true);
				fire.setShow(true);
				music.setShow(true);
				int textwidth = metrics.stringWidth("Back");
				back.setFont(20);
				back.setOffsetX((back.getSizeX() / 2) - (textwidth / 2));
				back.setVisible(true);
				check1.setVisible(true);
				check2.setVisible(true);
				check3.setVisible(true);
				
				if(check1.getClicked()){
					if(check1.getActor() == Images.noCheck){
						check1.setActor(Images.check);
						Game.getInstance().play.fog.setCreate(true);
						check1.released();
					}
					else{
						check1.setActor(Images.noCheck);
						Game.getInstance().play.fog.setCreate(false);
						check1.released();
					}
				}
				
				if(check2.getClicked()){
					if(check2.getActor() == Images.noCheck){
						check2.setActor(Images.check);
						check2.released();
					}
					else{
						check2.setActor(Images.noCheck);
						check2.released();
					}
				}
				
				if(check3.getClicked()){
					if(check3.getActor() == Images.noCheck){
						check3.setActor(Images.check);
						AudioPlayer.mute(true);
						check3.released();
					}
					else{
						check3.setActor(Images.noCheck);
						AudioPlayer.mute(false);
						check3.released();
					}
				}
				if(back.getClicked()){
					Game.getInstance().settings.setOpen(false);
					Game.getInstance().options.setOpen(true);
				}
			}
		else{
			fog.setShow(false);
			check1.setVisible(false);
			check2.setVisible(false);
			check3.setVisible(false);
			fire.setShow(false);
			music.setShow(false);
			back.setVisible(false);
		}
		}

	public boolean getCheck2() {
		if(check2.getActor() == Images.check){
			return true;
		}
		return false;
	}
	}


