package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sounds() {
		soundURL[0] = getClass().getResource("/sounds/27 - Chill.wav");
		soundURL[1] = getClass().getResource("/sounds/chestopening.wav");
		soundURL[2] = getClass().getResource("/sounds/dooropening.wav");
		soundURL[3] = getClass().getResource("/sounds/itempickup.wav");
	}
	public void setFile(int i) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
				clip = AudioSystem.getClip();
				clip.open(ais);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
