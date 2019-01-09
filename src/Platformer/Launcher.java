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
	
	
	public Launcher(PApplet p) 
	{
		super(p);
		StartGame();
	}

	public void keyPressed(char key, int keyCode) 
	{
		super.keyPressed(key, keyCode);
		activeScreen.keyPressed(key, keyCode); 
	}

	public void keyReleased(char key, int keyCode)
	{
		super.keyReleased(key, keyCode);
		activeScreen.keyReleased(key, keyCode);

	}
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) 
	{
		super.mouseClicked(mouseX,mouseY,mouseButton);
		activeScreen.mouseClicked(mouseX,mouseY,mouseButton);
	}

	public void StartGame()
	{
		StartScreen startScreen = new StartScreen(parent, this.gameManager);
		FlappyBird gameLevel = new FlappyBird(parent, this.gameManager);
		LevelCustomiser levelCustomiser = new LevelCustomiser(parent, this.gameManager);
		activeScreen = startScreen;
		started = true;	
		startScreen.exitScreensAdd(gameLevel);		
		startScreen.exitScreensAdd(levelCustomiser);
		levelCustomiser.exitScreensAdd(startScreen);
		gameLevel.exitScreensAdd(startScreen);	
		this.gameManager.StartAll();
	
	}

	
	public void UpdateAll() 
		{
			super.UpdateAll();			
			if(activeScreen.swap_screen != null) 
			{
				activeScreen = activeScreen.swap_screen;
				activeScreen.activated = false;
				activeScreen.swap_screen =null;
			}
			if(!activeScreen.activated) 
			{
				activeScreen.start();
			}
		}
}
