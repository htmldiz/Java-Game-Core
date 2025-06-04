package src.com.marcusdevcode;

import java.awt.*;

public class GraphicsDeviceManager {
    public static GraphicsDeviceManager instance;
    public GraphicsEnvironment ge;
    public GraphicsDevice gd;
    public DisplayMode dm;
    public GraphicsConfiguration cnf;
    public GraphicsDevice getScreen(){
        if(gd == null) {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gd = ge.getDefaultScreenDevice();
        }
        return gd;
    }
    public DisplayMode getDisplayMode(){
        if(dm == null) {
            dm = getScreen().getDisplayMode();
        }
        return dm;
    }
    public GraphicsConfiguration getConfiguration(){
        if(cnf == null) {
            gd = getScreen();
            cnf = gd.getDefaultConfiguration();
        }
        return cnf;
    }
    private GraphicsDeviceManager(){

    }
    public static GraphicsDeviceManager getInstance(){
        if(instance == null){
            instance = new GraphicsDeviceManager();
        }
        return instance;
    }
}
