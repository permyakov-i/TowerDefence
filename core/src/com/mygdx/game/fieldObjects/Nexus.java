/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.UIandField.Cell;

/**
 *  Нексус
 * @author Admin
 * 
 */
public class Nexus {
     
  
  public Nexus(Cell position, int integrity)
   {
      
      _position = position;
      _integrity = integrity;
      _maxIntegrity=integrity;
      _lastEffectTime=0;
   }
 
    private int _integrity;
  public int integrity()
   {
       return _integrity;
   }
  public void addIntegrity(int value)
     
   {
       if(_integrity+value<=_maxIntegrity)
             _integrity+=value;
       else
           _integrity=_maxIntegrity;
   }
   
  public void DecreaseIntegrity(int value)
   {

       if(_integrity-value<=0)
           _integrity=0;
       else
           _integrity=_integrity-value;
   }
   private Cell _position;


  public Cell position()
   {
       return _position;
   }
  
     private int _maxIntegrity;
    

  public int maxIntegrity()
   {
       return _maxIntegrity;
   }
  
    protected long _lastEffectTime;

  
    public long lastEffectTime() {
        return _lastEffectTime;
    }
   
    public void setLastEffectTime(long value) {
        _lastEffectTime=value;
    }
     
    
}
