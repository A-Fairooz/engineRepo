package Platformer;

import java.util.ArrayList;

import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import game_engine_2d.GUI.menuMaker;
import game_engine_2d.BoundingBox;
import processing.core.PApplet;

public class StartScreen extends GameScreen {
	
	menuMaker menuMaker;

	public StartScreen(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Start Screen";
	}
	
	@Override
	public void start() {
		super.start();
		
		menuMaker = new menuMaker(parent, this.exitScreens);
		menuMaker.start();
		this.gameObjects.add(menuMaker);
		this.ready = true;
		this.activate();
	}
	
	@Override
	public void keyPressed(char key, int keyCode) {
		if(key == '1') {
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
	public void mouseClicked() {
		
	}
}
