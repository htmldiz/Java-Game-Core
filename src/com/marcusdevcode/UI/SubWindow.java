package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.Helpers;
import src.com.marcusdevcode.IGameState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.resources.ObjectResources;
import src.com.marcusdevcode.resources.ResourceLoader;

import java.awt.*;
import java.util.ArrayList;

public class SubWindow implements ObjectResources, IGameState {
    private String Slocation;
    Main game;
    public Image background;
    public String backgroundPath = "resources/images/Window04.png";
    public String label;
    public Dimension size = new Dimension(0,0);
    public int labelSize = 12;
    public Point location = new Point();
    ArrayList<GMenuButton>items  = new ArrayList<GMenuButton>();
    private Font labelFontText;

    public SubWindow(String label,Main game, int x, int y) {
        this.game = game;
        this.location.x = x;
        this.location.y = y;
    }
    public SubWindow(String label,Main game, String location) {
        this.label = label;
        this.game  = game;
        this.Slocation = location;
    }

    public void Initlocation(String location) {
        if("center".equals(location)) {
            Dimension screenSize = Helpers.getScreenSize();
            this.location.x = (int)screenSize.getWidth()/2 - size.width/2;
            this.location.y = (int)screenSize.getHeight()/2 - size.height/2;
        }
    }
    @Override
    public void tick() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).tick();
        }
    }
    @Override
    public void render(Graphics2D g) {
        g.drawImage(background,this.location.x,this.location.y,size.width,size.height,null);
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
    public void addButton(GMenuButton Item) {
        this.items.add(Item);
        Item.loadResources();
        Point ItemLocation = Item.location.getLocation();
        Item.location = new Point(this.location.x + ItemLocation.x,this.location.y + ItemLocation.y);
    }

    @Override
    public void loadResources() {
        background      = ResourceLoader.getInstance().getBufferedImage(backgroundPath);
        size.width      = background.getWidth(null);
        size.height     = background.getHeight(null);
        Font customFont = ResourceLoader.getInstance().getFont("resources/fonts/cyberpunk.otf",Font.TRUETYPE_FONT);
        this.labelFontText = customFont.deriveFont(Font.PLAIN, labelSize);
        this.Initlocation(Slocation);
    }
}
