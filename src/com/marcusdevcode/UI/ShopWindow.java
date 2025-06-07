package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.GameStates.MainMenuState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.eventListeners.MouseEventCallback;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ShopWindow extends SubWindow {
    public ShopWindow(String title, Main game, String center, MainMenuState mainMenuState) {
        super(title, game, center);
        this.loadResources();
        GMenuButton BackItem = new GMenuButton(game,"Back",new Point(180,320));
        BackItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                mainMenuState.switchSubWindow("MainMenu");
            }
        });
        this.addButton(BackItem);
    }
}
