package com.pezcraft.dropical.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class DropicalTextField {
    private TextField field;
    private TextField.TextFieldStyle style;
    private Skin skin;

    private Sprite spriteBackground;
    private Sprite spriteCursor;
    private Sprite spriteDisabled;
    private Sprite spriteFocused;
    private Sprite spriteSelection;

    /** Constructs a new DropicalTextField with no placeholder-text and text, but nevertheless the basic {@link BitmapFont}, on position 0 with the size 0.
     * The text field cannot be seen without a texture in it or a size.
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
     * The text field cannot be seen without a texture in it or a size.
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
     * @param x X position of text field
     * @param y Y position of text field
     */
    public DropicalTextField(int x, int y) {
        this("", new BitmapFont(), x, y, 0, 0);
    }

    /** Constructs a new DropicalTextField no placeholder-text and text, but nevertheless the basic {@link BitmapFont}, on a certain position with a certain size.
     * The text field cannot be seen without a text or texture in it.
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
     * @param x X position of text field
     * @param y Y position of text field
     * @param fieldWidth width of text field
     * @param fieldHeight height of text field
     */
    public DropicalTextField(int x, int y, int fieldWidth, int fieldHeight) {
        this("", new BitmapFont(), x, y, fieldWidth, fieldHeight);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and the basic {@link BitmapFont}, on position 0 with the size 0.
     * The text field cannot be seen without a texture in it or a size.
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
     * @param placeholderText the text, that will be shown inside the text field, to tell the user what to put in it
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalTextField(String placeholderText) {
        this(placeholderText, new BitmapFont(), 0, 0, 0, 0);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and a {@link BitmapFont}, on position 0 with the size 0.
     * The text field cannot be seen without a texture in it or a size.
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
     * @param placeholderText the text, that will be shown inside the text field, to tell the user what to put in it
     * @param font font of the text
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalTextField(String placeholderText, BitmapFont font) {
        this(placeholderText, font, 0, 0, 0, 0);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and the basic {@link BitmapFont}, on a certain position with the size 0.
     * The text field cannot be seen without a texture in it or a size.
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
     * @param placeholderText the text, that will be shown inside the text field, to tell the user what to put in it
     * @param x X position of text field
     * @param y Y position of text field
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
     * @param placeholderText the text, that will be shown inside the text field, to tell the user what to put in it
     * @param x X position of text field
     * @param y Y position of text field
     * @param fieldWidth width of text field
     * @param fieldHeight height of text field
     * @throws IllegalArgumentException if text is null.
     */
    public DropicalTextField(String placeholderText, int x, int y, int fieldWidth, int fieldHeight) {
        this(placeholderText, new BitmapFont(), x, y, fieldWidth, fieldHeight);
    }

    /** Constructs a new DropicalTextField with a certain placeholder-text and a {@link BitmapFont}, on a certain position with the size 0.
     * The text field cannot be seen without a texture in it or a size.
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
     * @param placeholderText the text, that will be shown inside the text field, to tell the user what to put in it
     * @param x X position of text field
     * @param y Y position of text field
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
     * @param placeholderText the text, that will be shown inside the text field, to tell the user what to put in it
     * @param x X position of text field
     * @param y Y position of text field
     * @param fieldWidth width of text field
     * @param fieldHeight height of text field
     * @throws IllegalArgumentException if text or font is null.
     */
    public DropicalTextField(String placeholderText, BitmapFont font, int x, int y, int fieldWidth, int fieldHeight) {
        //create skin for text field style
        skin = new Skin();

        //add font to skin
        if(font == null) {
            throw new IllegalArgumentException("The font of the DropicalTextField cannot be null!");
        }
        skin.add("font", font);
        skin.add("fontColor", new Color(0x000000ff));   //basic font color

        //create text field style
        style = new TextField.TextFieldStyle();
        style.font = skin.getFont("font");
        style.fontColor = new Color(0x000000ff);
        style.messageFont = skin.getFont("font");
        style.messageFontColor = new Color(0x00000055);
        skin.add("default", style);

        //create text field
        field = new TextField("", skin, "default");

        //set placeholder
        field.setMessageText(placeholderText);

        //set text field position
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

    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if font is null.
     */
    public void setFont(BitmapFont font) {
        if(font != null) {
            skin.add("font", font);
            style.font = skin.getFont("font");
        } else {
            throw new IllegalArgumentException("The font of the DropicalTextField cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if font is null.
     */
    public void setPlaceholderFont(BitmapFont font) {
        if(font != null) {
            skin.add("messageFont", font);
            style.messageFont = skin.getFont("messageFont");
        } else {
            throw new IllegalArgumentException("The font of the DropicalTextField cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
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
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setPlaceholderFontColor(Color color) {
        if(color != null) {
            skin.add("messageFontColor", color);
            style.messageFontColor = skin.getColor("messageFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
     *
     * @throws IllegalArgumentException if color is null.
     */
    public void setFocusFontColor(Color color) {
        if(color != null) {
            skin.add("focusedFontColor", color);
            style.focusedFontColor = skin.getColor("focusedFontColor");
        } else {
            throw new IllegalArgumentException("The color of the font cannot be null!");
        }
    }
    /** You have to call <code>yourDropicalTextField.updateStyle()</code> afterwards.
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

    /** You have to to call this, if you...
     * <ul>
     *     <li>changed a text field texture</li>
     *     <li>changed the font</li>
     *     <li>changed a font color</li>
     * </ul>
     */
    public void updateStyle() {
        field.setStyle(style);
    }

    /** Aligns the text inside the text field.
     * <br>For the parameter use...<pre>{@code
    Align.  left  center  right}</pre>
     *
     *  @param textAlign Instead of an integer, you can use {@link Align}.
     */
    public void setTextAlignment(int textAlign) {
        field.setAlignment(textAlign);
    }

    /** Sets the scale of the font.
     */
    public void setFontScale(float fontScale) {
        style.font.getData().setScale(fontScale);
    }
    /** Sets the scale of the font.
     */
    public void setFontScale(float fontScaleX, float fontScaleY) {
        style.font.getData().setScale(fontScaleX, fontScaleY);
    }
    /** @return the X scale of the font.
     */
    public float getFontScaleX() {
        return style.font.getScaleX();
    }
    /** @return the Y scale of the font.
     */
    public float getFontScaleY() {
        return style.font.getScaleY();
    }

    /** Sets the text of the text field.
     */
    public void setText(String text) {
        field.setText(text);
    }
    /** Adds text to the current text of the text field.
     */
    public void appendText(String text) {
        field.appendText(text);
    }
    /** @return the text of the text field.
     */
    public String getText() {
        return field.getText();
    }
    /** Sets the placeholder text of the text field.
     *
     * @throws IllegalArgumentException if placeholder text is null.
     */
    public void setPlaceholderText(String placeholderText) {
        if(placeholderText != null) {
            field.setMessageText(placeholderText);
        } else {
            throw new IllegalArgumentException("The placeholder text inside the DropicalTextField cannot be null!");
        }
    }
    /** @return the placeholder text of the text field.
     */
    public String getPlaceholderText() {
        return field.getMessageText();
    }

    /** Filters entered characters with {@link TextField.TextFieldFilter}.
     * <br>If you just want to allow digits, write...<pre>{@code
new TextField.TextFieldFilter.DigitsOnlyFilter()}</pre>
     * <h3>Usage Example</h3>
     * <pre>{@code
yourTextField.setTextFilter(new TextField.TextFieldFilter() {
    @ Override
    public boolean acceptChar(TextField textField, char c) {
        return Character.isLetter(c) || c == '&';
    }
});}</pre>
     */
    public void setTextFilter(TextField.TextFieldFilter filter) {
        field.setTextFieldFilter(filter);
    }

    /** Sets the maximum amount of characters inside the text field.
     */
    public void setMaxLength(int maxLength) {
        field.setMaxLength(maxLength);
    }

    /** If true, the cursor will automatically jump into the next text field when pressing tab/shift+tab. The text field have to be in a stage.
     */
    public void setAutoFocus(boolean autoFocus) {
        field.setFocusTraversal(autoFocus);
    }
    /** Jumps into the next text field. The text field have to be in a stage.
     *
     * @param upwards If true, it jumps upwards not downwards.
     */
    public void findNextTextField(boolean upwards) {
        field.next(upwards);
    }

    /** Sets the position of the text field.
     */
    public void setPosition(float x, float y) {
        field.setPosition(x, y);
    }
    /** @return the X position of the text field.
     */
    public float getX() {
        return field.getX();
    }
    /** @return the Y position of the text field.
     */
    public float getY() {
        return field.getY();
    }

    /** Sets the size of the text field.
     */
    public void setSize(int buttonWidth, int buttonHeight) {
        field.setSize(buttonWidth, buttonHeight);
    }
    /** @return the width of the text field.
     */
    public float getWidth() {
        return field.getWidth();
    }
    /** @return the height of the text field.
     */
    public float getHeight() {
        return field.getHeight();
    }

    /** Disables the text field.
     */
    public void setDisabled(boolean isDisabled) {
        field.setDisabled(isDisabled);
    }
    /** @return if text field is disabled or not.
     */
    public boolean isDisabled() {
        return field.isDisabled();
    }

    /** Flips the textures on the X axis, but not the text.
     */
    public void flipX() {
        spriteBackground.flip(true, false);
        spriteCursor.flip(true, false);
        spriteFocused.flip(true, false);
        spriteSelection.flip(true, false);
        spriteDisabled.flip(true, false);
    }
    /** Flips the textures on the Y axis, but not the text.
     */
    public void flipY() {
        spriteBackground.flip(false, true);
        spriteCursor.flip(false, true);
        spriteFocused.flip(false, true);
        spriteSelection.flip(false, true);
        spriteDisabled.flip(false, true);
    }

    /** Sets the position of the cursor in the text. (position starting with 0)
     */
    public void setCursorPosition(int cursorPosition) {
        field.setCursorPosition(cursorPosition);
    }
    /** @return the position of the cursor in the text. (position starting with 0)
     */
    public int getCursorPosition() {
        return field.getCursorPosition();
    }

    /** Selects a part of the text.
     */
    public void setSelection(int selectionStart, int selectionEnd) {
        field.setSelection(selectionStart, selectionEnd);
    }
    /** @return the selected text.
     */
    public String getSelection() {
        return field.getSelection();
    }
    /** @return the start point of the text selection
     */
    public int getSelectionStart() {
        return field.getSelectionStart();
    }

    /** Sets the blink interval of the cursor inside the text field in seconds.
     */
    public void setBlinkInterval(float intervalInSeconds) {
        field.setBlinkTime(intervalInSeconds);
    }

    /** Characters will be hidden. The character for the censoring can be set with <code>yourDropicalTextField.setPasswortCharacter('x')</code>
     */
    public void setPasswordMode(boolean passwordMode) {
        field.setPasswordMode(passwordMode);
    }
    /** @return if text field is in password mode.
     */
    public boolean isPasswordMode() {
        return field.isPasswordMode();
    }
    /** Sets the censoring character if the text field is in password mode. Have to be called before setting the password mode.
     */
    public void setPasswordCharacter(char passwordCharacter) {
        field.setPasswordCharacter(passwordCharacter);
    }

    /** Sets, if methods of your program changing the text in your field will fire the {@link ChangeListener.ChangeEvent}.
     * If not, only the changes of the user will fire it.
     */
    public void setProgrammaticChangeEvents(boolean programmaticChangeEvents) {
        field.setProgrammaticChangeEvents(programmaticChangeEvents);
    }
    /** @return if methods of your program will fire the {@link ChangeListener.ChangeEvent} or not.
     */
    public boolean getProgrammaticChangeEvents() {
        return field.getProgrammaticChangeEvents();
    }

    /** Will be fired, when you type something inside the text field.
     */
    public void setTextFieldListener(TextField.TextFieldListener listener) {
        field.setTextFieldListener(listener);
    }
    /** Adds a listener to the text field.
     */
    public void addListener(EventListener listener) {
        field.addListener(listener);
    }

    /** If true (default), characters that are not in your font will be stripped. Otherwise, the character will be replaced by a space.
     */
    public void setOnlyFontChars(boolean onlyFontChars) {
        field.setOnlyFontChars(onlyFontChars);
    }

    /** You need this, to add your text field to a stage.
     */
    public TextField getField() {
        return field;
    }
}
