package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Bullet;

public class BulletTimer extends Thread {
	public BulletTimer() {
	}

	public void run() {
		while (true) {
			try {
				for (Bullet aBullet : Game.getInstance().play.player.getList()) {
					if (aBullet.isActive()) {
						aBullet.move();
					}
				}
			} catch (ConcurrentModificationException e) {
			} catch (NullPointerException e) {
			} catch (NoSuchElementException e) {
			}

			try {
				sleep(GameConstants.BULLET_INTERVAL);
			} catch (InterruptedException e) {
			}

		}
	}
}
