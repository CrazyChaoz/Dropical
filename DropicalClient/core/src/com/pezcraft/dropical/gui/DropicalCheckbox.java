package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DropicalCheckbox {
    private CheckBox box;
    private CheckBox.CheckBoxStyle style;
    private Skin skin;

    public DropicalCheckbox(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight) {
        //create skin for button style
        skin = new Skin();

        //add font to skin
        if(font == null) {
            throw new IllegalArgumentException("The font of the DropicalCheckbox cannot be null!");
        }
        skin.add("font", font);
        skin.add("fontColor", new Color(0x000000ff));   //basic font color

        //create button style
        style = new CheckBox.CheckBoxStyle();
        style.font = skin.getFont("font");
        style.fontColor = skin.getColor("fontColor");

        skin.add("checkboxOff", new Sprite(new Texture("GUI/checkBox/checkBoxOff_up.png"), 20, 20));
        style.checkboxOff = skin.newDrawable("checkboxOff");
        skin.add("checkboxOn", new Sprite(new Texture("GUI/checkBox/checkBoxOn_up.png"), 20, 20));
        style.checkboxOn = skin.newDrawable("checkboxOn");
        skin.add("checkboxOver", new Sprite(new Texture("GUI/checkBox/checkBoxOff_down.png"), 20, 20));
        style.checkboxOver = skin.newDrawable("checkboxOver");


        skin.add("default", style);

        //create button
        if(text != null) {
            box = new CheckBox(text, skin, "default");
        } else {
            throw new IllegalArgumentException("Text inside the DropicalButton cannot be null!");
        }

        //set button position
        box.setPosition(x, y);

        //set size of button
        box.setSize(buttonWidth, buttonHeight);

//        box.setTransform(true);
//        box.setScale(4);

        //set sprites
//        spriteUp = new Sprite();
//        spriteDown = new Sprite();
//        spriteChecked = new Sprite();
//        spriteCheckedOver = new Sprite();
//        spriteOver = new Sprite();
//        spriteDisabled = new Sprite();
    }

    /** You need this, to add your check box to a stage.
     */
    public CheckBox getBox() {
        return box;
    }
}
