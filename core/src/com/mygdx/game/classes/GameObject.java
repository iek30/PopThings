package com.mygdx.game.classes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class GameObject {

    static final int BALL = 0;
    static final int JAVA = 1;
    static final int ZOMBI = 2;
    static final int ANDROID = 3;

    static final int STATE_NORMAL = 0;
    static final int STATE_EXPLODE = 1;
    static final int STATE_REMOVE = 2;

    static final float EXPLOSION_DURATION = 0.95f;

    int state;
    float stateTime = 0;


    int type;
    float angleDeg;
    Vector2 position;

    public GameObject(float x, float y, int type) {
        position = new Vector2(x, y);
        state = STATE_NORMAL;
        this.type = type;
    }

    public void update(Body body, float delta) {
        if (state == STATE_NORMAL) {
            position.x = body.getPosition().x;
            position.y = body.getPosition().y;
            angleDeg = (float) Math.toDegrees(body.getAngle());
        } else if (state == STATE_EXPLODE) {
            if (stateTime >= EXPLOSION_DURATION) {
                state = STATE_REMOVE;
                stateTime = 0;
            }
        }
        stateTime += delta;
    }

    public void hit() {
        if (state == STATE_NORMAL) {
            state = STATE_EXPLODE;
            stateTime = 0;
            Partida.puntos++;
            Partida.cont = Partida.cont - 1;
        }
    }
}
