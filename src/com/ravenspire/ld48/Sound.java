package com.ravenspire.ld48;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static Sound music = new Sound("/resources/Sounds/music.wav");

	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() 
	{
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
		}
	}
	
	public void loop() 
	{
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		} catch (Throwable e) {
		}
	}
	
	public void stop() 
	{
		try {
			new Thread() {
				public void run() {
					clip.stop();
				}
			}.start();
		} catch (Throwable e) {
		}
	}
}