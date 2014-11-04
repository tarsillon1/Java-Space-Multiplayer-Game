package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.control.Timer;

public class CooldownTimer extends Thread {
	public CooldownTimer() {
	}

	public void run() {
		while (true) {

			try {
				sleep(GameConstants.COOLDOWN_INTERVAL);
				Game.getInstance().play.player.cooldown();
			} catch (InterruptedException e) {
			}
			try {

				for (Timer aTimer : Game.getInstance().play.timers) {
					aTimer.tick();
				}
			} catch (ConcurrentModificationException e) {
			}

		}

	}
}
