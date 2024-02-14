package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.utils.TipoPantalla;

public class MainMenuScreen extends Screens {

    ScrollPane scroll;

    public MainMenuScreen(MyGdxGame game) {
        super(game);

        Table menu = new Table();
        menu.defaults().expandY().fillY();

        for (final TipoPantalla learn : TipoPantalla.values()) {
            TextButton bt = new TextButton(learn.name, Assets.txButtonStyle);
            bt.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setScreen(learn);
                }
            });

            menu.row().padTop(15).height(50);
            menu.add(bt).fillX();
        }

        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setSize(500, SCREEN_HEIGHT);
        scroll.setPosition(150, 0);
        stage.addActor(scroll);
    }

    private void setScreen(TipoPantalla learn) {
        try {
            Screens newScreen = learn.clazz.getConstructor(MyGdxGame.class).newInstance(game);
            game.setScreen(newScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
    }

}
