package src.com.marcusdevcode.eventListeners;

import java.awt.event.MouseEvent;

public interface GameListenerMouseClicked extends GameListener {
    public void mouseClicked(MouseEvent e);
    public boolean inBoundsOfMouse(MouseEvent e);
}
