/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoaderAndBot;

import com.mygdx.game.TowerDefence;
import com.mygdx.game.UIandField.MainPanel;

public interface Module {

  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = 1;

  public void load(MainPanel panel);
  public int run(TowerDefence game);
  public void unload(TowerDefence game);
}
