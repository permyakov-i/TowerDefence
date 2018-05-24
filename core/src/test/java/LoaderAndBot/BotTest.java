/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.LoaderAndBot;

import LoaderAndBot.Bot;
import com.mygdx.game.TowerDefence;
import com.mygdx.game.UIandField.Cell;
import com.mygdx.game.UIandField.MainPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class BotTest {
    
    public BotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changePosition method, of class Bot.
     */
    @org.junit.Test
    public void testChangePosition() {
        System.out.println("changePosition");
        Bot instance = new Bot();
        Cell currentCell = new Cell(0,0);
        Cell targetCell = new Cell(1,1);
        Cell example = new Cell(0,0);
        Cell result = instance.changePosition(currentCell,targetCell);
        assertFalse(example.equals(result));
    }
    
      /**
     * Test of changePosition method, of class Bot.
     */
    @org.junit.Test
    public void testChangePositionFail() {
     System.out.println("changePosition");
        Bot instance = new Bot();
        Cell currentCell = new Cell(0,0);
        Cell targetCell = new Cell(1,1);
        Cell example = new Cell(1,1);
        Cell result = instance.changePosition(currentCell,targetCell);
        assertFalse(example.equals(result));
    }


    
}
