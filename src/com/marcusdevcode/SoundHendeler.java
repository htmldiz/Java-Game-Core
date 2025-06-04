package src.com.marcusdevcode;

import src.com.marcusdevcode.tinysound.Sound;
import src.com.marcusdevcode.tinysound.TinySound;

import javax.sound.sampled.*;

public class SoundHendeler {
    public Clip cp;
    public AudioInputStream inputStream;
    public FloatControl volumeControl;
    public Sound sound;
    public SoundHendeler(String path) {
        sound = TinySound.loadSound(this.getClass().getClassLoader().getResource(path));
    }
    public void play(){
        sound.play();
    }
    public void stop(){
        sound.stop();
    }
}