/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.UIandField.Cell;

/**
 * Защитные строения
 *
 * @author Admin
 */
public abstract class DefenseStructure {

    /**
     * Конструктор
     *
     */
    public DefenseStructure(Cell pos, int dmg, int price, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, String Name) {

        _damage = dmg;
        _position = pos;
        _price = price;
        _attackSpeed = atkSpeed;
        _texture = pict;
        _textureForBullet = pictForBullet;
        _destroy = false;
        _name = Name;
    }

    protected String _name;


    /**
     * Атака
     *
     */
    public Shell attack(Unit target) {
        Shell bull = null;
        if (_lastAttackTime == 0 || (TimeUtils.millis() - _lastAttackTime > _attackSpeed * 1000)) {

            _lastAttackTime = TimeUtils.millis();

            if (_textureForBullet != null) {
                TextureRegion temp = new TextureRegion();
                temp.setRegion(_textureForBullet);

                if (_rangeAttack == 0) {
                    target.reduсeHealth(_damage); // Создать пулю у цели
                } else {
                    bull = new Shell(_position.x() * Cell.Size + Cell.Size / 2, _position.y() * Cell.Size + Cell.Size / 2, target, _damage, temp);
                }
            }
        }
        return bull;

    }

    
    
    protected int _damage;

    public int damage() {
        return _damage;
    }
    protected int _price;

    public int price() {
        return _price;
    }

    protected TextureRegion _textureForBullet;

    public TextureRegion textureForBullet() {
        return _textureForBullet;
    }

    protected TextureRegion _texture;

    public TextureRegion texture() {
        return _texture;
    }
    
    @Override
    public String toString() {
        return new String(_name + " - " + _price);
    }

    protected int _rangeAttack;

    protected boolean _destroy;


    public boolean IsDestroy() {
        return _destroy;
    }

    public int rangeAttack() {
        return _rangeAttack;
    }
 
    protected long _lastAttackTime;

 
    public long lastAttackTime() {
        return _lastAttackTime;
    }

    /**
     * Проверка возможности атаки
     *
     */
    public boolean canAttack(Unit target) {
        float x1 = _position.x();
        float x2 = target.position().x();
        float y1 = _position.y();
        float y2 = target.position().y();

        boolean right = x1 >= x2 && x1 <= x2 + _rangeAttack;
        boolean top = y1 >= y2 && y1 <= y2 + _rangeAttack;
        boolean bot = y1 <= y2 && y1 >= y2 - _rangeAttack;
        boolean left = x1 <= x2 && x1 >= x2 - _rangeAttack;

        return ((right && (bot || top)) || (left && (bot || top)));

    }


    protected Cell _position;
    public Cell position() {
        return _position;
    }

    static boolean canStayOnRoad() {
        return false;
    }
    protected float _attackSpeed;
    public float attackSpeed() {
        return _attackSpeed;
    }



}
