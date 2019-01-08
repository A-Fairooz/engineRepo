package Platformer;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import game_engine_2d.GUI.menuMaker;
import game_engine_2d.BoundingBox;
import processing.core.PApplet;

public class StartScreen extends GameScreen {
	
	menuMaker MenuMaker;
	GameLevel gameLevel;
	public StartScreen(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Start Screen";
	}
	
	@Override
	public void start() {
		super.start();
		
		MenuMaker = new menuMaker(parent, this.exitScreens);
		MenuMaker.add_menu_item("Quit");
		MenuMaker.start();
		this.gameObjects.add(MenuMaker);
		this.ready = true;
		this.activate();
	}
	 
	@Override
	public void keyPressed(char key, int keyCode) {
		if(key == '4') {
			
			gameManager.newGame = false;
			
			parent.println(gameManager.newGame);
			this.swapTo(0);
		}
		if (key == '2') {
			
			parent.println(gameManager.newGame);
			this.swapTo(1);
		}
		if(key == '3') {
			parent.println(gameManager.newGame);
			parent.println(exitScreens);
			parent.exit();
		}
		if(key == '1') {	 
			gameManager.newGame = true;
			
			parent.println(gameManager.newGame);
			
			
			this.swapTo(0);
			
		}
	}
	
	@Override
	public void keyReleased(char key, int keyCode) {
		
	}
	
	@Override
	public void mousePressed() {
		
	}
	
	@Override 
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		
	}
}
