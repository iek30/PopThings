package com.mygdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;
import com.mygdx.game.MainMenuScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens;
import com.mygdx.game.utils.TipoPantalla;

public class Ajustes extends Screens {
    private TextField usernameField;
    ScrollPane scroll;
    TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("fondo_menu.png")));

    public Ajustes(MyGdxGame game) {
        super(game);

        Table menu = new Table();
        menu.setFillParent(true); // La tabla ocupa todo el espacio del escenario
        menu.center(); // Los elementos se centran en la tabla

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();

        // Configura la fuente y el color del texto
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        style.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("white.png"))));
        style.background.setLeftWidth(0); // Ajusta el margen izquierdo a 0 para que no haya espacio vacío
        style.background.setRightWidth(0);

        TextField txField = new TextField("",style);

        menu.row().padTop(15).height(100); // Incrementa la altura del espacio entre botones
        menu.add(txField).expandX().fillX(); // Ajusta el botón para que ocupe toda la fila horizontalmente


        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setFillParent(true);
        stage.addActor(scroll);
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
        // No se necesita una actualización continua en esta pantalla
    }

    @Override
    public void show() {
        super.show();
    }

}