/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.UIandField.Cell;

/**
 * Фабрика защиты
 *
 * @author Admin
 */
public class DefenseStructureFactory {

    /**
     * Текстуры конструкций
     */
    private static TextureAtlas Atlas = new TextureAtlas(Gdx.files.internal("Textures.atlas"));

    /**
     * Геттер для оборонных строений
     *
     * @param Type - тип врага
     * @param pos - начальная позиция
     * @return -защитная конструкция
     */
    public static DefenseStructure getDefenceStruct(String Type, Cell pos) {
        // Башни
        if (Type.equals("CannonTower")) {
            return new Tower(pos, 10, 35, 1, Atlas.findRegion("CannonTower"), Atlas.findRegion("MetallicBall"), 3, "CannonTower");
        } else if (Type.equals("MagicTower")) {
            return new Tower(pos, 20, 50, 2f, Atlas.findRegion("MagicTower"), Atlas.findRegion("MagicBall"), 4, "MagicTower");
        } else if (Type.equals("EnergyTower")) {
            return new Tower(pos, 2, 25, 0.3f, Atlas.findRegion("EnergyTower"), Atlas.findRegion("Hit"), 2, "EnergyTower");
        } // Ловушки
        else if (Type.equals("Wire")) {
            return new Trap(pos, 15, 10, 0, Atlas.findRegion("Wire"), Atlas.findRegion("Hit"), "Wire");
        } else if (Type.equals("BlastMine")) {
            return new Trap(pos, 33, 15, 0, Atlas.findRegion("BlastMine"), Atlas.findRegion("Hit"), "BlastMine");
        } else if (Type.equals("Spike")) {
            return new Trap(pos, 50, 20, 0, Atlas.findRegion("Spike"), Atlas.findRegion("Hit"), "Spike");
        }

        return null;

    }
    /**
     * Список башен
     */
    public static String[] ListTowers = {"CannonTower - 50", "MagicTower - 40", "EnergyTower - 35"};
    /**
     * Список ловушек
     */
    public static String[] ListTraps = {"Tripwire - 20", "BlastMine - 35", "Spikes - 25"};

}
