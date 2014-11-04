package com.thinkbox.sf.control.timers;

import java.util.ConcurrentModificationException;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.model.Animation;

public class AnimationTimer extends Thread{
	public AnimationTimer() {
	}

	public void run() {
		while (true) {
			try{
				for(Animation animation: Game.getInstance().play.animations){
					animation.update();
				}
				} catch (ConcurrentModificationException e) {
			}
			
			try {
				sleep(GameConstants.ANIMATION_INTERVAL);
			} catch (InterruptedException e) {
			}

		}
	}
}
