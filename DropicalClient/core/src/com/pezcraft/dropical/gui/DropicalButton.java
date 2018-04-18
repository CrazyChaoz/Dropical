package com.pezcraft.dropical.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DropicalButton extends Button {
    private TextButton button;
    private Skin skin;
    private Sprite spriteUp;
    private Sprite spriteDown;
    private Sprite spriteChecked;
    private Sprite spriteOver;
    private Sprite spriteDisabled;

    public DropicalButton() {
        this("", new BitmapFont(), "", "", "", "", "", 0, 0, 0, 0, 0, 0);
    }
    public DropicalButton(String text, BitmapFont font, String internalButtonUpPath, String internalButtonDownPath,
                          String internalButtonCheckedPath, String internalButtonOverPath, String internalButtonDisabledPath, int x, int y, int srcWidth, int srcHeight, int buttonWidth, int buttonHeight) {
        skin = new Skin();
        skin.add("font", font);

        //create textures
        spriteUp = new Sprite(new Texture(internalButtonUpPath), srcWidth, srcHeight);
        spriteUp.setSize(buttonWidth, buttonHeight);
        skin.add("up", spriteUp);

        spriteDown = new Sprite(new Texture(internalButtonDownPath), srcWidth, srcHeight);
        spriteDown.setSize(buttonWidth, buttonHeight);
        skin.add("down", spriteDown);

        spriteChecked = new Sprite(new Texture(internalButtonCheckedPath), srcWidth, srcHeight);
        spriteChecked.setSize(buttonWidth, buttonHeight);
        skin.add("checked", spriteChecked);

        spriteOver = new Sprite(new Texture(internalButtonOverPath), srcWidth, srcHeight);
        spriteOver.setSize(buttonWidth, buttonHeight);
        skin.add("over", spriteOver);

        spriteDisabled = new Sprite(new Texture(internalButtonDisabledPath), srcWidth, srcHeight);
        spriteDisabled.setSize(buttonWidth, buttonHeight);
        skin.add("disabled", spriteDisabled);

        skin.add("color", new Color(0x2B2B2Bff));

        //button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("up");
        textButtonStyle.down = skin.newDrawable("down");
        textButtonStyle.checked = skin.newDrawable("checked");
        textButtonStyle.over = skin.newDrawable("over");
        textButtonStyle.disabled = skin.newDrawable("disabled");
        textButtonStyle.font = skin.getFont("font");
        textButtonStyle.fontColor = skin.getColor("color");
        skin.add("default", textButtonStyle);

        button = new TextButton(text, skin);
        button.setPosition(x, y);
    }

    public void flipX() {
        spriteUp.flip(true, false);
        spriteDown.flip(true, false);
        spriteChecked.flip(true, false);
        spriteOver.flip(true, false);
        spriteDisabled.flip(true, false);
    }

    public void flipY() {
        spriteUp.flip(false, true);
        spriteDown.flip(false, true);
        spriteChecked.flip(false, true);
        spriteOver.flip(false, true);
        spriteDisabled.flip(false, true);
    }

    public TextButton getButton() {
        return button;
    }



}
