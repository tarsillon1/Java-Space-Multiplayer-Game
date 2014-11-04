package com.thinkbox.sf.control.timers;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;

public class CheckTimer extends Thread {
	public CheckTimer() {
	}

	public void run() {
		while (true) {
			Game.getInstance().play.bMap.check();
			try {
				sleep(GameConstants.CHECK_INTERVAL);
			} catch (InterruptedException e) {
			}

		}
	}
}
