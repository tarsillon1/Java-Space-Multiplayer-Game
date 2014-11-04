package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Fog_Particle;

public class ParticleTimer extends Thread{
	public ParticleTimer() {
	}

	public void run() {
		while (true) {
			if (GameConstants.step == 1) {

				try {
					for (Fog_Particle aParticle : Game.getInstance().play.particles) {
						aParticle.act();
					}
				} catch (ConcurrentModificationException e) {
				} catch (NoSuchElementException e) {
				} catch (NullPointerException e) {
				}
				
				Game.getInstance().play.fog.generate();
				Game.getInstance().play.stars.setCreate(true);
				Game.getInstance().play.stars.generate();
				GameConstants.step = 0;
			}

			try {
				Thread.sleep(GameConstants.GENERATE_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}
}
