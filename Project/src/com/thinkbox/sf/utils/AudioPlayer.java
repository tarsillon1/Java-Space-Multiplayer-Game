package com.thinkbox.sf.utils;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.thinkbox.sf.constants.GameConstants;
import com.thinkbox.sf.views.Load;

public class AudioPlayer {
	private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	private static String lastSound;
	private static boolean muted;
	public static void addSound(String key, String path) {
		Load.setMessage("Loading audio from " + GameConstants.SOUND_LOCATION);
		try {
			soundMap.put(key, new Sound(path));
		} catch (SlickException e) {
		}
	}
	public static void mute(boolean b){
		if(b == true){
			soundMap.get(lastSound).stop();
			muted = true;
		}
		else{
			muted = false;
			soundMap.get(lastSound).play();
		}
	}
	public static Sound getSound(String key) {
		if(muted == true && (key == Audio.mainMusic || key == Audio.menuMusic))
			key = Audio.mute;
		else{
			if(key == Audio.mainMusic || key == Audio.menuMusic)
				lastSound = key;
		}
		return soundMap.get(key);
	}
}
