package src.com.marcusdevcode.GameStates;
import src.com.marcusdevcode.GameState;
import src.com.marcusdevcode.SoundHendeler;
import src.com.marcusdevcode.UI.GMenuItem;
import src.com.marcusdevcode.IGameState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.UI.SubWindow;
import src.com.marcusdevcode.eventListeners.MouseEventCallback;
import src.com.marcusdevcode.resources.ResourceLoader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenuState extends Canvas implements IGameState {
    private final ArrayList<GMenuItem> menuItems = new ArrayList<GMenuItem>();
    private final SoundHendeler backgroundSound;
    Main game;
    Image background;
    SubWindow subWindow;
    ArrayList<GMenuItem>items  = new ArrayList<GMenuItem>();
    public MainMenuState(Main Game) {
        game = Game;
        this.backgroundSound = new SoundHendeler("resources/audio/main-menu.wav");
        subWindow = new SubWindow("Main menu",Game, "center");
        GMenuItem PlayItem = new GMenuItem(Game,"Play",new Dimension(365,70),new Point(250,150));
        PlayItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                game.change_state(GameState.IN_GAME);
            }
        });
        GMenuItem ExitItem = new GMenuItem(Game,"Exit",new Dimension(365,70),new Point(250,250));
        ExitItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                Game.closeGame();
            }
        });
        this.menuItems.add(PlayItem);
        this.menuItems.add(ExitItem);
        for (int i = 0; i < this.menuItems.size(); i++) {
            subWindow.addButton(this.menuItems.get(i));
        }
//        subWindow.addButton(new src.com.MarcusDevCode.UI.GMenuItem(Game,"TEst",new Dimension(365,70),new Point(250,150)));
        background = ResourceLoader.getInstance().getBufferedImage("resources/images/sky.png");
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
        }
        subWindow.dispose_events();
    }

    @Override
    public void register_events() {
        if(backgroundSound != null) {
            backgroundSound.play();
        }
        subWindow.register_events();
    }
}
