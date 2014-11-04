package com.thinkbox.sf.views;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.ClientConstants;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.Images;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.constants.WeaponConstants;
import com.thinkbox.sf.control.GenerateFog;
import com.thinkbox.sf.control.GenerateStars;
import com.thinkbox.sf.control.TileMap;
import com.thinkbox.sf.control.Timer;
import com.thinkbox.sf.model.Button;
import com.thinkbox.sf.model.Chat;
import com.thinkbox.sf.model.Fog_Particle;
import com.thinkbox.sf.model.Label;
import com.thinkbox.sf.model.Player;
import com.thinkbox.sf.model.PopUp;
import com.thinkbox.sf.model.Textbox;
import com.thinkbox.sf.multiplayer.Entity;
import com.thinkbox.sf.multiplayer.Username;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class Play {
	public ArrayList<Fog_Particle> particles = new ArrayList<Fog_Particle>();
	public ArrayList<Timer> timers = new ArrayList<Timer>();
	public ArrayList<Username> names = new ArrayList<Username>();
	public ArrayList<Textbox> textboxs = new ArrayList<Textbox>();
	public ArrayList<Label> labels = new ArrayList<Label>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<PopUp> popups = new ArrayList<PopUp>();
	
	public Player player = new Player(WeaponConstants.playerWeapon1);
	public TileMap map = new TileMap(50, 50, player);
	public GenerateFog fog = new GenerateFog();
	public GenerateStars stars = new GenerateStars();
	public Chat chat;
	
	private int step;

	public Play() {
	}

	public static void resize(BufferedImage img, int newW, int newH,
			Graphics g, int x, int y) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2 = dimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, x, y, newW, newH, null);
	}
	
	public void render(Graphics g) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Game.frame.setLocation(0, 0);
		map.draw(g);
			//Tempt Entity's//////////////////////////////////////////////////////////////////////////////////////
			try {
				for (int i = 0; i < GameConstants.LAYERS; i++) {
					for (Entity aEntity : ClientConstants.temp) {
						if(aEntity.getDrawLevel() == i)
							aEntity.draw(g);
					}
				}
			} catch (ConcurrentModificationException e) {
			} catch (NoSuchElementException e) {
			} catch (NullPointerException e) {
			}
		// Effects/////////////////////////////////////////////////////////////////////////////
		if (GameConstants.step == 0) {
			try {
				for (Fog_Particle aParticle : particles) {
					aParticle.draw(g);
				}
			} catch (ConcurrentModificationException e) {
			} catch (NoSuchElementException e) {
			} catch (NullPointerException e) {
			}
			GameConstants.step = 1;
		}
		//////////////////////////////////////////////////////////////////////
		player.draw(g);
		//Units////////////////////////////////////////////////////////////////////////////////
		try {
			for (Username aName : names) {
				aName.draw(g);
			}
		} catch (ConcurrentModificationException e) {
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
		//Darkness
		resize(Images.dark,
				ServerConstants.X + 2,
				ServerConstants.Y + 2, g, 0, 0);
		// /////////////////////////////////////////////////////////////////////////////////////
		if(Game.getInstance().client.setup){
			Graphics2D g2d = (Graphics2D) g;
			float opacity = 1f;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
				resize(Images.mainBack, Game.frame.getWidth(), Game.frame.getHeight(), g, 0, 0);
			if((step * 0.025) < 1){
				opacity = (float) (step * 0.025);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			}
			step += 1;
			resize(Images.clientBack, (int) width, (int) height + 30,
					g, 0, 0);
		}
		Game.getInstance().loading.draw(g);
	}

	public void playMusic() {
		AudioPlayer.getSound(Audio.mainMusic).loop();
	}
}
