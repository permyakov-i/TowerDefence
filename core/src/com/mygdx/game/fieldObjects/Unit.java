/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.fieldObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import com.mygdx.game.UIandField.Cell;
import com.mygdx.game.UIandField.Pair;
import com.mygdx.game.fieldObjects.Shell;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Юниты
 *
 * @author Admin
 */
public abstract class Unit {


    public Unit(Cell pos, ArrayList<Cell> road, int hp, int dmg, float speed, int moneyForKill, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, boolean side, boolean teleporter) {
        _healPoints = hp;
        _damage = dmg;
        _speed = speed;
        _moneyForKill = moneyForKill;
        _position = pos;
        _roadCell = road;
        _sideRoadCell = road;
        _texture = pict;
        _textureForBullet = pictForBullet;
        _direction = Direction.south();
        _attackSpeed = atkSpeed;
        _side=side;
        Random rand = new Random();

        _x = pos.x() * Cell.Size + rand.nextInt(32);
        _y = pos.y() * Cell.Size + rand.nextInt(32);
        
       if (!side)
        _path = calculatePath();
       else
           _path = calculateSidePath();
       //int delay = (5 + new Random().nextInt(5)) * 1000+500;
       if (teleporter)
       {
           //timer.schedule(new Task(), 15000);
           timer.schedule(new Task(), 20000);
           timer.schedule(new Task(), 15000);
       }
    }

    private List<Pair<Direction, Float>> _path;
    private boolean _side;

    static Timer timer = new Timer();

    class Task extends TimerTask {
        @Override
        public void run() {
            teleport();
        }

    }
     
    public void teleport (){
       if (_side)
       {
        if (_direction.equals(Direction.north())) {
                  _position=new Cell(_position.x()-1,_position.y()+1);
                _x=_x-64;
                _y=_y+64;
            } else if (_direction.equals(Direction.south())) {
                 _position=new Cell(_position.x()+1,_position.y()+1);
               _x=_x+64;
               _y=_y+64;
            } else if (_direction.equals(Direction.west())) {
                  _position=new Cell(_position.x(),_position.y()+1);
                _y=_y+64;
                _x=_x+64;
            } else if (_direction.equals(Direction.east())) {
                _position=new Cell(_position.x(),_position.y()+1);
                _y=_y+64;
                _x=_x+64;
            }
        if (_roadCell.lastIndexOf(_position)>0)
            _path = recalculatePath(_roadCell.lastIndexOf(_position));
        _side=false;
        }else
       {
          if (_direction.equals(Direction.north())) {
                  _position=new Cell(_position.x()+1,_position.y());
                _x=_x-64;
                _y=_y-64;
            } else if (_direction.equals(Direction.south())) {
                 _position=new Cell(_position.x()-1,_position.y());
                _x=_x-64;
                _y=_y-64;
            } else if (_direction.equals(Direction.west())) {
                  _position=new Cell(_position.x(),_position.y()-1);
                _y=_y-64;
                _x=_x-64;
            } else if (_direction.equals(Direction.east())) {
                _position=new Cell(_position.x(),_position.y()-1);
                _y=_y-64;
                _x=_x+64;
            }
           if (_sideRoadCell.lastIndexOf(_position)>0)
            _path = recalculateSidePath(_sideRoadCell.lastIndexOf(_position));
           _side=true;
       }
    }
   
    private Direction _direction;

    public Direction direction() {
        return _direction;
    }

    protected int _moneyForKill;

    public int moneyForKill() {
        return _moneyForKill;
    }

    protected int _damage;

    public int damage() {
        return _damage;
    }

    protected int _healPoints;

    public int healPoints() {
        return _healPoints;
    }

    protected int _rangeAttack;

    public int rangeAttack() {
        return _rangeAttack;
    }

    protected Cell _position;

    public Cell position() {
        return _position;
    }

    protected float _speed;

    public float speed() {
        return _speed;
    }

    protected ArrayList<Cell> _roadCell = new ArrayList();
    protected ArrayList<Cell> _sideRoadCell = new ArrayList();

    public ArrayList<Cell> roadCell() {
        return _roadCell;
    }
    public ArrayList<Cell> sideRoadCell() {
        return _roadCell;
    }
    protected float _attackSpeed;

    public float attackSpeed() {
        return _attackSpeed;
    }

    protected TextureRegion _texture;


    public TextureRegion texture() {
        return _texture;
    }

    protected long _lastAttackTime;

    public long lastAttackTime() {
        return _lastAttackTime;
    }

    protected TextureRegion _textureForBullet;

    public TextureRegion textureForBullet() {
        return _textureForBullet;
    }
 
    protected float _x, _y;

    public float x() {
        return _x;
    }

    public float y() {
        return _y;
    }

    public void move(float shift) {
        if (_path.size() > 0) {
            _direction = _path.get(0).getFirst();

            float deltaC = _path.get(0).getSecond() - (_speed - shift);

            float countStep;
            if (deltaC < 0) {
                countStep = _path.get(0).getSecond();
                _path.remove(0);
            } else {
                _path.get(0).setSecond(deltaC);
                countStep = _speed - shift;
            }

            if (deltaC == 0) {
                countStep = 0;
                _path.remove(0);

            }

            if (_direction.equals(Direction.north())) {
                _y += countStep;
                if (countStep == 0 || deltaC < 0) {
                    _position = new Cell(_position.x(), _position.y() + 1);
                }
            } else if (_direction.equals(Direction.south())) {
                _y -= countStep;
                if (countStep == 0 || deltaC < 0) {
                    _position = new Cell(_position.x(), _position.y() - 1);
                }
            } else if (_direction.equals(Direction.west())) {
                _x += countStep;
                if (countStep == 0 || deltaC < 0) {
                    _position = new Cell(_position.x() + 1, _position.y());
                }
            } else if (_direction.equals(Direction.east())) {
                _x -= countStep;
                if (countStep == 0 || deltaC < 0) {
                    _position = new Cell(_position.x() - 1, _position.y());
                }
            }
            if (deltaC < 0) {
                move(deltaC * -1);
            }
        }
    }
     
    public boolean canAttack(Cell target) {

        int deltX = _position.x() - target.x();
        int deltY = _position.y() - target.y();
        return (deltX > 0 && deltX <= _rangeAttack && deltY == 0) // Цель слева от врага
                || (deltX < 0 && (deltX * -1) <= _rangeAttack && deltY == 0) // Цель справа от врага
                || (deltY > 0 && deltY <= _rangeAttack && deltX == 0) // Цель снизу от врага
                || (deltY < 0 && (deltY * -1) <= _rangeAttack && deltX == 0);     // Цель сверху от врага

    }

     public void reduсeHealth(int value) {
        if (_healPoints - value <= 0) {
            _healPoints = 0;
        } else {
            _healPoints = _healPoints - value;
        }
    }
      
    public Shell attack(Cell target, float x, float y) {

        _direction = Direction.defineDirect(_position, target);
        if (_lastAttackTime == 0 || (TimeUtils.millis() - _lastAttackTime > _attackSpeed * 1000)) {

            _lastAttackTime = TimeUtils.millis();

            if (_textureForBullet != null) {
                TextureRegion temp = new TextureRegion();
                temp.setRegion(_textureForBullet);

                if (_rangeAttack == 0) {
                    return new Shell(x, y, x, y, _damage, temp); // Создать пулю у цели
                }
                return new Shell(_x, _y, x, y, _damage, temp);
            }
            return null;
        } else {
            return null;
        }

    }

     private List<Pair<Direction, Float>> recalculatePath(int n) {
        List<Pair<Direction, Float>> road = new ArrayList<Pair<Direction, Float>>();
        Cell temp = _position;
        for (Cell cell : _roadCell.subList(n, _roadCell.size())) {
            Pair<Direction, Float> pair = new Pair<Direction, Float>(Direction.defineDirect(temp, cell), (float) Cell.Size);
            road.add(pair);
            temp = cell;
        }
        road.remove(0);
        return road;
    }
     
     private List<Pair<Direction, Float>> recalculateSidePath(int n) {
        List<Pair<Direction, Float>> road = new ArrayList<Pair<Direction, Float>>();
        Cell temp = _position;
        for (Cell cell : _sideRoadCell.subList(n, _sideRoadCell.size())) {
            Pair<Direction, Float> pair = new Pair<Direction, Float>(Direction.defineDirect(temp, cell), (float) Cell.Size);
            road.add(pair);
            temp = cell;
        }
        road.remove(0);
        return road;
    }

    private List<Pair<Direction, Float>> calculatePath() {
        List<Pair<Direction, Float>> road = new ArrayList<Pair<Direction, Float>>();
        Cell temp = _position;
        for (Cell cell : _roadCell) {
            Pair<Direction, Float> pair = new Pair<Direction, Float>(Direction.defineDirect(temp, cell), (float) Cell.Size);
            road.add(pair);
            temp = cell;
        }
        road.remove(0);
        return road;
    }
    
    private List<Pair<Direction, Float>> calculateSidePath() {
        List<Pair<Direction, Float>> road = new ArrayList<Pair<Direction, Float>>();
        Cell temp = _position;
        for (Cell cell : _sideRoadCell) {
            Pair<Direction, Float> pair = new Pair<Direction, Float>(Direction.defineDirect(temp, cell), (float) Cell.Size);
            road.add(pair);
            temp = cell;
        }
        road.remove(0);
        return road;
    }
 

}
