package com.mygdx.game;

import com.mygdx.game.UIandField.IntroPanel;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Игра Tower Defence
 * @author Admin
 */
public class TowerDefence extends Game {

    /**
    * Вступительная панель
    */
    public IntroPanel introPanel;
    
    @Override
    public void create() {

        introPanel = new IntroPanel(this);

        this.setScreen(introPanel);

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

}
