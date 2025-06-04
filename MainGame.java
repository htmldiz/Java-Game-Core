import src.com.marcusdevcode.Main;

import javax.swing.*;

public class MainGame extends JFrame {
    public static void main(String[] args) {
        new Main();
    }
//    public MainGame(){
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setTitle("Test Sound Clip");
//        this.setSize(300, 200);
//        this.setVisible(true);
//        //initialize TinySound
//        TinySound.init();
//        //load a sound and music
//        //note: you can also load with Files, URLs and InputStreams
//        Music song = TinySound.loadMusic("resources/audio/epic.wav");
//        Sound coin = TinySound.loadSound("resources/audio/hovering.wav");
//        //start playing the music on loop
//        song.play(true);
//        //play the sound a few times in a loop
//        for (int i = 0; i < 20; i++) {
//            coin.play();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {}
//        }
//        //be sure to shutdown TinySound when done
//        TinySound.shutdown();
//    }
//
//        public static void main(String[] args) {
//            new MainGame();
//        }
}
