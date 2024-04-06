package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class PlayScreen extends ScreenAdapter {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int SPEED = 5;
	private Stage stage;
	public static final int snowflake_count = 12;

	@Override
	public void show () {
		stage = new Stage();

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		FileHandle fontFile = Gdx.files.internal("lobster.fnt");

		BitmapFont bitmapfont = new BitmapFont(fontFile);
		labelStyle.font = bitmapfont;
		labelStyle.fontColor = Color.GOLD;

		Label labelActor = new Label("0", labelStyle);
		int padding = 20;
		labelActor.setPosition(20, SCREEN_HEIGHT - bitmapfont.getCapHeight() - padding);
		stage.addActor(labelActor);

		Texture snowTexture = new Texture("snow_2.png");

		Random random = new Random();
		for(int i = 0; i < snowflake_count; ++i){
			int snowX = random.nextInt(SCREEN_WIDTH - snowTexture.getWidth());
			int snowY = random.nextInt(SCREEN_HEIGHT) + SCREEN_HEIGHT;
			SnowFlakeActor snowActor = new SnowFlakeActor(snowTexture, snowX, snowY, random);
			stage.addActor(snowActor);
		}
		BucketActor bucketActor = new BucketActor(labelActor);
		stage.addActor(bucketActor);
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0, 1, 1);
		stage.act();
		stage.draw();
	}
}
