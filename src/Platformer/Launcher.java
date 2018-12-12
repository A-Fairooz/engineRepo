package Platformer;

import game_engine_2d.*;
import processing.core.PApplet;
import java.util.ArrayList;
import game_engine_2d.data_management.*;


public class Launcher extends BaseLauncher {
	public boolean started = false;
	int waiting = 0;
	
	DataManager dataManager;
	GameScreen activeScreen;
	ArrayList<GameScreen> screens = new ArrayList<GameScreen>();
	
	public Launcher(PApplet p) {
		super(p);
		StartGame();
	}

	public void keyPressed(char key, int keyCode) {
		super.keyPressed(key, keyCode);
		activeScreen.keyPressed(key, keyCode);
	}

	public void keyReleased(char key, int keyCode) {
		super.keyReleased(key, keyCode);
		activeScreen.keyReleased(key, keyCode);

	}



	public void StartGame() {
		StartScreen startScreen = new StartScreen(parent, this.gameManager);
		screens.add(startScreen);
		GameLevel gameLevel = new GameLevel(parent, this.gameManager);
		screens.add(gameLevel);
		gameLevel.start();
		activeScreen = startScreen;
		started = true;
		startScreen.exitScreensAdd(gameLevel);
		this.gameManager.StartAll();
	
	}

	
		public void UpdateAll() {
			super.UpdateAll();
			
			if(activeScreen.swap_screen != null) {
				activeScreen = activeScreen.swap_screen;
				activeScreen.activated = false;
				activeScreen.swap_screen =null;
		}
			if(!activeScreen.activated) {
				activeScreen.start();
			}
		}
}
