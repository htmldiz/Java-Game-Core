package src.com.marcusdevcode;

import java.awt.*;

public class Helpers {
    public static Point getMousePosition(){
        Point globalMousePosition = MouseInfo.getPointerInfo().getLocation();
        return Helpers.getItemLocation((int)globalMousePosition.getX(), (int)globalMousePosition.getY());
    }
    public static Point getItemLocation(int x, int y){
        Point frameLocation = Window.getInstance().getLocationOnScreen();
        Point returnPoint = new Point();
        returnPoint.x = (int) (x - frameLocation.getX());
        returnPoint.y = (int) (y - frameLocation.getY());
        return returnPoint;
    }
}
