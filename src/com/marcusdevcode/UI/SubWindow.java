package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.Helpers;
import src.com.marcusdevcode.IGameState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.resources.ResourceLoader;

import java.awt.*;
import java.util.ArrayList;

public class SubWindow implements IGameState {
    Main game;
    Image background;
    String label;
    int width;
    int height;
    int labelSize = 12;
    Point location = new Point();
    ArrayList<GMenuItem>items  = new ArrayList<GMenuItem>();
    private Font labelFontText;

    public SubWindow(Main game, int x, int y) {
        this.game = game;
        this.location.x = x;
        this.location.y = y;
    }
    public SubWindow(String label,Main game, String location) {
        this.label = label;
        this.game = game;
        this.loadImage();
        this.loadFont();
        this.Initlocation(location);
    }

    public void Initlocation(String location) {
        if("center".equals(location)) {
            Helpers.getItemLocation(this.location.x,this.location.y);
        }
    }
    public void loadImage() {
        background = ResourceLoader.getInstance().getBufferedImage("resources/images/Window04.png");
        width      = background.getWidth(null);
        height     = background.getHeight(null);
    }
    @Override
    public void tick() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).tick();
        }
    }
    @Override
    public void render(Graphics2D g) {
        g.drawImage(background,this.location.x,this.location.y,width,height,null);
        drawLabel(g, label);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).render(g);
        }
    }

    @Override
    public void dispose_events() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).dispose_events();
        }
    }
    private void loadFont() {
        if(this.labelFontText == null){
            Font customFont = ResourceLoader.getInstance().getFont("resources/fonts/cyberpunk.otf",Font.TRUETYPE_FONT);
            this.labelFontText = customFont.deriveFont(Font.PLAIN, labelSize);
        }
    }
    private void drawLabel(Graphics2D g, String text) {
        g.setFont(labelFontText);
        FontMetrics metrics = g.getFontMetrics();
        int stringWidth     = metrics.stringWidth(text) / 2;
        int stringHeight    = metrics.getHeight()+10;
        g.setColor(Color.GREEN);
        int StateImageWeight = background.getWidth(null)/2;
        g.drawString(text, this.location.x+StateImageWeight-stringWidth, this.location.y + stringHeight);
    }
    @Override
    public void register_events() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).register_events();
        }
    }
    public void addButton(GMenuItem Item) {
        this.items.add(Item);
        Point ItemLocation = Item.location.getLocation();
        Item.location = new Point(this.location.x + ItemLocation.x,this.location.y + ItemLocation.y);
    }
}
