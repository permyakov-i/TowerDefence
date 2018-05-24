/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.mygdx.game.UIandField.Cell;
import java.util.ArrayList;
import java.util.Random;

/**
 * Волна врагов
 * @author Admin
 */
public class Wave {
    public Wave (ArrayList<Unit> units){
        _units=units;

    }
 
    protected ArrayList<Unit> _units;


    public ArrayList<Unit> units() {
        return _units;
    }

    public static Wave createRandomWave(int countEnemy, ArrayList<Cell> road)
    {
        ArrayList<Unit> units=new ArrayList<Unit>();
        for(int i=countEnemy; i>0; i--)
        {              
            Random rand = new Random();        
            int num =rand.nextInt( UnitFactory.EnemyList.length);
            units.add(UnitFactory.getUnit(UnitFactory.EnemyList[num], road.get(0), road));
            
        }
        return new Wave(units);
    }

}
