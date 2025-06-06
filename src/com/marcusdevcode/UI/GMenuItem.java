package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.Helpers;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.MouseEventHandlers;
import src.com.marcusdevcode.SoundHendeler;
import src.com.marcusdevcode.eventListeners.*;
import src.com.marcusdevcode.resources.ObjectResources;
import src.com.marcusdevcode.resources.ResourceLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GMenuItem implements GameListenerMouseClicked, ObjectResources, GameListenerMouseEntered, GameListenerMouseExited, GameObjectListenerMouseMoved {
    String name = "src.com.MarcusDevCode.UI.GMenuItem";
    private Main Game;
    String label = "";
    int labelSize = 18;
    String eventsId;
    Font labelFontText;
    SoundHendeler hoverSound;
    SoundHendeler clickSound;
    boolean hoverSoundPlayed = false;
    Dimension size = new Dimension(0,0);
    Point location = new Point(0,0);
    HashMap<Integer, BufferedImage> Images = new HashMap<Integer, BufferedImage>();
    public static final int DEFAULT = 0;
    public static final int HOVERED = 1;
    public int current_state = DEFAULT;
    private MouseEventCallback mouseClickedHendeler;
    public GMenuItem(Main Game,String label, Dimension size, Point location) {
        this.Game     = Game;
        this.label    = label;
        this.size     = size;
        this.name     = label;
        this.location = location;
    }

    public GMenuItem(Main game, String label, Point location) {
        this.Game     = game;
        this.label    = label;
        this.name     = label;
        this.location = location;
    }

    public boolean inBounds(){
        Point mousePosition = Helpers.getMousePosition();
        Image Image = getCurrentStateImage();
        if(
                mousePosition.x >= this.location.x &&
                mousePosition.x <= this.location.x + Image.getWidth(null) &&
                mousePosition.y >= this.location.y &&
                mousePosition.y <= this.location.y + Image.getHeight(null)
        ){
            return true;
        }
        return false;
    }
    public void tick() {
        if(inBounds()){
            if(this.hoverSoundPlayed == false) {
                if(this.hoverSound != null) {
                    this.hoverSound.play(false);
                    this.hoverSoundPlayed = true;
                }
            }
            this.current_state = GMenuItem.HOVERED;
        }else{
            if(this.hoverSoundPlayed == true) {
                this.hoverSoundPlayed = false;
                this.hoverSound.stop();
            }
            this.current_state = GMenuItem.DEFAULT;
        }
    }
    public Image getCurrentStateImage() {
         return this.Images.get(this.current_state);
    }
    public void render(Graphics2D g) {
        Image image = this.Images.get(this.current_state);
        g.drawImage(image,this.location.x,this.location.y,null);
        g.setFont(new Font("Serif", Font.BOLD, 30));
        drawLabel(g,label);
    }

    private void drawLabel(Graphics2D g, String text) {
        g.setFont(labelFontText);
        FontMetrics metrics = g.getFontMetrics();
        int stringWidth     = metrics.stringWidth(text) / 2;
        int stringHeight    = metrics.getHeight() / 3;
        g.setColor(Color.GREEN);
        int StateImageHeight = getCurrentStateImage().getHeight(null)/2;
        int StateImageWeight = getCurrentStateImage().getWidth(null)/2;
        g.drawString(text, this.location.x+StateImageWeight-stringWidth, this.location.y + stringHeight+StateImageHeight);
    }

    public void dispose_events() {
        if(eventsId != null){
            MouseEventHandlers.getInstance().removeGameObject(eventsId);
            eventsId = null;
        }
    }

    public void register_events() {
        eventsId = MouseEventHandlers.getInstance().addGameObject(this);
    }

    @Override
    public boolean inBoundsOfMouse(MouseEvent e) {
        return inBounds();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickSound.play(false);
        if(mouseClickedHendeler != null){
            mouseClickedHendeler.onEvent(e);
        }
    }

    public void onClick(MouseEventCallback mouseEventCallback) {
        mouseClickedHendeler = mouseEventCallback;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Exited");
    }

    @Override
    public void loadResources() {
        Font customFont    = ResourceLoader.getInstance().getFont("resources/fonts/cyberpunk.otf",Font.TRUETYPE_FONT);
        this.labelFontText = customFont.deriveFont(Font.PLAIN, labelSize);
        this.hoverSound    = new SoundHendeler("resources/audio/hovering.wav","sound");
        this.clickSound    = new SoundHendeler("resources/audio/click.wav","sound");
        BufferedImage DEFAULTImage = ResourceLoader.getInstance().getBufferedImage("resources/images/Btn_V16.png");
        BufferedImage HOVEREDImage = ResourceLoader.getInstance().getBufferedImage("resources/images/Btn_V15.png");
        this.location.x = this.location.x-DEFAULTImage.getWidth()/2;
        if(this.size == null){
            this.size = new Dimension(DEFAULTImage.getWidth(),DEFAULTImage.getHeight());
        }
        Images.put(DEFAULT,DEFAULTImage);
        Images.put(HOVERED,HOVEREDImage);
    }
}
