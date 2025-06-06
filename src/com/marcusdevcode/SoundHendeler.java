package src.com.marcusdevcode;

import src.com.marcusdevcode.tinysound.Music;
import src.com.marcusdevcode.tinysound.Sound;
import src.com.marcusdevcode.tinysound.TinySound;

import javax.sound.sampled.*;

public class SoundHendeler {
    public Clip cp;
    public AudioInputStream inputStream;
    public FloatControl volumeControl;
    public Sound sound;
    public Music music;
    public String type;
    public SoundHendeler(String path,String type) {
        this.type = type;
        if(type == "sound") {
            sound = TinySound.loadSound(this.getClass().getClassLoader().getResource(path));
        }
        if(type == "music") {
            music = TinySound.loadMusic(this.getClass().getClassLoader().getResource(path));
        }
    }
    public void play(boolean loop){
        if(type == "sound") {
            sound.play();
        }
        if(type == "music") {
            music.play(loop);
        }
    }
    public void stop(){
        if(type == "sound") {
            sound.stop();
        }
        if(type == "music") {
            music.stop();
        }
    }
}