package com.awesometuts.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.awesometuts.flappybird.GameMain;

import helpers.Gameinfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Gameinfo.WIDTH;
		config.height = Gameinfo.HEIGHT;

		new LwjglApplication(new GameMain(), config);
	}
}
