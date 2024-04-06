package com.mygdx.game;

import com.badlogic.gdx.Game;

public class StartGame extends Game {

    @Override
    public void create(){
        setScreen(new PlayScreen());
    }
}
