package com.pezcraft.dropical.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DropicalButton {
    private Skin skin;
    private Stage stage;

    public DropicalButton() {
        new DropicalButton(new BitmapFont());
    }
    public DropicalButton(BitmapFont bitmapFont) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Make the stage consume events

        //Create a font
        skin = new Skin();
        skin.add("font", bitmapFont);

        //Create a texture
        Sprite spriteMain = new Sprite(new Texture("GUI/buttons/singleplayer.png"), 52, 61);
        spriteMain.setSize(208, 244);
        skin.add("main", spriteMain);

        Sprite spriteDown = new Sprite(new Texture("GUI/buttons/singleplayer_down.png"), 52, 61);
        spriteDown.setSize(208, 244);
        skin.add("down", spriteDown);

        Sprite spriteUp = new Sprite(new Texture("GUI/buttons/singleplayer_up.png"), 52, 61);
        spriteUp.setSize(208, 244);
        skin.add("up", spriteUp);

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("up");
        textButtonStyle.down = skin.newDrawable("down");
        textButtonStyle.checked = skin.newDrawable("down");
        textButtonStyle.over = skin.newDrawable("down");
        textButtonStyle.font = skin.getFont("font");
        skin.add("default", textButtonStyle);


        TextButton newGameButton = new TextButton("Singleplayer", skin); //use the initialized skin
        newGameButton.setPosition(88 , 12);
        stage.addActor(newGameButton);
    }

    public void act() {
        stage.act();
    }

    public void draw() {
        stage.draw();
    }
}
