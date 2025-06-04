package src.com.marcusdevcode;

import src.com.marcusdevcode.eventListeners.GameListener;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class MouseEventHandlers extends MouseAdapter implements MouseListener, MouseWheelListener, MouseMotionListener {
    HashMap<String, GameListener> GameObjects = new HashMap<String, GameListener>();
    static MouseEventHandlers instance;
    private MouseEventHandlers(){}
    public static MouseEventHandlers getInstance() {
        if(instance == null){
            instance = new MouseEventHandlers();
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        useEventCaller(e,"mouseClicked");
    }
    public void useEventCaller(MouseEvent e,String eventName) {
        Set<String> keys = GameObjects.keySet();
        if (!keys.isEmpty()) {
            for (String key : keys) {
                GameListener GameObject = GameObjects.get(key);
                if(GameObject != null){
                    boolean inBoundsOfMouse = GameObject.inBoundsOfMouse(e);
                    if (inBoundsOfMouse) {
                        try {
                            Method eventMethod = GameObject.getClass().getMethod(eventName, e.getClass());
                            eventMethod.invoke(GameObject, e);
                        } catch (InvocationTargetException | IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        } catch (NoSuchMethodException ex) {
    //                        throw new RuntimeException(ex);
                        }
                    }
                }
            }
        }
    }
    public String addGameObject(GameListener gameListener) {
        String integer = UUID.randomUUID().toString();
        GameObjects.put(integer, gameListener);
        return integer;
    }
    public void removeGameObject(String integer) {
        GameObjects.remove(integer);
    }
    public HashMap<String, GameListener> getGameObjects() {
        return GameObjects;
    }
    public void setGameObjects(HashMap<String, GameListener> gameObjects) {
        GameObjects = gameObjects;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        useEventCaller(e,"mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        useEventCaller(e,"mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        useEventCaller(e,"mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        useEventCaller(e,"mouseExited");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        useEventCaller(e,"mouseWheelMoved");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        useEventCaller(e,"mouseDragged");
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        useEventCaller(e,"mouseMoved");
    }
}