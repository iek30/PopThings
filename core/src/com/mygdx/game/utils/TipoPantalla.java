package com.mygdx.game.utils;

import com.mygdx.game.Screens;
import com.mygdx.game.classes.Partida;

public enum TipoPantalla {

    PARTIDA("JUGAR PARTIDA", Partida.class),
    AJUSTES("AJUSTES", Partida.class);


    public final String name;
    public final Class<? extends Screens> clazz;

    TipoPantalla(String label, Class<? extends Screens> clazz) {
        this.name = label;
        this.clazz = clazz;
    }

}
