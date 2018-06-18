package com.dropical.client.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.screens.Menu;

public class DropicalMain extends Game {
	private SpriteBatch batch;
	private Music music;

	@Override
	public void create() {
		initSettings();

		batch = new SpriteBatch();

		//Menü anzeigen
		ScreenManager manager = ScreenManager.getInstance();
		manager.setMain(this);
		manager.setMenuScreen(new Menu(this), this);
		manager.showScreen(manager.getMenuScreen());

		music = Gdx.audio.newMusic(Gdx.files.internal("Tetris.ogg"));
		music.play();
		music.setLooping(true);
	}

	@Override
	public void render() {
		super.render();
	}

	private void initSettings() {
		Preferences settings = Gdx.app.getPreferences("settings");

		boolean needChange = false;
		if(!settings.contains("ghost")) {
			settings.putBoolean("ghost", true);
			needChange = true;
		}

		if(needChange) {
			settings.flush();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

}