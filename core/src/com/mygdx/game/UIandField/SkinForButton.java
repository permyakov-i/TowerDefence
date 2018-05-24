/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.UIandField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 * @author Admin
 */
public class SkinForButton {

    public static Skin createBasicSkin() {
        //Create a font
        Skin buttonsSkin = new Skin();
        BitmapFont font = new BitmapFont();
        buttonsSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 12, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonsSkin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonsSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = buttonsSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonsSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = buttonsSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonsSkin.getFont("default");
        buttonsSkin.add("default", textButtonStyle);
        return buttonsSkin;
    }

}
