package src.com.marcusdevcode;

import java.awt.*;

public interface IGameState {
    void tick();
    void render(Graphics2D g);
    void dispose_events();
    void register_events();
}
