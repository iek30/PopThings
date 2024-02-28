package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Puntuaciones {
    private static final String PREFERENCE_NAME = "Record";
    private static final String HIGH_SCORE_KEY = "Record";

    private Preferences preferences;

    public Puntuaciones() {
        preferences = Gdx.app.getPreferences(PREFERENCE_NAME);
    }

    public void saveHighScore(int score) {
        int currentHighScore = preferences.getInteger(HIGH_SCORE_KEY, 0);
        if (score > currentHighScore) {
            preferences.putInteger(HIGH_SCORE_KEY, score);
            preferences.flush();
        }
    }

    public int getHighScore() {
        return preferences.getInteger(HIGH_SCORE_KEY, 0);
    }
}
