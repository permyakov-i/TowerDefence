/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Снаряд
 *
 * @author Admin
 */
public class Shell {

    
    public Shell(float x, float y, float destx, float desty, int dmg, TextureRegion pict) {
        _x = x;
        _y = y;
        _destx = destx;
        _desty = desty;
        _damage = dmg;
        _texture = new TextureRegion();
        _texture.setRegion(pict);
        _toEnemy = false;

    }

    public Shell(float x, float y, Unit target, int dmg, TextureRegion pict) {
        _x = x;
        _y = y;
        _target = target;
        _damage = dmg;
        _texture = new TextureRegion();
        _texture.setRegion(pict);
        _toEnemy = true;
    }
       private Unit _target;

    public Unit target() {
        return _target;
    }

 
    private float _x, _y;

    public float x() {
        return _x;
    }

    public float y() {
        return _y;
    }

    private float _destx, _desty;

    public float destx() {
        return _destx;
    }

    public float desty() {
        return _desty;
    }
    
      public void move() {
        if (_target != null) {
            _destx = _target.x();
            _desty = _target.y();
        }
        float speed = 4;

        float x;
        if (_x != _destx && _y != _desty) {
            if (_x < _destx) {
                if (_x + speed < _destx) {
                    x = _x + speed;
                } else {
                    x = _destx;
                }
            } else if (_x > _destx) {
                if (_destx + speed < _x) {
                    x = _x - speed;
                } else {
                    x = _destx;
                }
            } else {
                x = _destx;
            }

            _y = (x - _x) * (_desty - _y) / (_destx - _x) + _y;

            _x = x;
        }
    }
      
    private int _damage;

    public int damage() {
        return _damage;
    }
  
    private TextureRegion _texture;

    public TextureRegion texture() {
        return _texture;
    }
    public boolean moveOff() {
        return _x == _destx && _y == _desty;
    }

    public boolean toEnemy() {
        return _toEnemy;
    }
    boolean _toEnemy;

}
