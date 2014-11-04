package com.thinkbox.sf.control.timers;

import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.control.inputs.MouseInput;


public class LocationTimer extends Thread {
	public LocationTimer(){
		
	}
	
	public void run() {
		while (true) {
			Game.getInstance().play.player.setGoX(MouseInput.MOUSE_X);
			Game.getInstance().play.player.setGoY(MouseInput.MOUSE_Y);
			try {
				sleep(GameConstants.LOCINTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}
}
