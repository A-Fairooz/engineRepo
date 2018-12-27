package Platformer;

import java.util.ArrayList;

import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import game_engine_2d.GUI.menuMaker;
import game_engine_2d.BoundingBox;
import processing.core.PApplet;

public class StartScreen extends GameScreen {
	
	menuMaker MenuMaker;

	public StartScreen(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Start Screen";
	}
	
	@Override
	public void start() {
		super.start();
		
		MenuMaker = new menuMaker(parent, this.exitScreens);
		MenuMaker.start();
		this.gameObjects.add(MenuMaker);
		this.ready = true;
		this.activate();
	}
	
	@Override
	public void keyPressed(char key, int keyCode) {
		if(key == '1') {
			parent.println();
			this.swapTo(0);
		}
		if (key == '2') {
			
			this.swapTo(1);
		}
		if(key == '3') {
			parent.println(exitScreens);
			this.swapTo(2);
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
