package src.com.marcusdevcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CursorMouseMotionListener implements MouseMotionListener {

    JFrame yourFrame;
    Cursor customCursor;

    CursorMouseMotionListener(JFrame yourFrame, Cursor customCursor){
        this.yourFrame = yourFrame;
        this.customCursor = customCursor;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Do nothing.
    }
    /// The important bit below
    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getPoint().x >= 50 && e.getPoint().x <=150 && e.getPoint().y >= 50 && e.getPoint().y <=150){
            yourFrame.setCursor(customCursor);
        }
        else{
            yourFrame.setCursor(customCursor);
        }
    }
}