package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class Assets {

    public static BitmapFont font;
    public static TextButton.TextButtonStyle txButtonStyle;
    public static ScrollPane.ScrollPaneStyle scrollPaneStyle;
    public static TextField.TextFieldStyle txFieldStyle;

    public static void load() {
        font = new BitmapFont();

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui.txt"));

        txButtonStyle = new TextButton.TextButtonStyle(new NinePatchDrawable(
                atlas.createPatch("bt")), new NinePatchDrawable(
                atlas.createPatch("btDown")), null, font);

        NinePatchDrawable knob = new NinePatchDrawable(
                atlas.createPatch("scroll"));
        scrollPaneStyle = new ScrollPane.ScrollPaneStyle(null, knob, knob, knob, knob);


    }

}
