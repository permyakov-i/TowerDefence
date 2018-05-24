/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoaderAndBot;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.TowerDefence;
import com.mygdx.game.fieldObjects.DefenseStructureFactory;
import LoaderAndBot.Module;
import com.mygdx.game.UIandField.Cell;
import com.mygdx.game.UIandField.MainPanel;
import java.util.Random;

public class Bot implements Module {

    private long _lastActionTime = 0;
    private MainPanel _panel = null;
    private Cell _targetPos = null;

    @Override
    public void load( MainPanel panel) {
        System.out.println("Module " + this.getClass() + " loading ...");
        _panel = panel;
    }

    @Override
    public int run(TowerDefence game) {
        if (_lastActionTime == 0 || (TimeUtils.millis() - _lastActionTime > 1 * 300)) {
            _lastActionTime = TimeUtils.millis();
            System.out.println("Module " + this.getClass() + "Bot action");
            //Определить состояние игры
            if (_panel.nexusHP() > 0 && !_panel.Win) {
                if (_targetPos == null) {
                    // Определить позицию для башни.
                    _targetPos = calculatePlacement(game);
                }
                if (_panel.currentCell.equals(_targetPos)) {
                    Random rand = new Random();
                    int num = rand.nextInt(3);

                    // Передвинуть ячейку
                    _panel.currentCell = _targetPos;
                    // Поставить башню
                    if (num == 0 && !_panel.isRoad(_targetPos)) {
                        _panel.buyTower(DefenseStructureFactory.getDefenceStruct("CannonTower", _panel.currentCell));
                    } else if (num == 1 && !_panel.isRoad(_targetPos)) {
                        _panel.buyTower(DefenseStructureFactory.getDefenceStruct("MagicTower", _panel.currentCell));
                    } else if (num == 2 && !_panel.isRoad(_targetPos)) {
                        _panel.buyTower(DefenseStructureFactory.getDefenceStruct("EnergyTower", _panel.currentCell));
                    }
                    _targetPos = null;
                } else {
                    if (_panel.currentCell.x() > _targetPos.x()) {
                        _panel.currentCell = new Cell(_panel.currentCell.x() - 1, _panel.currentCell.y());
                    } else if (_panel.currentCell.x() < _targetPos.x()) {
                        _panel.currentCell = new Cell(_panel.currentCell.x() + 1, _panel.currentCell.y());
                    } else if (_panel.currentCell.y() > _targetPos.y()) {
                        _panel.currentCell = new Cell(_panel.currentCell.x(), _panel.currentCell.y() - 1);
                    } else if (_panel.currentCell.y() < _targetPos.y()) {
                        _panel.currentCell = new Cell(_panel.currentCell.x(), _panel.currentCell.y() + 1);
                    }

                }

                if (_panel.currentMoney < 25) {
                    _panel.initiateWave();
                }
            }
        }
        return Module.EXIT_SUCCESS;
    }

    public Cell calculatePlacement(TowerDefence game) {
        Random rand = new Random();
        int num = rand.nextInt(_panel.getRoadCell().size());
        Cell roadCell = _panel.getRoadCell().get(num);

        if (!_panel.isntEmpty(new Cell(roadCell.x() + 1, roadCell.y()))
                && !_panel.isRoad(new Cell(roadCell.x() + 1, roadCell.y()))
                && roadCell.x() + 1 < _panel.mapWidth()) {
            return new Cell(roadCell.x() + 1, roadCell.y());
        } else if (!_panel.isntEmpty(new Cell(roadCell.x(), roadCell.y() + 1))
                && !_panel.isRoad(new Cell(roadCell.x(), roadCell.y() + 1))
                && roadCell.y() + 1 < _panel.mapHeight()) {
            return new Cell(roadCell.x(), roadCell.y() + 1);
        } else if (!_panel.isntEmpty(new Cell(roadCell.x() - 1, roadCell.y()))
                && !_panel.isRoad(new Cell(roadCell.x() - 1, roadCell.y()))
                && roadCell.x() - 1 > -1) {
            return new Cell(roadCell.x() - 1, roadCell.y());
        } else if (!_panel.isntEmpty(new Cell(roadCell.x(), roadCell.y() - 1))
                && !_panel.isRoad(new Cell(roadCell.x(), roadCell.y() - 1))
                && roadCell.y() - 1 > -1) {
            return new Cell(roadCell.x(), roadCell.y() - 1);
        }

        return roadCell;
    }

    @Override
    public void unload(TowerDefence game) {
        System.out.println("Module " + this.getClass() + "unload");
    }

}
