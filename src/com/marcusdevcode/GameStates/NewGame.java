package src.com.marcusdevcode.GameStates;

import src.com.marcusdevcode.IGameState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.UI.GMenuItem;
import src.com.marcusdevcode.UI.SubWindow;
import src.com.marcusdevcode.resources.ResourceLoader;

import java.awt.*;
import java.util.ArrayList;

public class NewGame extends Canvas implements IGameState {
    private SubWindow subWindow;
    private Main game;
    Image background;
    Point location = new Point();
    private final ArrayList<GMenuItem> menuItems = new ArrayList<GMenuItem>();
    public int width;
    public int height;
    public NewGame(Main Game){
        game = Game;
    }
    @Override
    public void tick() {}

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background,this.location.x,this.location.y,width,height,null);
    }

    @Override
    public void dispose_events() {}

    @Override
    public void register_events() {}

    @Override
    public void loadResources() {
        background = ResourceLoader.getInstance().getBufferedImage("resources/images/sky.png");
        width      = background.getWidth(null);
        height     = background.getHeight(null);
    }
}
