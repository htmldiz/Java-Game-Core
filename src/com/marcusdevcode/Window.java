package src.com.marcusdevcode;

import src.com.marcusdevcode.tinysound.TinySound;
import src.com.marcusdevcode.resources.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas{
    public JFrame frame;
    Main game;
    public static Window instance;
    public GraphicsDevice gd;
    public Dimension screenSize = new Dimension(0,0);
    public void addGame(Main Game){
        game = Game;
    }
    public Point getLocationOnScreen(){
        return frame.getLocationOnScreen();
    }
    public Dimension getScreenSize(){
        return screenSize;
    }
    public void startGame(){
        if(game != null) {
            game.start();
        }
    }
    public void addCursor(String ImagePath){
        Image cursorImage = ResourceLoader.getInstance().getBufferedImage(ImagePath);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
        if(frame != null) {
            frame.setCursor(customCursor);
        }
        if(game != null) {
            game.setCursor(customCursor);
        }
    }
    public void createFrame(){
        GraphicsDeviceManager gdm = GraphicsDeviceManager.getInstance();
        frame = new JFrame(gdm.getConfiguration());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.requestFocusInWindow();
        frame.setFocusable(true);
        screenSize.width = (int)gdm.getDisplayMode().getWidth();
        screenSize.height = (int)gdm.getDisplayMode().getHeight()-40;
        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setLocationRelativeTo(null);
//        gdm.getScreen().setFullScreenWindow(frame);
        if(game != null) {
            frame.getContentPane().add(game);
        }
        frame.setVisible(true);
    }
    public static Window getInstance(){
        if(instance == null){
            instance = new Window();
        }
        return instance;
    }
}
