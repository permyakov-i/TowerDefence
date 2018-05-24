/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.mygdx.game.fieldObjects.DefenseStructure;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UIandField.Cell;
import com.mygdx.game.fieldObjects.Shell;

/**
 * Ловушка
 *
 * @author Admin
 */
public class Trap extends DefenseStructure {


    public Trap(Cell pos, int dmg, int price, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, String Name) {
        super(pos, dmg, price, atkSpeed, pict, pictForBullet, Name);
        _rangeAttack = 0;
    }

    static boolean canStayOnRoad() {
        return true;
    }

    public Shell attack(Unit target) {
        _destroy = true;
        target.reduсeHealth(_damage);
        return null;
    }

    public boolean canAttack(Unit target) {
        return _position.equals(target.position());

    }
}
