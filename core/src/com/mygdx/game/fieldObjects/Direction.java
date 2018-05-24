/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.mygdx.game.UIandField.Cell;

/**
    /*
 * Direction - абстракция направления в системе координат "север-юг-восток-запад"; 
 * позволяет сравнивать направления и порождать новые направления относительно 
 * текущего
 */
public class Direction {
    
   /**
     * Направление в градусах
     */
    private int _angle = 90;
    /**
     * Высота
     * @param anle угл
     */
    private Direction(int angle) {
        // Приводим заданный угол к допустимому диапазону 
        angle = angle%360;
        if(angle < 0)    angle += 360;
        
        this._angle = angle;
    }
    
    // ------------------ Возможные направления ---------------------
       /**
     * Направление север
     */
    public static Direction north()
    { return new Direction(90); }
    /**
     * Направление юг
     */
    public static Direction south()
    { return new Direction(270); }
    /**
     * Направление запад
     */
    public static Direction east()
    { return new Direction(0); }
    /**
     * Направление восток
     */
    public static Direction west()
    { return new Direction(180); }
    
      // ------------------ Сравнить направления ---------------------
    /**
     * Направление сравнение
     */
    @Override
    public boolean equals(Object other) {

        if(other instanceof Direction) {
            // Типы совместимы, можно провести преобразование
            Direction otherDirect = (Direction)other;
            // Возвращаем результат сравнения углов
            return  _angle == otherDirect._angle;
        }
        
        return false;
    }
    
          /**
     * Определить направление движения к ячейке
     * @param first начальная позиция
     * @param nextCell позиция ячейки на карте
     * @return Direction - Направление
     * 
    */    
   static public Direction defineDirect(Cell first,Cell nextCell)
    {
        if(first.x()<nextCell.x())
        {
            return Direction.west();
                }
        else if(first.x()>nextCell.x())
        {
              return Direction.east();
        }
        else if(first.y()<nextCell.y())
        {
          return Direction.north();
        }
        else  if(first.y()>nextCell.y())
        {
          return Direction.south();
        }  
        return null;
    }
   /**
     * Развернуть
     * @return противоположное направление
     */
    public Direction opposite() { 
        return new Direction(this._angle+180); 
    }
    /**
     * Противоположное ли направление
     * @param other направление для сравнения
     * @return  результат
     */
     public boolean isOpposite(Direction other) {
        return this.opposite().equals(other);
    }
    
    
}
