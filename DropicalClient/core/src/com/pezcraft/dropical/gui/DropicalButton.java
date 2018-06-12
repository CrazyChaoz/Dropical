package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/** A {@link TextButton} styled with pictures. It simplifies the use of buttons.
 *
 * @author Pezcraft */
public class DropicalButton {
    private TextButton button;
    private TextButton.TextButtonStyle style;
    private Skin skin;
    private int buttonWidth;
    private int buttonHeight;

    private Sprite spriteUp;
    private Sprite spriteDown;
    private Sprite spriteChecked;
    private Sprite spriteCheckedOver;
    private Sprite spriteOver;
    private Sprite spriteDisabled;

    public DropicalButton() {
        this("", new BitmapFont(), 0, 0, 0, 0);
    }
    public DropicalButton(int x, int y) {
        this("", new BitmapFont(), x, y, 0, 0);
    }
    public DropicalButton(int x, int y, int buttonWidth, int buttonHeight) {
        this("", new BitmapFont(), x, y, buttonWidth, buttonHeight);
    }
    public DropicalButton(String text) {
        this(text, new BitmapFont(), 0, 0, 0, 0);
    }
    public DropicalButton(String text, BitmapFont font) {
        this(text, font, 0, 0, 0, 0);
    }
    public DropicalButton(String text, int x, int y) {
        this(text, new BitmapFont(), x, y, 0, 0);
    }
    public DropicalButton(String text, BitmapFont font, int x, int y) {
        this(text, font, x, y, 0, 0);
    }
    public DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight) {
//        this.buttonWidth = buttonWidth;   //not needed in new DropicalButton
//        this.buttonHeight = buttonHeight;

        //create skin for button style
        skin = new Skin();

        //add font to skin
        if(font == null) {
            throw new NullPointerException("The font of the DropicalButton cannot be null!");
        }
        skin.add("font", font);
        skin.add("fontColor", new Color(0x000000ff));   //basic font color

        //create button style
        style = new TextButton.TextButtonStyle();
        style.font = skin.getFont("font");
        style.fontColor = skin.getColor("fontColor");
        skin.add("default", style);

        //create button
        if(text != null) {
            button = new TextButton(text, skin, "default");
        } else {
            throw new NullPointerException("Text inside the DropicalButton cannot be null!");
        }

        //set button position
        button.setPosition(x, y);

        //set size of button
        button.setSize(buttonWidth, buttonHeight);

        //set sprites
        spriteUp = new Sprite();
        spriteDown = new Sprite();
        spriteChecked = new Sprite();
        spriteCheckedOver = new Sprite();
        spriteOver = new Sprite();
        spriteDisabled = new Sprite();
    }


//    public DropicalButton() {
//        this("", new BitmapFont(), "", "", "", "", "", 0, 0, 0, 0, 0, 0);
//    }
    public DropicalButton(String text, BitmapFont font, String internalButtonUpPath, String internalButtonDownPath,
                          String internalButtonCheckedPath, String internalButtonOverPath, String internalButtonDisabledPath, int x, int y, int srcWidth, int srcHeight, int buttonWidth, int buttonHeight) {
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;

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
    }

    public void setUpTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteUp = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
//        spriteUp.setSize(buttonWidth, buttonHeight);
        skin.add("up", spriteUp);
        style.up = skin.newDrawable("up");
    }
    public void setDownTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteDown = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
//        spriteDown.setSize(buttonWidth, buttonHeight);
        skin.add("down", spriteDown);
        style.down = skin.newDrawable("down");
    }
    public void setCheckedTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteChecked = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
//        spriteChecked.setSize(buttonWidth, buttonHeight);
        skin.add("checked", spriteChecked);
        style.checked = skin.newDrawable("checked");
    }
    public void setCheckedOverTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteCheckedOver = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
//        spriteCheckedOver.setSize(buttonWidth, buttonHeight);
        skin.add("checkedOver", spriteCheckedOver);
        style.checked = skin.newDrawable("checkedOver");
    }
    public void setOverTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteOver = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
//        spriteOver.setSize(buttonWidth, buttonHeight);
        skin.add("over", spriteOver);
        style.over = skin.newDrawable("over");
    }
    public void setDisabledTexture(String internalPath, int srcWidth, int srcHeight) {
        spriteDisabled = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
//        spriteDisabled.setSize(buttonWidth, buttonHeight);
        skin.add("disabled", spriteDisabled);
        style.disabled = skin.newDrawable("disabled");
    }
    public void setFont(BitmapFont font) {
        skin.add("font", font);
        style.font = skin.getFont("font");
    }

    public void updateStyle() {
//        skin.add("default", style);
//        button.setSkin(skin);
//        button.setStyle(style);

//        skin.add("default", style);
//        button.setSkin(skin);
//        button = new TextButton("df", skin);
//        button.setStyle(skin.get("lol", TextButton.TextButtonStyle.class));

//        button.setStyle(skin.get("default", TextButton.TextButtonStyle.class));
        button.setStyle(style);
    }

    public void setText(String text) {
        if(text != null) {
            button.setText(text);
        } else {
            throw new NullPointerException("Text inside the DropicalButton cannot be null!");
        }
    }

    public String getText() {
        return button.getText().toString();
    }

    public void setPosition(float x, float y) {
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

    public void setSize(int buttonWidth, int buttonHeight) {
        button.setSize(buttonWidth, buttonHeight);
    }

    public TextButton getButton() {
        return button;
    }

}
