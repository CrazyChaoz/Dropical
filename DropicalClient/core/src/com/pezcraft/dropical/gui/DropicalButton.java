package com.pezcraft.dropical.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DropicalButton {
    private Stage stage;
    private TextButton button;
    private Skin skin;

    public DropicalButton() {
        this("", new BitmapFont(), "", "", "", "", "", 0, 0);
    }
    public DropicalButton(String text, BitmapFont font, String internalButtonUpPath, String internalButtonDownPath,
                          String internalButtonCheckedPath, String internalButtonOverPath, String internalButtonDisabledPath, int x, int y) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); //make the stage consume events

        skin = new Skin();
        skin.add("font", font);

        //create textures
        Sprite spriteUp = new Sprite(new Texture(internalButtonUpPath), 52, 61);
        spriteUp.setSize(208, 244);
        skin.add("up", spriteUp);

        Sprite spriteDown = new Sprite(new Texture(internalButtonDownPath), 52, 61);
        spriteDown.setSize(208, 244);
        skin.add("down", spriteDown);

        Sprite spriteChecked = new Sprite(new Texture(internalButtonCheckedPath), 52, 61);
        spriteChecked.setSize(208, 244);
        skin.add("checked", spriteChecked);

        Sprite spriteOver = new Sprite(new Texture(internalButtonOverPath), 52, 61);
        spriteOver.setSize(208, 244);
        skin.add("over", spriteOver);

        Sprite spriteDisabled = new Sprite(new Texture(internalButtonDisabledPath), 52, 61);
        spriteDisabled.setSize(208, 244);
        skin.add("disabled", spriteDisabled);

        //button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("up");
        textButtonStyle.down = skin.newDrawable("down");
        textButtonStyle.checked = skin.newDrawable("checked");
        textButtonStyle.over = skin.newDrawable("over");
        textButtonStyle.disabled = skin.newDrawable("disabled");
        textButtonStyle.font = skin.getFont("font");
        skin.add("default", textButtonStyle);

        button = new TextButton(text, skin);
        button.setPosition(x, y);
        stage.addActor(button);
    }

    public void act() {
        stage.act();
    }

    public void draw() {
        stage.draw();
    }
}
