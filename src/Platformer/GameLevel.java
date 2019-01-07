package Platformer;


import game_engine_2d.BaseLauncher;
import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;

import game_engine_2d.GameScreen;
import processing.core.PImage;
import game_engine_2d.GUI.menuMaker;
import game_engine_2d.Tile;
import game_engine_2d.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import sun.applet.Main;


public class GameLevel extends GameScreen{

	menuMaker MenuMaker;
	Main mainc;
	PImage testImage;
	BaseLauncher basel;
	
	
	public GameLevel(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Game Level 1";
	}
	
	private DataManager dataManager;
	
	
	
	
	//@Override
	public void start() {
		if(!this.ready) {
		
		super.start();
		
		MenuMaker = new menuMaker(parent,this.exitScreens);
		
		MenuMaker.start();
		this.menuGameObjects.add(MenuMaker);
		
		loadPlayer();
		parent.println(this.exitScreen);
		if(gameManager.newGame == false) {

			this.load_tile_json();
		}
		
		this.ready = true;
		}
		this.activate();
		
	}
	
	
	public void loadPlayer() {

		Player player = new Player(parent, parent.width / 2, parent.height / 2, 60, 60);
		player.start();
		this.gameManager.addObject(player);
		this.gameManager.addPlayerGameObjects(player);
		Camera2D camera = new Camera2D(parent, player, 99);
		camera.cameraOffset.y = 90;
		this.gameManager.addObject(camera);
	}
	
	
	
	
	
	@Override
	public void keyPressed(char key, int keyCode) {
		if(key == '1') {
			this.swapTo(0);
			
			gameManager.screenOffset.x = 0;
			gameManager.screenOffset.y = 0;
		}
		
		if(key == 'r' || key == 'R') {
			
			
			reload();
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
 	
 	public void reload() {
 		
 		
 		preset_tiles();
 		loadPlayer();
 		
 	}
	
 	public void preset_tiles() {
 		
 		
 		
		int th = parent.height /30;
		int tw = parent.width / 30;
		
		int wbHeightWidth = 400;
		
		Tile platform;
 		//bottom
 				for (int i = 0; i < 30; i++) {
 					platform = new Tile(parent, i * tw , parent.height , tw, th,255,255,255);
 					platform.start();
 					platform.strokeColour = parent.color(0, 200, 200);
 					platform.fillColour = parent.color(0, 200, 200);
 					this.gameManager.addObject(platform);
 					this.gameManager.addGameBoundingBoxes(platform);
 				}

 				// left
 				for (int i = 0; i < wbHeightWidth; i++) {
 					platform = new Tile(parent, 0, parent.height  - (th) * i, tw, th,255,255,255);
 					platform.start();
 					platform.strokeColour = parent.color(0, 200, 200);
 					platform.fillColour = parent.color(0, 200, 200);
 					this.gameManager.addObject(platform);
 					this.gameManager.addGameBoundingBoxes(platform);
 				}
 				
 				
 				//PImage image = new PImage()
 				
 				
 				// right
 				for (int i = 0; i < wbHeightWidth; i++) {
 					platform = new Tile(parent, parent.width, parent.height - (th) * i, tw, th,255,255,255);
 					platform.start();
 					platform.strokeColour = parent.color(0, 200, 200);
 					platform.fillColour = parent.color(0, 200, 200);
 					this.gameManager.addObject(platform);
 					this.gameManager.addGameBoundingBoxes(platform);
 					
 				}
 	}
 	
	public void random_tiles() {
		
		int platforms = 500;
		Tile platform;
		int tw = 50;
		int th = 20;
		int numPlatformsX = 2 * parent.width / tw;
		int numPlatformsY = 2 * parent.height / th;
		for (int i = 0; i < platforms; i++) {
			int x = (int) parent.random(0, numPlatformsX) * tw;
			int y = (int) parent.random(-numPlatformsY, numPlatformsY) * th;
			platform = new Tile(parent, x, y, tw, th,255,255,255);
			platform.start();
			this.gameObjects.add(platform);
			this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
