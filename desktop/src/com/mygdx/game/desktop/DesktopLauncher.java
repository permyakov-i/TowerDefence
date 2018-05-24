package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TowerDefence;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "TowerDefence";
                 config.width = 1024;
                config.height = 562;
                config.resizable=false;
		new LwjglApplication(new TowerDefence(), config);

	}
}
