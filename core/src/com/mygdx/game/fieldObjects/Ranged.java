/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UIandField.Cell;
import java.util.ArrayList;

/**
 * Ренжи
 * @author Admin
 */
public  class Ranged extends Unit{
       
   public Ranged(Cell pos, ArrayList<Cell> road,int hp,int dmg,float speed, int moneyForKill,float atkSpeed, TextureRegion  pict, int range,TextureRegion  pictForBullet, boolean side, boolean teleporter){
       super(pos,road,hp,dmg,speed,moneyForKill,atkSpeed,pict,pictForBullet,side,teleporter);
         _rangeAttack=range;
    }
}
    
