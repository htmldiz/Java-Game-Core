package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.GameState;
import src.com.marcusdevcode.GameStates.MainMenuState;
import src.com.marcusdevcode.Main;
import src.com.marcusdevcode.eventListeners.MouseEventCallback;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenuWindow extends SubWindow {
    public MainMenuWindow(String title, Main game, String center, MainMenuState mainMenuState) {
        super(title, game, center);
        this.loadResources();
        int fbnt = 100;
        GMenuButton PlayButton    = new GMenuButton(game,"Play",new Point(size.width/2,fbnt));
        GMenuButton OptionsButton = new GMenuButton(game,"Options",new Point(size.width/2,fbnt+70));
        GMenuButton ExitButton    = new GMenuButton(game,"Exit",new Point(size.width/2,fbnt+70+fbnt));
        ArrayList<GMenuButton> menuItems = new ArrayList<GMenuButton>();
        menuItems.add(PlayButton);
        menuItems.add(OptionsButton);
        menuItems.add(ExitButton);
        PlayButton.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                game.change_state(GameState.IN_GAME);
            }
        });
        ExitButton.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                game.closeGame();
            }
        });
        OptionsButton.onClick(new MouseEventCallback(){
            @Override
            public void onEvent(MouseEvent e) {
                String sub_window_id = "Options";
                if(mainMenuState.items.get(sub_window_id) == null){
                    SubWindow subWindow = new OptionsWindow(sub_window_id,game, mainMenuState);
                    mainMenuState.items.put(sub_window_id,subWindow);
                }
                mainMenuState.switchSubWindow(sub_window_id);
            }
        });
        for (int i = 0; i < menuItems.size(); i++) {
            GMenuButton gMenuButton = menuItems.get(i);
            addButton(gMenuButton);
        }
    }
}
