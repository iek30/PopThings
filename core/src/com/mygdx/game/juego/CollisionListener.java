package com.mygdx.game.juego;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        //Primero compruebo si la colisión no es con paredes o suelo.
        if (bodyA.getUserData() instanceof GameObject && bodyB.getUserData() instanceof GameObject) {
            GameObject gameObjectA = (GameObject) bodyA.getUserData();
            GameObject gameObjectB = (GameObject) bodyB.getUserData();

            //Ahora puedo comprobar si son del mismo tipo.
            if (gameObjectA.type == gameObjectB.type) {
                if (gameObjectA.state == GameObject.STATE_NORMAL && gameObjectB.state == GameObject.STATE_NORMAL) {
                    gameObjectA.hit();
                    gameObjectB.hit();
                    //Reproduzco sonido de explosión.
                    Partida.sonido4.play();
                }
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getUserData() instanceof GameObject && bodyB.getUserData() instanceof GameObject) {
            GameObject gameObjectA = (GameObject) bodyA.getUserData();
            GameObject gameObjectB = (GameObject) bodyB.getUserData();

            //Compruebo en que estado se encuentra el objeto, si está disponible puede explotar.
            if (gameObjectA.state != GameObject.STATE_NORMAL || gameObjectB.state != GameObject.STATE_NORMAL)
                contact.setEnabled(false);
            else
                contact.setEnabled(true);
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
