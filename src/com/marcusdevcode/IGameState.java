package src.com.marcusdevcode;

import src.com.marcusdevcode.resources.ObjectResources;

import java.awt.*;

public interface IGameState extends ObjectResources {
    void tick();
    void render(Graphics2D g);
    void dispose_events();
    void register_events();
    void loadResources();
}
