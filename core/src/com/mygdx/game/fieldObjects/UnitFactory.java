/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.UIandField.Cell;
import java.util.ArrayList;

/**
 * Фабрика юнитов
 *
 * @author Admin
 */
public class UnitFactory {


    private static TextureAtlas unitAtlas = new TextureAtlas(Gdx.files.internal("Textures.atlas"));

  
    public static Unit getUnit(String Type, Cell pos, ArrayList<Cell> road) {
        // Ближний бой
        if (Type.equals("Vampyre")) {
            return new Melee(pos, road, 15, 5, 2.2f, 4, 1.5f, unitAtlas.findRegion("Vampyre"), unitAtlas.findRegion("Nothing"),false,false);
        } else if (Type.equals("Knight")) {
            return new Melee(pos, road, 25, 30, 1.8f, 6, 1.3f, unitAtlas.findRegion("Knight"), unitAtlas.findRegion("Nothing"),false,false);
        } else if (Type.equals("Ork")) {
            return new Melee(pos, road, 30, 10, 2.4f, 6, 1.7f, unitAtlas.findRegion("Ork"), unitAtlas.findRegion("Nothing"),false,false);
        } else if (Type.equals("Assasin")) {
            return new Melee(pos, road, 30, 10, 2.4f, 6, 1.7f, unitAtlas.findRegion("Ork"), unitAtlas.findRegion("Nothing"), true,true);
        } //Дальний бой
        else if (Type.equals("Mage")) {
            return new Ranged(pos, road, 10, 5, 2.2f, 6, 1.5f, unitAtlas.findRegion("Mage"), 2, unitAtlas.findRegion("MagicBall"),false,false);
        } else if (Type.equals("Elven")) {
            return new Ranged(pos, road, 15, 7, 2.3f, 8, 1f, unitAtlas.findRegion("Elven"), 3, unitAtlas.findRegion("MetallicBall"),false,false);
        } else if (Type.equals("Soldier")) {
            return new Ranged(pos, road, 20, 2, 2.0f, 8, 1.7f, unitAtlas.findRegion("Soldier"), 2, unitAtlas.findRegion("MetallicBall"),false,false);
        } else if (Type.equals("Tracer")) {
            return new Ranged(pos, road, 20, 2, 2.0f, 8, 1.7f, unitAtlas.findRegion("Soldier"), 2, unitAtlas.findRegion("MetallicBall"), true,true);
        }
        return null;

    }
    /**
     * Список врагов
     */
    public static String[] EnemyList = {"Vampyre", "Knight", "Ork", "Mage", "Elven", "Soldier", "Assasin", "Tracer"};
}
