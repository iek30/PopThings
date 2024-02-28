package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.juego.Partida;
import com.mygdx.game.settings.Ajustes;
import com.mygdx.game.utils.TipoPantalla;

public class MainMenuScreen extends Screens {
    ScrollPane scroll;
    TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("fondo_menu.png")));
    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("sounds/main_sound.wav"));;

    public MainMenuScreen(MyGdxGame game) {
        super(game);

        if (!musica.isPlaying()){
            musica.play();
            musica.setLooping(true);
            musica.setVolume(0.5f);
        }

        Table menu = new Table();
        menu.setFillParent(true); // La tabla ocupa todo el espacio del escenario
        menu.center(); // Los elementos se centran en la tabla

        for (final TipoPantalla learn : TipoPantalla.values()) {
            TextButton bt = new TextButton(learn.name, Assets.txButtonStyle);
            bt.getLabel().setFontScale(2); // Aumenta el tamaño del texto del botón
            bt.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MainMenuScreen.this.game.setScreen(getScreen(learn));
                }
            });

            menu.row().padTop(15).height(100); // Incrementa la altura del espacio entre botones
            menu.add(bt).expandX().fillX(); // Ajusta el botón para que ocupe toda la fila horizontalmente
        }

        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setFillParent(true);
        stage.addActor(scroll);

    }
    private Screens getScreen(TipoPantalla learn) {

        switch (learn){
            case PARTIDA:
                return new Partida(game);
            default:
                return new Ajustes(game);
        }
    }

    @Override
    public void draw(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {

    }

}

