package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

public class BucketActor extends Actor {
    private Texture texture;
    private Rectangle hitbox;
    private Label labelActor;
    private static final int OTSTUP = 75;
    public BucketActor(Label labelActor){
        texture = new Texture("santashat.png");
        hitbox = new Rectangle(getX(), getY(), texture.getWidth(), texture.getHeight()-OTSTUP);
        this.labelActor = labelActor;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isTouched()) {
            int nextX = Gdx.input.getX() - texture.getWidth()/2;
            setX(nextX);
        }
        hitbox.setPosition(getX(), getY());
        checkOverlap();
    }

    private  void checkOverlap(){
        Stage stage = getStage();
        Array<Actor> actors = stage.getActors();
        for (int i = 0; i < actors.size; ++i){
            Actor actor = actors.get(i);
            if (actor instanceof SnowFlakeActor){
                SnowFlakeActor snowFlake = (SnowFlakeActor) actor;
                boolean isOverlap = hitbox.overlaps(snowFlake.getHitbox());
                if(isOverlap){
                    snowFlake.reSpawn();

                    String points = labelActor.getText().toString();
                    labelActor.setText(Integer.parseInt(points)+1);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }
}
