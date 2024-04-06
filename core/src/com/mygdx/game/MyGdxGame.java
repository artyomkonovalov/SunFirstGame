package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	SpriteBatch batch;
	Texture img;
	private float x = 0;
	private Texture snow;
	private int snowY = SCREEN_HEIGHT;
	private int snowX = 0;
	private int speed = 3;
	private Random random;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		snow = new Texture("snow_2.png");

		random = new Random();
		snowX = random.nextInt(SCREEN_WIDTH - snow.getWidth());
	}

	@Override
	public void render () {
		snowY = snowY - speed;
		if(Gdx.input.justTouched()) {
			x = Gdx.input.getX() - img.getWidth()/2;
		}
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.draw(snow, snowX, snowY);
		batch.draw(img, x, 0);
		batch.end();
		if(snowY < -snow.getHeight()){
			snowY = SCREEN_HEIGHT;
			snowX = random.nextInt(SCREEN_WIDTH - snow.getWidth());
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
