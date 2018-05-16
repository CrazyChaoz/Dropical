package com.dropical.client.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.screens.Menu;

public class DropicalMain extends Game {
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();

//		setScreen(new Menu(this));
		ScreenManager manager = ScreenManager.getInstance();
		manager.setMenuScreen(new Menu(this));
		manager.showScreen(manager.getMenuScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

}