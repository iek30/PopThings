package com.mygdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.mygdx.game.Assets;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Puntuaciones;
import com.mygdx.game.Screens;


public class Ajustes extends Screens {

    ScrollPane scroll;
    TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("record_screen.png")));

    Puntuaciones puntuaciones = new Puntuaciones();
    int record;
    public Ajustes(MyGdxGame game) {
        super(game);
        record = puntuaciones.getHighScore();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void draw(float delta) {

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        spriteBatch.begin();
        Assets.font.draw(spriteBatch, "Tu record: " + record, 100, SCREEN_HEIGHT/2+550);
        Assets.font.setColor(Color.BROWN);
        float scaleFactor = 10f;
        Assets.font.getData().setScale(scaleFactor);
        spriteBatch.end();

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
