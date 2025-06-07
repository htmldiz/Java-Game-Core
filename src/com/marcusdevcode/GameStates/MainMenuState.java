package src.com.marcusdevcode.GameStates;
import src.com.marcusdevcode.*;
import src.com.marcusdevcode.UI.MainMenuWindow;
import src.com.marcusdevcode.UI.SubWindow;
import src.com.marcusdevcode.resources.ResourceLoader;

import java.awt.*;
import java.util.HashMap;

public class MainMenuState extends Canvas implements IGameState {
    private SoundHendeler backgroundSound;
    Main game;
    Image background;
    boolean backgroundSoundPlaying;
    public SubWindow currentsubWindow;
    String currentsubWindowID;
    public HashMap<String,SubWindow> items  = new HashMap<String,SubWindow>();
    public MainMenuState(Main Game) {
        game = Game;
        SubWindow subWindow = new MainMenuWindow("Main menu",Game, "center",this);
        currentsubWindowID = "MainMenu";
        this.items.put(currentsubWindowID,subWindow);
        currentsubWindow = subWindow;
        MainMenuState mainMenuState = this;
    }

    public void switchSubWindow(String sub_window_id) {
        if(!currentsubWindowID.equals(sub_window_id)){
            currentsubWindow.dispose_events();
            currentsubWindowID = sub_window_id;
            currentsubWindow = items.get(sub_window_id);
            currentsubWindow.register_events();
        }
    }
        @Override
    public void tick() {
        currentsubWindow.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background,0,0,game.gd.getDisplayMode().getWidth(), game.gd.getDisplayMode().getHeight(),null);
        currentsubWindow.render(g);
    }

    @Override
    public void dispose_events() {
        if(backgroundSound != null) {
            backgroundSound.stop();
            backgroundSoundPlaying = false;
        }
        currentsubWindow.dispose_events();
    }

    @Override
    public void register_events() {
        if(backgroundSoundPlaying != true) {
            if(backgroundSound != null) {
                backgroundSound.play(true);
                backgroundSoundPlaying = true;
            }
        }
        currentsubWindow.register_events();
    }

    @Override
    public void loadResources() {
        if(backgroundSound == null) {
            backgroundSound = new SoundHendeler("resources/audio/main-menu.wav","music");
        }
        background      = ResourceLoader.getInstance().getBufferedImage("resources/images/sky.png");
    }
}
