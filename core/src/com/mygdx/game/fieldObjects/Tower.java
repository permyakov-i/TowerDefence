/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.mygdx.game.fieldObjects.DefenseStructure;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UIandField.Cell;

/**
 * Башня
 *
 * @author Admin
 */
public class Tower extends DefenseStructure {


    public Tower(Cell pos, int dmg, int price, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, int range, String Name) {
        super(pos, dmg, price, atkSpeed, pict, pictForBullet, Name);
        _rangeAttack = range;
    }

    static boolean canStayOnRoad() {
        return false;
    }

}
