package com.mygdx.game.utils;

import com.mygdx.game.Screens;
import com.mygdx.game.tutoriales.Learn1;

public enum Learn {

    LEARN_1("JUGAR PARTIDA", Learn1.class);

    public final String name;
    public final Class<? extends Screens> clazz;

    Learn(String label, Class<? extends Screens> clazz) {
        this.name = label;
        this.clazz = clazz;
    }

}
