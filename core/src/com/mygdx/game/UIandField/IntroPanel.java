/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.UIandField;

import LoaderAndBot.LoaderEngine;
import com.mygdx.game.TowerDefence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.TowerDefence;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 * Начальный экран
 *
 * @author Admin
 */
public class IntroPanel implements Screen {

   
    private TowerDefence game;
    private SpriteBatch batch;
    private Texture background;
    private Skin buttonsSkin = new Skin();
    private Stage stage;
    private GameField currentMap = null;    
    private ArrayList<GameField> mapsList = new ArrayList<GameField>();
    public String filePath="";
    
    public IntroPanel(TowerDefence aThis) {
        super();
        game = aThis;
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        restart();
        currentMap = mapsList.get(0);
    }

     public void restart() {
        mapsList.clear();
        mapsList.add(GameField.GenerateField());
        currentMap = mapsList.get(0);
        createButtons();
    }
    
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();
    }
    
    /**
     * Кнопки
     */
    private void createButtons() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        buttonsSkin = SkinForButton.createBasicSkin();
        TextButton newGameButton = new TextButton("Start", buttonsSkin);
        newGameButton.sizeBy(Gdx.graphics.getWidth()/4, 0);
        newGameButton.setPosition(0, 0);
        newGameButton.setTouchable(Touchable.enabled);
        stage.addActor(newGameButton);

        TextButton ExitButton = new TextButton("Exit", buttonsSkin);
        ExitButton.sizeBy(Gdx.graphics.getWidth()/4, 0);
        ExitButton.setPosition(Gdx.graphics.getWidth() / 2 , 0);
        ExitButton.setTouchable(Touchable.enabled);
        stage.addActor(ExitButton);

        newGameButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                JFileChooser fileopen = new JFileChooser();    
                fileopen.setCurrentDirectory(new File("..\\build\\classes\\main\\LoaderAndBot"));
               int ret = fileopen.showDialog(null, "Открыть файл");
               filePath="";
               if(ret == JFileChooser.APPROVE_OPTION)
                {
                    filePath =  fileopen.getSelectedFile().getPath();
                      System.out.println(filePath);
                }else
                {
                     MainPanel ls = new MainPanel(game, currentMap, false);
                            game.setScreen(ls);
                            return true;
                }
               
               Gdx.app.postRunnable(new Runnable(){
                   @Override
                   public void run() {
                       if(filePath != ""){
                            String[] arr = new String[1];
                           arr[0] = filePath;
                            MainPanel ls = new MainPanel(game, currentMap, true);
                            LoaderEngine.main(arr,ls);
                            game.setScreen(ls);
                       }    
                   }
                   
               });
               return true;
            }
        });

        ExitButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return true;
            }
        });
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
    }

}
