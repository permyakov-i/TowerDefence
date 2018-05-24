package LoaderAndBot;

import LoaderAndBot.Loader;
import com.mygdx.game.TowerDefence;
import com.mygdx.game.UIandField.MainPanel;
import java.io.File;

public class LoaderEngine {
    
 public static Module _execute = null;
  public static void main(String args[], MainPanel panel) {
    String modulePath = args[0];
    System.out.print("Module path: ");
    System.out.println(modulePath);
    Loader loader = new Loader(modulePath, ClassLoader.getSystemClassLoader());

    String module = modulePath;
        if (module.endsWith(".class")) {
            try {
                String moduleName = module.split("LoaderAndBot\\\\")[1];
               moduleName = moduleName.split("\\.class")[0];
                if (moduleName.equals("Module") == false) {
                    System.out.print("Executing loading module: ");
                    System.out.println(moduleName);

                    Class clazz = loader.loadClass( "LoaderAndBot." + moduleName);
                    _execute = (Module) clazz.newInstance();
                    _execute.load(panel);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
  }
}
