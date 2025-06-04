package src.com.marcusdevcode;

import src.com.marcusdevcode.GameStates.MainMenuState;
import src.com.marcusdevcode.GameStates.NewGame;

public class StateLoader {
    public int stateID;
    public IGameState state;
    StateLoader(int StateID, Main game) {
        setStateID(StateID);
        if(getStateID() == GameState.MAIN_MENU){
            state = new MainMenuState(game);
            state.loadResources();
        }
        if(getStateID() == GameState.IN_GAME){
            state = new NewGame(game);
            state.loadResources();
        }
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public IGameState getState() {
        return state;
    }

    public void setState(IGameState state) {
        this.state = state;
    }

    public void dispose_events() {
        if(state != null){
            state.dispose_events();
        }
    }

    public void register_events() {
        if(state != null){
            state.register_events();
        }
    }
}
