package com.thinkbox.sf.control.timers;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.constants.ServerConstants;
import com.thinkbox.sf.utils.Audio;
import com.thinkbox.sf.utils.AudioPlayer;

public class MovementTimer extends Thread {
	private boolean entered;

	public MovementTimer() {

	}

	public void run() {
		while (true) {
			Game.getInstance().play.player.aTick();
			if (Game.getInstance().play.player.getTick() >= Game.getInstance().play.player
					.getAcceleration()) {
				Game.getInstance().play.player.aReset();
				if (!(Game.getInstance().play.player.getAcceleration() == GameConstants.MAX_INTERVAL)) {
						Game.getInstance().play.player.move();
						if(!AudioPlayer.getSound(Audio.moving).playing()){
							AudioPlayer.getSound(Audio.moving).loop();
						}
						entered = false;
				}
				else
					AudioPlayer.getSound(Audio.moving).stop();

				if ((ServerConstants.Y / 2)
						- Game.getInstance().play.map.getDrawY() < 0) {
					if (entered == false) {
						Game.getInstance().play.map.setCurrent(
								Game.getInstance().play.map.getX(),
								Game.getInstance().play.map.getY() + 1);
						Game.getInstance().play.map.setCenter(
								Game.getInstance().play.map.getDrawX(),
								-Game.getInstance().play.map.getDrawY() + 1);
						Game.getInstance().play.map.setNegY(true);
						entered = true;
					}
				}

				else if ((ServerConstants.Y / 2)
						+ Game.getInstance().play.map.getDrawY() < 0) {
					if (entered == false) {
						Game.getInstance().play.map.setCurrent(
								Game.getInstance().play.map.getX(),
								Game.getInstance().play.map.getY() - 1);
						Game.getInstance().play.map.setCenter(
								Game.getInstance().play.map.getDrawX(),
								-Game.getInstance().play.map.getDrawY());
						Game.getInstance().play.map.setNegY(false);
						entered = true;
					}
				}

				else if ((ServerConstants.X / 2)
						+ Game.getInstance().play.map.getDrawX() < 0) {
					if (entered == false) {
						Game.getInstance().play.map.setCurrent(
								Game.getInstance().play.map.getX() + 1,
								Game.getInstance().play.map.getY());
						Game.getInstance().play.map.setCenter(
								-Game.getInstance().play.map.getDrawX(),
								Game.getInstance().play.map.getDrawY());
						Game.getInstance().play.map.setNegX(false);
						entered = true;
					}
				}

				else if ((ServerConstants.X / 2)
						- Game.getInstance().play.map.getDrawX() < 0) {
					if (entered == false) {
						Game.getInstance().play.map.setCurrent(
								Game.getInstance().play.map.getX() - 1,
								Game.getInstance().play.map.getY());
						Game.getInstance().play.map.setCenter(
								-Game.getInstance().play.map.getDrawX() + 1,
								Game.getInstance().play.map.getDrawY());
						Game.getInstance().play.map.setNegX(true);
						entered = true;
					}
				}
			}
			try {
				sleep(GameConstants.MOVEMENT_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}
}
