package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DropicalButton {
    private TextButton button;
    private TextButton.TextButtonStyle style;
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
        skin.add("up", spriteUp);

        spriteDown = new Sprite(new Texture(internalButtonDownPath), srcWidth, srcHeight);
        skin.add("down", spriteDown);

        spriteChecked = new Sprite(new Texture(internalButtonCheckedPath), srcWidth, srcHeight);
        skin.add("checked", spriteChecked);

        spriteOver = new Sprite(new Texture(internalButtonOverPath), srcWidth, srcHeight);
        skin.add("over", spriteOver);

        spriteDisabled = new Sprite(new Texture(internalButtonDisabledPath), srcWidth, srcHeight);
        skin.add("disabled", spriteDisabled);

        skin.add("color", new Color(0x2B2B2Bff));

        //button style
        style = new TextButton.TextButtonStyle();
        style.up = skin.newDrawable("up");
        style.down = skin.newDrawable("down");
        style.checked = skin.newDrawable("checked");
        style.over = skin.newDrawable("over");
        style.disabled = skin.newDrawable("disabled");
        style.font = skin.getFont("font");
        style.fontColor = skin.getColor("color");
        skin.add("default", style);

        button = new TextButton(text, skin);
        button.setPosition(x, y);
        setButtonSize(buttonWidth, buttonHeight);
    }

    public void setUpTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteUp.setTexture(new Texture(internalPath));
        spriteUp.setRegionWidth(srcWidth);
        spriteUp.setRegionHeight(srcHeight);
        skin.add("up", spriteUp);
        style.up = skin.newDrawable("up");

        button.setStyle(style);
    }
    public void setDownTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteDown.setTexture(new Texture(internalPath));
        spriteDown.setRegionWidth(srcWidth);
        spriteDown.setRegionHeight(srcHeight);
        skin.add("down", spriteDown);
        style.down = skin.newDrawable("down");

        button.setStyle(style);
    }
    public void setCheckedTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteChecked.setTexture(new Texture(internalPath));
        spriteChecked.setRegionWidth(srcWidth);
        spriteChecked.setRegionHeight(srcHeight);
        skin.add("checked", spriteChecked);
        style.checked = skin.newDrawable("checked");

        button.setStyle(style);
    }
    public void setOverTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteOver.setTexture(new Texture(internalPath));
        spriteOver.setRegionWidth(srcWidth);
        spriteOver.setRegionHeight(srcHeight);
        skin.add("over", spriteOver);
        style.over = skin.newDrawable("over");

        button.setStyle(style);
    }
    public void setDisabledTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteDisabled.setTexture(new Texture(internalPath));
        spriteDisabled.setRegionWidth(srcWidth);
        spriteDisabled.setRegionHeight(srcHeight);
        skin.add("disabled", spriteDisabled);
        style.disabled = skin.newDrawable("disabled");

        button.setStyle(style);
    }

    public void setFont(BitmapFont font) {
        skin.add("font", font);
        style.font = skin.getFont("font");

        button.setStyle(style);
    }

    public void setText(String text) {
        button.setText(text);
    }

    public String getText() {
        return button.getText().toString();
    }

    public void setPosition(float x, float y) {
        button.setPosition(x, y);
    }

    public void setButtonSize(int buttonWidth, int buttonHeight) {
        button.setSize(buttonWidth, buttonHeight);
    }

    public void setSrcSize(int srcWidth, int srcHeight) {
        spriteUp.setRegionWidth(srcWidth);
        spriteUp.setRegionHeight(srcHeight);
        skin.add("up", spriteUp);
        style.up = skin.newDrawable("up");

        spriteDown.setRegionWidth(srcWidth);
        spriteDown.setRegionHeight(srcHeight);
        skin.add("up", spriteDown);
        style.up = skin.newDrawable("up");

        spriteChecked.setRegionWidth(srcWidth);
        spriteChecked.setRegionHeight(srcHeight);
        skin.add("up", spriteChecked);
        style.up = skin.newDrawable("up");

        spriteOver.setRegionWidth(srcWidth);
        spriteOver.setRegionHeight(srcHeight);
        skin.add("up", spriteOver);
        style.up = skin.newDrawable("up");

        spriteDisabled.setRegionWidth(srcWidth);
        spriteDisabled.setRegionHeight(srcHeight);
        skin.add("up", spriteDisabled);
        style.up = skin.newDrawable("up");

        button.setStyle(style);
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
