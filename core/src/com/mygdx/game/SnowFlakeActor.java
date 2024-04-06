package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Random;


public class SnowFlakeActor extends Actor {
    private final Texture texture;
    private Random random;
    private Rectangle hitbox;

    public SnowFlakeActor(Texture texture, float startX, float startY, Random random){
        hitbox = new Rectangle(getX(), getY(), texture.getWidth(), texture.getHeight());
        this.random = random;
        this.texture = texture;
        setPosition(startX, startY);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void act(float delta){
        super.act(delta);
        float nextY = getY() - PlayScreen.SPEED;
        setY(nextY);
        if(getY() < -texture.getHeight()){
            setY(PlayScreen.SCREEN_HEIGHT);
            setX(random.nextInt(PlayScreen.SCREEN_WIDTH - texture.getWidth()));
        }
        hitbox.setPosition(getX(), getY());
    }

    public void reSpawn(){
        setX(random.nextInt(PlayScreen.SCREEN_WIDTH - texture.getWidth()));
        setY(random.nextInt(PlayScreen.SCREEN_HEIGHT)+ PlayScreen.SCREEN_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }

}
