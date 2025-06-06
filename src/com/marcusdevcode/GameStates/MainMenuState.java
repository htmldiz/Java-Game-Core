package src.com.marcusdevcode.GameStates;
import src.com.marcusdevcode.*;
import src.com.marcusdevcode.UI.GMenuItem;
import src.com.marcusdevcode.UI.SubWindow;
import src.com.marcusdevcode.eventListeners.MouseEventCallback;
import src.com.marcusdevcode.resources.ResourceLoader;
import src.com.marcusdevcode.tinysound.TinySound;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenuState extends Canvas implements IGameState {
    private final ArrayList<GMenuItem> menuItems = new ArrayList<GMenuItem>();
    private SoundHendeler backgroundSound;
    Main game;
    Image background;
    boolean backgroundSoundPlaying;
    SubWindow subWindow;
    ArrayList<GMenuItem>items  = new ArrayList<GMenuItem>();
    public MainMenuState(Main Game) {
        TinySound.init();
        game = Game;
        subWindow = new SubWindow("Main menu",Game, "center");
        subWindow.loadResources();
        int fbnt = 100;
        GMenuItem PlayItem = new GMenuItem(Game,"Play",new Point(subWindow.size.width/2,fbnt));
        GMenuItem OptionsItem = new GMenuItem(Game,"Options",new Point(subWindow.size.width/2,fbnt+70));
        GMenuItem ExitItem = new GMenuItem(Game,"Exit",new Point(subWindow.size.width/2,fbnt+70+fbnt));
        this.menuItems.add(OptionsItem);
        this.menuItems.add(PlayItem);
        this.menuItems.add(ExitItem);
        PlayItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                game.change_state(GameState.IN_GAME);
            }
        });
        ExitItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                Game.closeGame();
            }
        });
        for (int i = 0; i < this.menuItems.size(); i++) {
            GMenuItem gMenuItem = this.menuItems.get(i);
            gMenuItem.loadResources();
            subWindow.addButton(gMenuItem);
        }
    }

    @Override
    public void tick() {
        subWindow.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background,0,0,game.gd.getDisplayMode().getWidth(), game.gd.getDisplayMode().getHeight(),null);
        subWindow.render(g);
    }

    @Override
    public void dispose_events() {
        if(backgroundSound != null) {
            backgroundSound.stop();
            backgroundSoundPlaying = false;
        }
        subWindow.dispose_events();
    }

    @Override
    public void register_events() {
        if(backgroundSoundPlaying != true) {
            if(backgroundSound != null) {
                backgroundSound.play(true);
                backgroundSoundPlaying = true;
            }
        }
        subWindow.register_events();
    }

    @Override
    public void loadResources() {
        if(backgroundSound == null) {
            backgroundSound = new SoundHendeler("resources/audio/main-menu.wav","music");
        }
        background      = ResourceLoader.getInstance().getBufferedImage("resources/images/sky.png");
    }
}
