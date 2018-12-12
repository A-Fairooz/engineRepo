package Platformer;

import java.util.ArrayList;
import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import game_engine_2d.*;
import game_engine_2d.Tile;
import game_engine_2d.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;



public class GameLevel extends GameScreen{

	public GameLevel(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Game Level 1";
	}
	
	private DataManager dataManager;
	
	
	@Override
	public void start() {
		super.start();
		
		
		Player player = new Player(parent, parent.width / 2, parent.height / 2, 60, 60);
		player.start();
		this.gameManager.addObject(player);
		this.gameManager.addPlayerGameObjects(player);
		Camera2D camera = new Camera2D(parent, player, 99);
		camera.cameraOffset.y = 90;
		this.gameManager.addObject(camera);
		random_tiles();
		preset_tiles();
		this.ready = true;
		this.activate();
	}
	
	private void tile_json() {
		dataManager = new DataManager(parent);
		dataManager.load_data();
		JSONArray tiles = dataManager.game_data.getJSONArray("tiles");
		for(int i = 0; i < tiles.size(); i++) {
			JSONObject tile = tiles.getJSONObject(i);
			int x = tile.getInt("x");
			int y = tile.getInt("y");
			int tw = tile.getInt("w");
			int th = tile.getInt("h");
			
			Tile platform = new Tile(parent, x, y, tw, th, 255);
			platform.start();
			this.gameObjects.add(platform);
			this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
		}
	}
	
	@Override
	public void keyPressed(char key, int keyCode) {
	
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
	
	
 	public void preset_tiles() {
 		
 		
 		
		int th = parent.height /30;
		int tw = parent.width / 30;
		
		int wbHeightWidth = 400;
		
		Tile platform;
 		//bottom
 				for (int i = 0; i < 30; i++) {
 					platform = new Tile(parent, i * tw , parent.height , tw, th,255);
 					platform.start();
 					platform.strokeColour = parent.color(0, 200, 200);
 					platform.fillColour = parent.color(0, 200, 200);
 					this.gameManager.addObject(platform);
 					this.gameManager.addGameBoundingBoxes(platform);
 				}

 				// left
 				for (int i = 0; i < wbHeightWidth; i++) {
 					platform = new Tile(parent, 0, parent.height  - (th) * i, tw, th, 255);
 					platform.start();
 					platform.strokeColour = parent.color(0, 200, 200);
 					platform.fillColour = parent.color(0, 200, 200);
 					this.gameManager.addObject(platform);
 					this.gameManager.addGameBoundingBoxes(platform);
 				}
 				
 				
 				
 				
 				
 				// right
 				for (int i = 0; i < wbHeightWidth; i++) {
 					platform = new Tile(parent, parent.width, parent.height - (th) * i, tw, th, 255);
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
			platform = new Tile(parent, x, y, tw, th, 255);
			platform.start();
			this.gameObjects.add(platform);
			this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
