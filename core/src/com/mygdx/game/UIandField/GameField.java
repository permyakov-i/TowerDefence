/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.UIandField;

import com.mygdx.game.fieldObjects.Nexus;
import com.mygdx.game.fieldObjects.Unit;
import com.mygdx.game.fieldObjects.UnitFactory;
import com.mygdx.game.fieldObjects.Wave;
import java.util.ArrayList;
import com.mygdx.game.fieldObjects.DefenseStructure;
import java.util.Random;

/**
 * Игровая карта
 *
 * @author Admin
 */
public class GameField {

    /**
     * Конструктор
     *
     * @param name название
     * @param startMoney стартовые деньги
     *
     */
    GameField(String name, int startMoney, Nexus main) {
        _main = main;
        _name = name;
        _startMoney = startMoney;
        _height = 10;
        _width = 16;
    }
    /**
     * Нексус
     */
    private Nexus _main;

    public Nexus main() {
        return _main;
    }

    private String _name;

    public String name() {
        return _name;
    }

    private int _width;

    public int width() {
        return _width;
    }
 
    private int _height;

    public int height() {
        return _height;
    }

    private int _startMoney;

    public int moneyOnStart() {
        return _startMoney;
    }

    private ArrayList<DefenseStructure> _defenseConst = new ArrayList();

    public ArrayList<DefenseStructure> defenseConst() {
        return _defenseConst;
    }

 
    private ArrayList<Cell> _roadCell = new ArrayList();
    private ArrayList<Cell> _sideRoadCell = new ArrayList();

    public ArrayList<Cell> roadCell() {
        return _roadCell;
    }
    
    public ArrayList<Cell> sideRoadCell() {
        return _sideRoadCell;
    }

    private ArrayList<Wave> _waves = new ArrayList();

    public ArrayList<Wave> waves() {
        return _waves;
    }


    public boolean CheckCell(Cell position) {
        if (_main.position().equals(position)) {
            return true;
        }

        for (DefenseStructure dc : _defenseConst) {
            if (dc.position().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckRoad(Cell position) {
        for (Cell cell : _roadCell) {
            if (cell.equals(position)) {
                return true;
            }
        }
        for (Cell cell : _sideRoadCell) {
            if (cell.equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Генерация карты
     *
     */
    static public GameField GenerateField() {
        Nexus main = new Nexus(new Cell(14, 4), 200);
        // Создать первую карту
        GameField map = new GameField("Standart", 100, main);
        for (int i = 0; i < 6; i++) {
            map.roadCell().add(new Cell(0, i));
        }
        for (int i = 0; i < 5; i++) {
            map.roadCell().add(new Cell(i, 6));
        }
        for (int i = 6; i > 1; i--) {
            map.roadCell().add(new Cell(5, i));
        }
        for (int i = 5; i < 10; i++) {
            map.roadCell().add(new Cell(i, 1));
        }
        for (int i = 1; i < 4; i++) {
            map.roadCell().add(new Cell(10, i));
        }
        for (int i = 10; i < 14; i++) {
            map.roadCell().add(new Cell(i, 4));
        }
        for (int i = 0; i < 6; i++) {
            map.sideRoadCell().add(new Cell(1, i));
        }
        for (int i = 2; i < 5; i++) {
            map.sideRoadCell().add(new Cell(i, 5));
        }
        for (int i = 4; i >= 0; i--) {
            map.sideRoadCell().add(new Cell(4, i));
        }
        for (int i = 5; i < 11; i++) {
            map.sideRoadCell().add(new Cell(i, 0));
        }
        for (int i = 0; i < 4; i++) {
            map.sideRoadCell().add(new Cell(11, i));
        }
        for (int i = 12; i < 15; i++) {
            map.sideRoadCell().add(new Cell(i, 3));
        }

        // 1
        ArrayList<Unit> enemies = new ArrayList<Unit>();
        enemies.add(UnitFactory.getUnit("Vampyre", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Soldier", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));
        //enemies.add(UnitFactory.getUnit("Assasin", map.sideRoadCell().get(0), map.sideRoadCell()));
        //enemies.add(UnitFactory.getUnit("Tracer", map.sideRoadCell().get(0), map.sideRoadCell()));
        map.waves().add(new Wave(enemies));
        // 2
        enemies = new ArrayList<Unit>();
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Knight", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Soldier", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Knight", map.roadCell().get(0), map.roadCell()));
        map.waves().add(new Wave(enemies));
        // 3
        enemies = new ArrayList<Unit>();
        enemies.add(UnitFactory.getUnit("Soldier", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Soldier", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Mage", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Mage", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Soldier", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Elven", map.roadCell().get(0), map.roadCell()));
        enemies.add(UnitFactory.getUnit("Ork", map.roadCell().get(0), map.roadCell()));

        map.waves().add(new Wave(enemies));

        return map;
    }



}
