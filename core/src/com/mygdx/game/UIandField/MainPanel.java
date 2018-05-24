/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.UIandField;

import LoaderAndBot.LoaderEngine;
import com.mygdx.game.fieldObjects.Direction;
import com.mygdx.game.fieldObjects.Unit;
import com.mygdx.game.fieldObjects.Wave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.TowerDefence;
import com.mygdx.game.TowerDefence;
import com.mygdx.game.fieldObjects.Shell;
import com.mygdx.game.fieldObjects.DefenseStructureFactory;
import java.util.ArrayList;
import java.util.Iterator;
import com.mygdx.game.fieldObjects.DefenseStructure;

/**
 *
 * @author Admin
 */
public class MainPanel implements Screen {
 
    private TowerDefence game;
    private SpriteBatch batch;
    private GameField map;
    
    public Cell currentCell = new Cell(0, 50);
    public Wave currentWave;
    public int waveCount;
    public int currentMoney;
    public boolean Win = false;
    
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private BitmapFont node15 = null;
    private BitmapFont node20 = null;
    private Texture background;
    private Texture nexus;
    private Texture roadBackground;
    private Texture frameBG;
    private Texture Panel;
    public boolean _bot = false;
 
    private ScrollPane scrollPane;
    private List list;
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private Stage stage;

    private TextButton engage;
    private TextButton exit;
 
    private InputMultiplexer multiplexer = new InputMultiplexer();
    private  MouseProcessor processor = new MouseProcessor();

    private ArrayList<Shell> _shells = new ArrayList();

    public MainPanel(TowerDefence aThis, GameField aMap, boolean bot) {
        super();
        restart(aThis, aMap, bot);
    }
    
    public ArrayList<Cell> getRoadCell() {
       return map.roadCell();
    }
    
    public void initiateWave()
    {
        currentWave = map.waves().get(waveCount);
    }
    
    public int mapHeight() {
       return map.height();
    }
    
    public int mapWidth() {
       return map.width();
    }
    
    public int nexusHP() {
       return map.main().integrity();
    }

     public boolean isRoad(Cell pos) {
       return map.CheckRoad(pos);
    }
     
     public boolean isntEmpty(Cell pos) {
       return map.CheckCell(pos);
    }
    
    @Override
    public void render(float f) {

        if(_bot)
            LoaderEngine._execute.run(game);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 50, map.width() * Cell.Size, map.height() * Cell.Size+50);
        batch.draw(Panel, 0, 0);


        if (map.main().integrity() <= 0) {
            renderText("Your castle burns. Good luck next time!", Gdx.graphics.getWidth() * 4 / 16, 30, 20);
            currentWave = null;
        }

        if (currentWave != null && currentWave.units().isEmpty()) {
            endWave();
        }

        for (Cell cell : map.roadCell()) {
            batch.draw(roadBackground, cell.x() * Cell.Size, cell.y() * Cell.Size+50, Cell.Size, Cell.Size);
        }
        for (Cell cell : map.sideRoadCell()) {
            batch.draw(roadBackground, cell.x() * Cell.Size, cell.y() * Cell.Size+50, Cell.Size, Cell.Size);
        }
        batch.draw(nexus, map.main().position().x() * Cell.Size - 4, map.main().position().y() * Cell.Size + 46, Cell.Size * 1.125f, Cell.Size * 1.125f);
      
        renderTower();
        renderCell();
        if (currentWave != null) {
            renderUnits();
            renderText("Siege still alive: " + currentWave.units().size(), Gdx.graphics.getWidth() * 7 / 16, 30, 20);
        }

        if (_shells.size() != 0) {
            renderBullets();
        }

        //Прочность нексуса
        renderText(map.main().integrity() + "/" + map.main().maxIntegrity(), map.main().position().x() * Cell.Size, map.main().position().y() * Cell.Size + Cell.Size / 4+25, 15);
        //Деньги
        renderText("Money: " + currentMoney, Gdx.graphics.getWidth() * 2 / 17, 30, 20);
        //Количество волн
        renderText("Wave " + (waveCount + 1) + "/" + map.waves().size(), Gdx.graphics.getWidth() * 13 / 16, 30, 20);
        //Победная надпись
        if (Win && map.main().integrity() > 0) {
            renderText("There is noone left to kill..you won!", Gdx.graphics.getWidth() * 4 / 16, 30, 20);
        }

        batch.end();
        stage.act();
        stage.draw();
        System.gc();

    }

    @Override
    public void show() {

    }

    public void showShop(float x, float y) {
        if (Gdx.graphics.getHeight() - y < 150) {
            y -= 150;
        }
        if (Gdx.graphics.getWidth() - x < 200) {
            x -= 200;
        }

        list = new List(skin);
        if (map.CheckCell(currentCell)) {
            list.setItems((Object[]) ListActions);
        } else if (map.CheckRoad(currentCell)) {
            list.setItems((Object[]) DefenseStructureFactory.ListTraps);
        } else {
            list.setItems((Object[]) DefenseStructureFactory.ListTowers);
        }

        list.getSelection().setMultiple(false);
        list.getSelection().setRequired(false);
        list.getSelection().setToggle(true);
        scrollPane = new ScrollPane(list, skin);
        scrollPane.setFlickScroll(false);
        scrollPane.setHeight(150);
        scrollPane.setWidth(200);
        scrollPane.setX(x);
        scrollPane.setY(y);
        stage.addActor(scrollPane);
        scrollPane.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Buttons.RIGHT) {
                    if (scrollPane != null) {
                        scrollPane.remove();
                        Gdx.input.setInputProcessor(multiplexer);
                        return true;
                    }
                }
                return false;
            }
        });

        scrollPane.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                Object obj = list.getSelected();
                if (obj == null) {
                    obj = list.getItems().first();
                }
                if (obj.toString().compareTo("Destroy construction") == 0) {
                    destroyTower();
                } else if (obj.toString().compareTo("MagicTower - 40") == 0) {
                    buyTower(DefenseStructureFactory.getDefenceStruct("MagicTower", currentCell));
                } else if (obj.toString().compareTo("CannonTower - 50") == 0) {
                    buyTower(DefenseStructureFactory.getDefenceStruct("CannonTower", currentCell));
                } else if (obj.toString().compareTo("EnergyTower - 35") == 0) {
                    buyTower(DefenseStructureFactory.getDefenceStruct("EnergyTower", currentCell));
                } else if (obj.toString().compareTo("Tripwire - 20") == 0) {
                    buyTower(DefenseStructureFactory.getDefenceStruct("Wire", currentCell));
                } else if (obj.toString().compareTo("BlastMine - 35" ) == 0) {
                    buyTower(DefenseStructureFactory.getDefenceStruct("BlastMine", currentCell));
                } else if (obj.toString().compareTo("Spikes - 25") == 0) {
                    buyTower(DefenseStructureFactory.getDefenceStruct("Spike", currentCell));
                }

            }
        });

    }

    public void restart(TowerDefence aThis, GameField field, boolean bot) {
        game = aThis;
        map = field;
        stage = new Stage();
        currentMoney = map.moneyOnStart();
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("grass.jpg"));
        roadBackground = new Texture(Gdx.files.internal("road.png"));
        nexus = new Texture(Gdx.files.internal("Nexus.png"));
        Panel = new Texture(Gdx.files.internal("Panel.png"));
        currentWave = null;
        waveCount = 0;
        _bot=bot ;
        Gdx.input.setInputProcessor(multiplexer);
        if (!_bot)
        {
            multiplexer.addProcessor(processor);
        }
        multiplexer.addProcessor(stage);
        createEngageButton();
        createExitButton();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 15;
        node15 = generator.generateFont(parameter);
        parameter.size = 20;
        node20 = generator.generateFont(parameter);

    }


    public void renderText(String str, float x, float y, int size) {
        if (size == 15) {
            node15.draw(batch, str, x, y);
        }
        if (size == 20) {
            node20.draw(batch, str, x, y);
        }

    }
  
  
    public void renderBullets() {

        for (Shell bullet : _shells) {
            bullet.move();
            batch.draw(bullet.texture(), bullet.x(), bullet.y()+50, Cell.Size / 6, Cell.Size / 6);
        }
        Iterator<Shell> iter = _shells.iterator();

        while (iter.hasNext()) {

            Shell bullet = iter.next();

            if (bullet.moveOff()) {
                if (bullet.toEnemy()) {
                    bullet.target().reduсeHealth(bullet.damage());
                } else {
                    map.main().DecreaseIntegrity(bullet.damage());
                }
                iter.remove();
            }
        }

    }
   
    
    public void renderUnits() {
        for (Unit enemy : currentWave.units()) {
            if (enemy.canAttack(map.main().position())) {
                Shell temp = enemy.attack(map.main().position(), map.main().position().x() * Cell.Size + Cell.Size / 2, map.main().position().y() * Cell.Size + Cell.Size / 2+50);
                if (temp != null) {
                    _shells.add(temp);
                }
            } else {
                enemy.move(0f);
            }
            if (enemy.direction().equals(Direction.north())) {
                batch.draw(enemy.texture(), enemy.x(), enemy.y()+50, Cell.Size / 2, Cell.Size / 2, Cell.Size , Cell.Size , 1f, 1f, 180f);
            } else if (enemy.direction().equals(Direction.east())) {
                batch.draw(enemy.texture(), enemy.x(), enemy.y()+50, Cell.Size / 2, Cell.Size / 2, Cell.Size , Cell.Size , 1f, 1f, 1f, true);
            } else if (enemy.direction().equals(Direction.west())) {
                batch.draw(enemy.texture(), enemy.x(), enemy.y()+50, Cell.Size / 2, Cell.Size / 2, Cell.Size , Cell.Size , 1f, 1f, 1f, false);

            } else {
                batch.draw(enemy.texture(), enemy.x(), enemy.y()+50, Cell.Size / 2, Cell.Size / 2, Cell.Size , Cell.Size , 1f, 1f, 0f);
            }

        }
        Iterator<Unit> iter = currentWave.units().iterator();
        while (iter.hasNext()) {

            Unit enemy = iter.next();

            if (enemy.healPoints() == 0) {
                currentMoney += enemy.moneyForKill();
                iter.remove();
            }
        }

    }
    String[] ListActions = {"Destroy construction"};
    

    public void createEngageButton() {
        Skin buttonsSkin = SkinForButton.createBasicSkin();
        engage = new TextButton("Engage", buttonsSkin); // Use the initialized skin
        engage.setBounds(0, 0, 70, 50);
        engage.setPosition(0, 0);
        engage.setTouchable(Touchable.enabled);

        stage.addActor(engage);

        engage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                currentWave = map.waves().get(waveCount);

                return true;
            }
        });
    }

    public void createExitButton() {
      Skin buttonsSkin = SkinForButton.createBasicSkin();
        exit = new TextButton("Exit", buttonsSkin); // Use the initialized skin
        exit.setBounds(0, 0, 70, 50);
        exit.setPosition(Gdx.graphics.getWidth()*15/16, 0);
        exit.setTouchable(Touchable.enabled);

        stage.addActor(exit);

        exit.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                 if(_bot)
                LoaderEngine._execute.unload(game);            
                game.introPanel.restart();
                             game.setScreen(game.introPanel );
                             
                return true;
            }
        });

    }


    public void renderTower() {
        for (DefenseStructure dc : map.defenseConst()) {
            if (currentWave != null) {
                for (Unit enemy : currentWave.units()) {
                    if (dc.canAttack(enemy)) {
                        Shell temp = dc.attack(enemy);
                        if (temp != null) {
                            _shells.add(temp);
                        }
                    }
                }
            }
            batch.draw(dc.texture(), dc.position().x() * Cell.Size, dc.position().y() * Cell.Size+50, Cell.Size, Cell.Size);
        }
        Iterator<DefenseStructure> iter = map.defenseConst().iterator();
        while (iter.hasNext()) {

            DefenseStructure td = iter.next();

            if (td.IsDestroy()) {
                iter.remove();
            }
        }

    }

    public void renderCell() {
        if (Gdx.graphics.getHeight() - currentCell.y() * Cell.Size > 50) {
            if (map.CheckCell(currentCell)) {
                frameBG = new Texture(Gdx.files.internal("first.png"));
            } else {
                frameBG = new Texture(Gdx.files.internal("second.png"));
            }
            batch.draw(frameBG, currentCell.x() * Cell.Size, currentCell.y() * Cell.Size+50, Cell.Size, Cell.Size);
        }
    }

    public void buyTower(DefenseStructure dc) {
        if ((currentMoney - dc.price()) >= 0) {
            map.defenseConst().add(dc);
            currentMoney -= dc.price();
            if (!_bot)
            {
                scrollPane.remove();
                Gdx.input.setInputProcessor(multiplexer);
            }
        }
    }

    public void destroyTower() {
        Iterator<DefenseStructure> iter = map.defenseConst().iterator();
        while (iter.hasNext()) {

            DefenseStructure dc = iter.next();

            if (dc.position().equals(currentCell)) {
                iter.remove();
            }
        }
        scrollPane.remove();
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void endWave() {
        currentWave = null;
        if (waveCount + 1 != map.waves().size()) {
            waveCount++;

        } else {
            Win = true;
        }

    }

   
    public Cell findCell(int x, int y) {
        return new Cell(x / Cell.Size, (y-50) / Cell.Size);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
        roadBackground.dispose();

        frameBG.dispose();

    }

    public class MouseProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
            if (y < Gdx.graphics.getHeight()-50) {
                if (button == Buttons.LEFT) {
                    if (scrollPane != null) {
                        scrollPane.remove();
                    }
                    if (!map.main().position().equals(currentCell)) {
                        showShop(x, Gdx.graphics.getHeight() - (y+50));
                        Gdx.input.setInputProcessor(stage);
                    }
                    return true;
                }
                if (button == Buttons.RIGHT) {
                    if (scrollPane != null) {
                        scrollPane.remove();
                        Gdx.input.setInputProcessor(multiplexer);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int x, int y, int pointer) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {

            return true;
        }

        @Override
        public boolean mouseMoved(int i, int i1) {
            currentCell = findCell(i, Gdx.graphics.getHeight() - i1);
            return true;
        }
    }

}
