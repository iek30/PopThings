package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class Screens extends InputAdapter implements Screen {

    public static final float SCREEN_WIDTH = 1080;
    public static final float SCREEN_HEIGHT = 1920;

    public static final float WORLD_WIDTH = 2f;
    public static final float WORLD_HEIGHT = 5.8f;

    public MyGdxGame game;

    public OrthographicCamera oCamUI;
    public OrthographicCamera oCamBox2D;
    public SpriteBatch spriteBatch;
    public Stage stage;

    public Screens(MyGdxGame game) {
        this.game = game;

        stage = new Stage(new StretchViewport(Screens.SCREEN_WIDTH, Screens.SCREEN_HEIGHT));

        oCamUI = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        oCamUI.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);

        oCamBox2D = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        oCamBox2D.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);

        InputMultiplexer input = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(input);

        spriteBatch = new SpriteBatch();
    }

    // Se llama cada 60fps
    @Override
    public void render(float delta) {

        try {
            update(delta);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stage.act(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        try {
            draw(delta);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stage.draw();
    }

    public abstract void draw(float delta) throws InterruptedException;

    public abstract void update(float delta) throws InterruptedException;

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }



    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK){

            if (this instanceof MainMenuScreen) {
                Gdx.app.exit();
            } else {
                game.setScreen(new MainMenuScreen(game));
            }

        }
        return super.keyDown(keycode);
    }

}
