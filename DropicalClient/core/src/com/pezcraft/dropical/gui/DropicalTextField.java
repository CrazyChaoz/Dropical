package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class DropicalTextField {
    private TextField field;
    private TextField.TextFieldStyle style;
    private Skin skin;
    private int fieldWidth;
    private int fieldHeight;

    private Sprite spriteBackground;
    private Sprite spriteCursor;

    public DropicalTextField(String text, BitmapFont font, String internalTextFieldBackgroundPath, String internalTextFieldCursorPath, int x, int y, int srcWidth, int srcHeight, int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;

        skin = new Skin();
        skin.add("font", font);

        //create textures
        spriteBackground = new Sprite(new Texture(internalTextFieldBackgroundPath), srcWidth, srcHeight);
        spriteBackground.setSize(fieldWidth, fieldHeight);
        skin.add("background", spriteBackground);

        spriteCursor = new Sprite(new Texture(internalTextFieldCursorPath), 1, 14);
        spriteCursor.setSize(3, 56);
        skin.add("cursor", spriteCursor);

        //button style
        style = new TextField.TextFieldStyle();
        style.background = skin.newDrawable("background");
        style.cursor = skin.newDrawable("cursor");
        style.font = skin.getFont("font");
        style.fontColor = new Color(0x4C4C4Cff);
        style.messageFontColor = new Color(0x4C4C4C55);
        skin.add("default", style);

        field = new TextField(text, skin);
        field.setSize(400, 88);
        field.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return Character.isDigit(c) || c == '.';
            }
        });
        field.setAlignment(1);
        field.setMaxLength(15);
        field.setPosition(x, y);

        field.setMessageText("IP");
    }

    public TextField getField() {
        return field;
    }
}
