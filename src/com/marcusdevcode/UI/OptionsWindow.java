package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.GameStates.MainMenuState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.eventListeners.MouseEventCallback;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OptionsWindow extends SubWindow {
    public OptionsWindow(String title, Main game, MainMenuState mainMenuState) {
        super(title, game, "center");
        backgroundPath = "resources/images/Window01.png";
        this.loadResources();
        GMenuButton BackItem = new GMenuButton(game,"Back",new Point(this.size.width/2,320));
        BackItem.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                mainMenuState.switchSubWindow("MainMenu");
            }
        });
        this.addButton(BackItem);
        GMenuButton BackItem1 = new GMenuButton(game,"Audio Settings",new Point(this.size.width/2,120));
        BackItem1.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                String sub_window_id = "Audio Settings";
                if(mainMenuState.items.get(sub_window_id) == null){
                    SubWindow subWindow = new AudioWindow("Audio Settings",game, mainMenuState);
                    mainMenuState.items.put(sub_window_id,subWindow);
                }
                mainMenuState.switchSubWindow(sub_window_id);
            }
        });
        this.addButton(BackItem1);
    }
}
