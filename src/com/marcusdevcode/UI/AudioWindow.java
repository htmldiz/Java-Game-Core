package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.GameStates.MainMenuState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.eventListeners.MouseEventCallback;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AudioWindow extends SubWindow {
    public AudioWindow(String subWindowId, Main game, MainMenuState mainMenuState) {
        super(subWindowId, game, "center");
        this.loadResources();
        GMenuButton BackItem = new GMenuButton(game,"Back",new Point(this.size.width/2,320));
        BackItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                mainMenuState.switchSubWindow("Options");
            }
        });
        GMenuButton volumeItem = new GMenuSlider(game,"Audio volume",new Point(this.size.width/2,120),-1.0,1.0,0.1);
        this.addButton(volumeItem);
        this.addButton(BackItem);
    }
}
