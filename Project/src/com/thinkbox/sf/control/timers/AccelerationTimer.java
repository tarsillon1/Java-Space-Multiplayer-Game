package com.thinkbox.sf.control.timers;
import com.thinkbox.sf.Game;
import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.control.inputs.KeyInput;


public class AccelerationTimer extends Thread {
	public AccelerationTimer(){
		
	}
	
	public void run() {
		while (true) {
			if(KeyInput.W == true && Game.getInstance().play.player.getAcceleration() > 9){
				Game.getInstance().play.player.setAcceletion(Game.getInstance().play.player.getAcceleration() - 1);
			}
			
			if(KeyInput.W == false && Game.getInstance().play.player.getAcceleration() < GameConstants.MAX_INTERVAL){
				Game.getInstance().play.player.setAcceletion(Game.getInstance().play.player.getAcceleration() + 1);
			}
			
			try {
				sleep(GameConstants.ACCEL_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}
}
