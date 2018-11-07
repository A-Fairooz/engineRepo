package Platformer;

import game_engine_2d.*;
import processing.core.PApplet;


public class Launcher extends BaseLauncher {

	public Launcher(PApplet p) {
		super(p);
	}

	public void StartGame() {
		super.StartGame();
		Player player = new Player(parent,parent.width/2, parent.height/2,60,60);
		//Enemy enemy = new Enemy(parent,parent.width/2, parent.height/2,60,60);
		player.start();
		//enemy.start();
		this.gameManager.addObject(player);
		this.gameManager.addPlayerGameObjects(player);
		//this.gameManager.addObject(enemy);
		//this.gameManager.addEnemyGameObjects(enemy);
		
		Camera2D camera = new Camera2D(parent,player,99);
		camera.cameraOffset.y = 90;
		this.gameManager.addObject(camera);
		
		int platforms = 20;
		Tile platform;
		for(int i = 0; i < platforms; i ++) {
			platform = new Tile(parent, 70+ i * 55, parent.height-50,50,20);
			platform.start();
			this.gameManager.addObject(platform);
			this.gameManager.addGameBoundingBoxes(platform);
		}
		
		
		
	      
		
	}
		public void UpdateAll() {
			super.UpdateAll();
		}
	
}
