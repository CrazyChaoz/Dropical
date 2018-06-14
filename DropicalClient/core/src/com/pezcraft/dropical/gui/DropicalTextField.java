package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class DropicalTextField {
    private TextField field;
    private TextField.TextFieldStyle style;
    private Skin skin;

    private Sprite spriteBackground;
    private Sprite spriteCursor;
    private Sprite spriteDisabled;
    private Sprite spriteFocused;
    private Sprite spriteSelection;

    public DropicalTextField(String text, BitmapFont font, String internalTextFieldBackgroundPath, String internalTextFieldCursorPath, int x, int y, int srcWidth, int srcHeight, int fieldWidth, int fieldHeight) {

        skin = new Skin();
        skin.add("font", font);

        //create textures
        spriteBackground = new Sprite(new Texture(internalTextFieldBackgroundPath), srcWidth, srcHeight);
        spriteBackground.setSize(fieldWidth, fieldHeight);
        skin.add("background", spriteBackground);

        spriteCursor = new Sprite(new Texture(internalTextFieldCursorPath), 1, 14);
        spriteCursor.setSize(3, 56);
        skin.add("cursor", spriteCursor);

        //textfield style
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

    /** Constructs a new DropicalTextField with no placeholder-text and text, but nevertheless the basic {@link BitmapFont}, on position 0 with the size 0.
     * The textfield cannot be seen without a texture in it or a size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
        🠙currently being used
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);
     * }</pre>
     */
    public DropicalTextField() {
        this("", new BitmapFont(), 0, 0, 0, 0);
    }

    /** Constructs a new DropicalTextField with no placeholder-text and text, but nevertheless the basic {@link BitmapFont}, on a certain position with the size 0.
     * The textfield cannot be seen without a texture in it or a size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
        🠙currently being used
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param x X position of textfield
     * @param y Y position of textfield
     */
    public DropicalTextField(int x, int y) {
        this("", new BitmapFont(), x, y, 0, 0);
    }

    /** Constructs a new DropicalTextField no placeholder-text and text, but nevertheless the basic {@link BitmapFont}, on a certain position with a certain size.
     * The textfield cannot be seen without a text or texture in it.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
        🠙currently being used
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param x X position of textfield
     * @param y Y position of textfield
     * @param fieldWidth width of textfield
     * @param fieldHeight height of textfield
     */
    public DropicalTextField(int x, int y, int fieldWidth, int fieldHeight) {
        this("", new BitmapFont(), x, y, fieldWidth, fieldHeight);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and the basic {@link BitmapFont}, on position 0 with the size 0.
     * The textfield cannot be seen without a texture in it or a size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
        🠙currently being used
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param placeholderText the text, that will be shown inside the textfield, to tell the user what to put in it
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalTextField(String placeholderText) {
        this(placeholderText, new BitmapFont(), 0, 0, 0, 0);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and a {@link BitmapFont}, on position 0 with the size 0.
     * The textfield cannot be seen without a texture in it or a size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
        🠙currently being used
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param placeholderText the text, that will be shown inside the textfield, to tell the user what to put in it
     * @param font font of the text
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalTextField(String placeholderText, BitmapFont font) {
        this(placeholderText, font, 0, 0, 0, 0);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and the basic {@link BitmapFont}, on a certain position with the size 0.
     * The textfield cannot be seen without a texture in it or a size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
        🠙currently being used
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param placeholderText the text, that will be shown inside the textfield, to tell the user what to put in it
     * @param x X position of textfield
     * @param y Y position of textfield
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalTextField(String placeholderText, int x, int y) {
        this(placeholderText, new BitmapFont(), x, y, 0, 0);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and the basic {@link BitmapFont}, on a certain position with a certain size.
     *
     *  <h3>Further Constructors</h3>
     *  <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
        🠙currently being used
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param placeholderText the text, that will be shown inside the textfield, to tell the user what to put in it
     * @param x X position of textfield
     * @param y Y position of textfield
     * @param fieldWidth width of textfield
     * @param fieldHeight height of textfield
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight) {
        this(placeholderText, new BitmapFont(), x, y, fieldWidth, fieldHeight);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and a {@link BitmapFont}, on a certain position with the size 0.
     * The textfield cannot be seen without a texture in it or a size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
        🠙currently being used
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);}</pre>
     * @param placeholderText the text, that will be shown inside the textfield, to tell the user what to put in it
     * @param x X position of textfield
     * @param y Y position of textfield
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalTextField(String placeholderText, BitmapFont font, int x, int y) {
        this(placeholderText, font, x, y, 0, 0);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and a {@link BitmapFont}, on a certain position with a certain size.
     *
     * <h3>Further Constructors</h3>
     * <pre>{@code
    DropicalTextField();
    DropicalTextField(int x, int y);
    DropicalTextField(int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText);
    DropicalTextField(String placeholderText, BitmapFont font);
    DropicalTextField(String placeholderText, int x, int y);
    DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y);
    DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight);
        🠙currently being used}</pre>
     * @param placeholderText the text, that will be shown inside the textfield, to tell the user what to put in it
     * @param x X position of textfield
     * @param y Y position of textfield
     * @param fieldWidth width of textfield
     * @param fieldHeight height of textfield
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight) {
        //create skin for textfield style
        skin = new Skin();

        //add font to skin
        if(font == null) {
            throw new IllegalArgumentException("The font of the DropicalTextField cannot be null!");
        }
        skin.add("font", font);
        skin.add("fontColor", new Color(0x000000ff));   //basic font color

        //create textfield style
        style = new TextField.TextFieldStyle();
        style.font = skin.getFont("font");
        style.fontColor = new Color(0x000000ff);
        style.messageFont = skin.getFont("font");
        style.messageFontColor = new Color(0x00000055);
        skin.add("default", style);

        field = new TextField("", skin, "default");
        field.setMessageText(placeholderText);

        //set textfield position
        field.setPosition(x, y);

        //set size of testfield
        field.setSize(fieldWidth, fieldHeight);

        //set sprites
        spriteBackground = new Sprite();
        spriteCursor = new Sprite();
        spriteFocused = new Sprite();
        spriteSelection = new Sprite();
        spriteDisabled = new Sprite();
    }


    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setBackgroundTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteBackground = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("background", spriteBackground);
            style.background = skin.newDrawable("background");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param cursorWidth exact height of image file in pixels
     * @param cursorHeight exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setCursorTexture(String internalPath, int cursorWidth, int cursorHeight) {
        if(internalPath != null) {
            spriteCursor = new Sprite(new Texture(internalPath), cursorWidth, cursorHeight);
            skin.add("down", spriteCursor);
            style.cursor = skin.newDrawable("down");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setDisabledTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteDisabled = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("checked", spriteDisabled);
            style.disabledBackground = skin.newDrawable("checked");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setFocusedTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteFocused = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("checkedOver", spriteFocused);
            style.focusedBackground = skin.newDrawable("checkedOver");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @param internalPath path to image file
     * @param srcHeight exact height of image file in pixels
     * @param srcWidth exact width of image file in pixels
     * @throws IllegalArgumentException if path is null.
     */
    public void setSelectionTexture(String internalPath, int srcWidth, int srcHeight) {
        if(internalPath != null) {
            spriteSelection = new Sprite(new Texture(internalPath), srcWidth, srcHeight);
            skin.add("over", spriteSelection);
            style.selection = skin.newDrawable("over");
        } else {
            throw new IllegalArgumentException("Texture path cannot be null!");
        }
    }



    public TextField getField() {
        return field;
    }
}
