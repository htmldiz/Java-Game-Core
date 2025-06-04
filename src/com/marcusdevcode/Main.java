package src.com.marcusdevcode;

import src.com.marcusdevcode.tinysound.TinySound;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Main extends Canvas implements Runnable{
    static  Window window;
    private Thread MainThread;
    private boolean is_runing = false;
    private double width;
    private double height;
    public GraphicsDevice gd;
    int R = 255;
    boolean Rd = false;
    Point mousePosition = new Point();
    int G = 255;
    boolean Gd = false;
    int B = 255;
    boolean Bd = false;
    private int currentStateID;
    private StateLoader currentStateObj;
    private ArrayList<StateLoader> StateStack = new ArrayList<StateLoader>();
    private MouseEventHandlers mouseEventHandler;
    public Main(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd     = ge.getDefaultScreenDevice();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width  = ScreenSize.getWidth();
        height = ScreenSize.getHeight();
        Toolkit.getDefaultToolkit().getScreenResolution();
        window = Window.getInstance();
        window.addGame(this);
        window.createFrame();
        window.startGame();
        this.change_state(GameState.MAIN_MENU);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    change_state(GameState.MAIN_MENU);
                }
            }
        });
        addMouseListener(MouseEventHandlers.getInstance());
    }
    public static void main(String[] args) {
        new Main();
    }
    public void closeGame() {
        TinySound.shutdown();
        System.exit(0);
    }
    public StateLoader getOrLoadState(int stateID) {
        StateLoader st = getState(stateID);
        if(st == null){
            st = new StateLoader(stateID,this);
            StateStack.add(st);
        }
        return st;
    }
    public StateLoader getState(int stateID) {
        StateLoader element_return = null;
        for (int i = 0; i < StateStack.size(); i++) {
            StateLoader element = StateStack.get(i);
            if(element.stateID == stateID){
                element_return = element;
            }
        }
        return element_return;
    }
    public void setCurrentState(int new_state_id) {
        StateLoader new_state_obj = getOrLoadState(new_state_id);
        this.currentStateID  = new_state_id;
        this.currentStateObj = new_state_obj;
        this.currentStateObj.register_events();
    }
    public void change_state(int new_state_id) {
        if(this.currentStateID != new_state_id) {
            if(this.currentStateID != 0){
                this.currentStateObj.dispose_events();
            }
            setCurrentState(new_state_id);
        }
    }
    public void stop() throws InterruptedException {
        MainThread.join();
        is_runing = false;
    }
    private void currentStateRender(Graphics g){
        if(currentStateObj != null) {
            currentStateObj.getState().render((Graphics2D) g);
        }
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,(int)width,(int)height);
        currentStateRender(g);
        g.dispose();
        bs.show();
    }
    private void currentGameStateTick(){
        if(currentStateObj != null) {
            currentStateObj.getState().tick();
        }
    }
    private void tick(){
        currentGameStateTick();
    }
    public void start(){
        MainThread = new Thread(this);
        MainThread.start();
        is_runing = true;
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime ();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(is_runing){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if (is_runing)
                render();
            frames++;
            if(System.currentTimeMillis() - timer >1000){
                timer += 1000;
                frames = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
