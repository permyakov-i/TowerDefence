/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.UIandField;

/**
 * Клетка поля
 *
 * @author Admin
 */
public class Cell {

    public static int Size = 64; //размер

    /**
     * Конструктор
     *
     * @param x высота
     * @param y ширина
     */
    public Cell(int x, int y) {
        _y = y;
        _x = x;
    }

    private int _y;
    private int _x;
    public int y() {
        return _y;
    }

    public int x() {
        return _x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            return (_x == ((Cell) obj).x() && _y == ((Cell) obj).y());
        }
        return false;

    }

}
