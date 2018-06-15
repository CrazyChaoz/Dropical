package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/** A {@link TextButton} styled with textures. It simplifies the useage of buttons.
 *
 * @author Pezcraft */
public class DropicalButton {
    private TextButton button;
    private TextButton.TextButtonStyle style;
    private Skin skin;

    private Sprite spriteUp;
    private Sprite spriteDown;
    private Sprite spriteChecked;
    private Sprite spriteCheckedOver;
    private Sprite spriteOver;
    private Sprite spriteDisabled;

    /** Constructs a new DropicalButton with no text, but nevertheless the basic {@link BitmapFont}, on position 0 with the size 0.
     * The button cannot be seen without a text or texture in it.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
    🠙currently being used
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);
     * }</pre>
     */
    public DropicalButton() {
        this("", new BitmapFont(), 0, 0, 0, 0);
    }

    /** Constructs a new DropicalButton with no text, but nevertheless the basic {@link BitmapFont}, on a certain position with the size 0.
     * The button cannot be seen without a text or texture in it.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
    🠙currently being used
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param x X position of button
     * @param y Y position of button
     */
    public DropicalButton(int x, int y) {
        this("", new BitmapFont(), x, y, 0, 0);
    }

    /** Constructs a new DropicalButton with no text, but nevertheless the basic {@link BitmapFont}, on a certain position with a certain size.
     * The button cannot be seen without a text or texture in it.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
    🠙currently being used
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param x X position of button
     * @param y Y position of button
     * @param buttonWidth width of button
     * @param buttonHeight height of button
     */
    public DropicalButton(int x, int y, int buttonWidth, int buttonHeight) {
        this("", new BitmapFont(), x, y, buttonWidth, buttonHeight);
    }

    /** Constructs a new DropicalButton with a certain text and the basic {@link BitmapFont}, on position 0 with the size 0.
     * But the size of the button will be as big as the size of the text ({@link BitmapFont}).
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
    🠙currently being used
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param text text inside the button
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalButton(String text) {
        this(text, new BitmapFont(), 0, 0, 0, 0);
    }

    /** Constructs a new DropicalButton with a certain text and a {@link BitmapFont}, on position 0 with the size 0.
     * But the size of the button will be as big as the size of the text ({@link BitmapFont}).
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
    🠙currently being used
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param text text inside the button
     * @param font font of the text
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalButton(String text, BitmapFont font) {
        this(text, font, 0, 0, 0, 0);
    }

    /** Constructs a new DropicalButton with a certain text and the basic {@link BitmapFont}, on a certain position with the size 0.
     * But the size of the button will be as big as the size of the text ({@link BitmapFont}).
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
    🠙currently being used
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param text text inside the button
     * @param x X position of button
     * @param y Y position of button
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalButton(String text, int x, int y) {
        this(text, new BitmapFont(), x, y, 0, 0);
    }

    /** Constructs a new DropicalButton with a certain text and the basic {@link BitmapFont}, on a certain position with a certain size.
     *
     *  <h3>Further Constructors</h3>
     *  <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
    🠙currently being used
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param text text inside the button
     * @param x X position of button
     * @param y Y position of button
     * @param buttonWidth width of button
     * @param buttonHeight height of button
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight) {
        this(text, new BitmapFont(), x, y, buttonWidth, buttonHeight);
    }

    /** Constructs a new DropicalButton with a certain text and a {@link BitmapFont}, on a certain position with the size 0.
     * But the size of the button will be as big as the size of the text ({@link BitmapFont}).
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
    🠙currently being used
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);}</pre>
     * @param text text inside the button
     * @param x X position of button
     * @param y Y position of button
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalButton(String text, BitmapFont font, int x, int y) {
        this(text, font, x, y, 0, 0);
    }

    /** Constructs a new DropicalButton with a certain text and a {@link BitmapFont}, on a certain position with a certain size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
DropicalButton();
DropicalButton(int x, int y);
DropicalButton(int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text);
DropicalButton(String text, BitmapFont font);
DropicalButton(String text, int x, int y);
DropicalButton(String text, int x, int y, int buttonWidth, int buttonHeight);
DropicalButton(String text, BitmapFont font, int x, int y);
DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight);
    🠙currently being used}</pre>
     * @param text text inside the button
     * @param x X position of button
     * @param y Y position of button
     * @param buttonWidth width of button
     * @param buttonHeight height of button
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalButton(String text, BitmapFont font, int x, int y, int buttonWidth, int buttonHeight) {
        //create skin for button style
        skin = new Skin();

        //add font to skin
        if(font == null) {
            throw new IllegalArgumentException("The font of the DropicalButton cannot be null!");
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
            throw new IllegalArgumentException("Text inside the DropicalButton cannot be null!");
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


    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setUpTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteUp = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("up", spriteUp);
            style.up = skin.newDrawable("up");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setDownTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteDown = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("down", spriteDown);
            style.down = skin.newDrawable("down");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setCheckedTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteChecked = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("checked", spriteChecked);
            style.checked = skin.newDrawable("checked");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setCheckedOverTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteCheckedOver = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("checkedOver", spriteCheckedOver);
            style.checked = skin.newDrawable("checkedOver");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.#
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setOverTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteOver = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("over", spriteOver);
            style.over = skin.newDrawable("over");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setDisabledTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteDisabled = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("disabled", spriteDisabled);
            style.disabled = skin.newDrawable("disabled");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }

    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if font is null.
     */
    public void setFont(BitmapFont font) {
        if(font != null) {
            skin.add("font", font);
            style.font = skin.getFont("font");
        } else {
            throw new IllegalArgumentException("The font of the DropicalButton cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setFontColor(Color color) {
        if(color != null) {
            skin.add("fontColor", color);
            style.fontColor = skin.getColor("fontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setDownFontColor(Color color) {
        if(color != null) {
            skin.add("downFontColor", color);
            style.downFontColor = skin.getColor("downFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setCheckedFontColor(Color color) {
        if(color != null) {
            skin.add("checkedFontColor", color);
            style.checkedFontColor = skin.getColor("checkedFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setCheckedOverFontColor(Color color) {
        if(color != null) {
            skin.add("checkedOverFontColor", color);
            style.checkedOverFontColor = skin.getColor("checkedOverFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setOverFontColor(Color color) {
        if(color != null) {
            skin.add("overFontColor", color);
            style.overFontColor = skin.getColor("overFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setDisabledFontColor(Color color) {
        if(color != null) {
            skin.add("disabledFontColor", color);
            style.disabledFontColor = skin.getColor("disabledFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }

    /** Aligns the text box ({@link Label}) inside the button. That's not the text itself. Use <code>button.setTextAlignment()</code> instead.
     * <br>For the parameter use...<pre>{@code
Align.  topLeft     top         topRight
        left        center      right
        bottomLeft  bottom      bottomRight}</pre>
     *
     *  @param labelAlign Instead of an integer, you can use {@link Align}.
     */
    public void setLabelAlignment(int labelAlign) {
        button.getLabel().setAlignment(labelAlign, button.getLabel().getLineAlign());
    }
    /** Aligns all lines of the text inside the button.
     * <br>For the parameter use...<pre>{@code
Align.  left  center  right}</pre>
     *
     *  @param textAlign Instead of an integer, you can use {@link Align}.
     */
    public void setTextAlignment(int textAlign) {
        button.getLabel().setAlignment(button.getLabel().getLabelAlign(), textAlign);
    }
    /** @return the alignment of the text box ({@link Label}) inside the button
     */
    public int getLabelAlignment() {
        return button.getLabel().getLabelAlign();
    }
    /** @return the alignment of the lines of the text inside the button.
     */
    public int getTextAlignment() {
        return button.getLabel().getLineAlign();
    }

    /** Sets whether the text is wrapped, if it reaches the end of the text box ({@link Label}) in the button or not.
     */
    public void setWrap(boolean wrap) {
        button.getLabel().setWrap(wrap);

    }

    /** Sets the scale of the font.
     */
    public void setFontScale(float fontScale) {
        button.getLabel().setFontScale(fontScale);
    }
    /** Sets the scale of the font.
     */
    public void setFontScale(float fontScaleX, float fontScaleY) {
        button.getLabel().setFontScale(fontScaleX, fontScaleY);
    }
    /** @return the X scale of the font.
     */
    public float getFontScaleX() {
        return button.getLabel().getFontScaleX();
    }
    /** @return the Y scale of the font.
     */
    public float getFontScaleY() {
        return button.getLabel().getFontScaleY();
    }

    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     */
    public void setCheckedOffsetX(float offsetX) {
        style.checkedOffsetX = offsetX;
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     */
    public void setCheckedOffsetY(float offsetY) {
        style.checkedOffsetY = offsetY;
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     */
    public void setUnpressedOffsetX(float offsetX) {
        style.unpressedOffsetX = offsetX;
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     */
    public void setUnpressedOffsetY(float offsetY) {
        style.unpressedOffsetY = offsetY;
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     */
    public void setPressedOffsetX(float offsetX) {
        style.pressedOffsetX = offsetX;
    }
    /** You have to call <code>yourDropicalButton.updateStyle()</code> afterwards.
     */
    public void setPressedOffsetY(float offsetY) {
        style.pressedOffsetY = offsetY;
    }

    /** You have to to call this, if you...
     * <ul>
     *     <li>changed a button texture</li>
     *     <li>changed an button offset</li>
     *     <li>changed the font</li>
     *     <li>changed a font color</li>
     * </ul>
     */
    public void updateStyle() {
        button.setStyle(style);
    }

    /** @throws IllegalArgumentException if text is null.
     */
    public void setText(String text) {
        if(text != null) {
            button.setText(text);
        } else {
            throw new IllegalArgumentException("Text inside the DropicalButton cannot be null!");
        }
    }
    /** @return the text inside the button.
     */
    public String getText() {
        return button.getText().toString();
    }

    /** Sets the position of the button.
     */
    public void setPosition(float x, float y) {
        button.setPosition(x, y);
    }
    /** @return the X position of the button.
     */
    public float getX() {
        return button.getX();
    }
    /** @return the Y position of the button.
     */
    public float getY() {
        return button.getY();
    }

    /** Sets the size of the button.
     */
    public void setSize(int buttonWidth, int buttonHeight) {
        button.setSize(buttonWidth, buttonHeight);
    }
    /** @return the width of the button.
     */
    public float getWidth() {
        return button.getWidth();
    }
    /** @return the height of the button.
     */
    public float getHeight() {
        return button.getHeight();
    }

    /** Sets whether the button is checked or not. If you press a button, checked will be set automatically.)
     */
    public void setChecked(boolean isChecked) {
        button.setChecked(isChecked);
    }
    /** @return if button is checked or not.
     */
    public boolean isChecked() {
        return button.isChecked();
    }

    /** Disables the button.
     */
    public void setDisabled(boolean isDisabled) {
        button.setDisabled(isDisabled);
    }
    /** @return if button is disabled or not.
     */
    public boolean isDisabled() {
        return button.isDisabled();
    }

    /** Flips the textures on the X axis, but not the text.
     */
    public void flipX() {
        spriteUp.flip(true, false);
        spriteDown.flip(true, false);
        spriteChecked.flip(true, false);
        spriteCheckedOver.flip(true, false);
        spriteOver.flip(true, false);
        spriteDisabled.flip(true, false);
    }
    /** Flips the textures on the Y axis, but not the text.
     */
    public void flipY() {
        spriteUp.flip(false, true);
        spriteDown.flip(false, true);
        spriteChecked.flip(false, true);
        spriteCheckedOver.flip(false, true);
        spriteOver.flip(false, true);
        spriteDisabled.flip(false, true);
    }

    /** Sets the origin when rotating and scaling. The origin does not effect the button position.
     * If you want a precise origin positioning, use <code>setOrigin(int originX, int originY)</code> instead.
     * <br>For the parameter use...<pre>{@code
Align.  topLeft     top         topRight
        left        center      right
        bottomLeft  bottom      bottomRight}</pre>
     *
     *  @param alignment Instead of an integer, you can use {@link Align}
     */
    public void setOrigin(int alignment) {
        button.setTransform(true);
        button.setOrigin(alignment);
    }
    /** Sets the origin position which is relative to button's bottom left corner when rotating and scaling.
     * <br>The origin does not effect the button position.
     *
     * @param originX X position relative to button's bottom left corner.
     * @param originY Y position relative to button's bottom left corner.
     */
    public void setOrigin(float originX, float originY) {
        button.setTransform(true);
        button.setOrigin(originX, originY);
    }
    /** @return the origin X position of the button.
     */
    public float getOriginX() {
        return button.getOriginX();
    }
    /** @return the origin Y position of the button.
     */
    public float getOriginY() {
        return button.getOriginY();
    }

    /** Sets the rotation of the button. If you want to rotate it from the middle, you have to use <code>setOrigin()</code> too.
     */
    public void rotate(float degrees) {
        button.setTransform(true);
        button.setRotation(degrees);
        checkTransform();
    }
    /** Adds the specified rotation to the current rotation. If you want to rotate it from the middle, you have to use <code>setOrigin()</code> too.
     */
    public void rotateBy(float degress) {
        button.setTransform(true);
        button.rotateBy(degress);
        checkTransform();
    }
    /** @return the rotation of the button.
     */
    public float getRotation() {
        return button.getRotation();
    }

    /** Sets the scale of the button. If you want to scale it from the middle, you have to use <code>setOrigin()</code> too.
     */
    public void setScale(float scale) {
        button.setTransform(true);
        button.setScale(scale);
        checkTransform();
    }
    /** Sets the scale of the button. If you want to scale it from the middle, you have to use <code>setOrigin()</code> too.
     */
    public void setScale(float scaleX, float scaleY) {
        button.setTransform(true);
        button.setScale(scaleX, scaleY);
        checkTransform();
    }
    /** Adds the specified scale to the current scale. If you want to scale it from the middle, you have to use <code>setOrigin()</code> too.
     */
    public void scaleBy(float scale) {
        button.setTransform(true);
        button.scaleBy(scale);
        checkTransform();
    }
    /** Adds the specified scale to the current scale. If you want to scale it from the middle, you have to use <code>setOrigin()</code> too.
     */
    public void scaleBy(float scaleX, float scaleY) {
        button.setTransform(true);
        button.scaleBy(scaleX, scaleY);
        checkTransform();
    }
    /** @return the X scale of the button.
     */
    public float getScaleX() {
        return button.getScaleX();
    }
    /** @return the Y scale of the button.
     */
    public float getScaleY() {
        return button.getScaleY();
    }
    private void checkTransform() {
        //for performance reasons, transform will be set to false, if the button ist not rotated or scaled
        if(button.getRotation() == 0 && button.getScaleX() == 1 && button.getScaleY() == 1) {
            button.setTransform(false);
        }
    }

    /** Adds e.g. a {@link InputListener} or a {@link ClickListener} to the button.
     */
    public void addListener(EventListener listener) {
        button.addListener(listener);
    }

    /** You need this, to add your button to a stage.
     */
    public TextButton getButton() {
        return button;
    }
}
