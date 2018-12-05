package Platformer;

import game_engine_2d.*;
import processing.core.PApplet;


public class Launcher extends BaseLauncher {
	public boolean started = false;
	int waiting = 0;
	
	public Launcher(PApplet p) {
		super(p);
	}

	public void keyPressed(char key, int keyCode) {
		super.keyPressed(key, keyCode);
		if (key == 'b' || key == 'B') {
			this.ReLoad();
		}
	}

	public void keyReleased(char key, int keyCode) {
		super.keyReleased(key, keyCode);

	}

	public void ReLoad() {
		this.gameManager.Init();
		TestRestart();

	}

	public void StartGame() {
		if (this.started)
			return;
		super.StartGame();

		if (waiting < 90) {
			parent.background(100);
			parent.text("Initialising!", parent.width / 2, parent.height / 2);
			waiting++;
			return;
		}

		TestRestart();
	}

	public void TestRestart() {
		Player player = new Player(parent, parent.width / 2, parent.height / 2, 60, 60);
		player.start();
		this.gameManager.addObject(player);
		this.gameManager.addPlayerGameObjects(player);
		Camera2D camera = new Camera2D(parent, player, 99);
		camera.cameraOffset.y = 90;
		this.gameManager.addObject(camera);
		int platforms = 100;
		int th = parent.height /30;
		int tw = parent.width / 30;
		int numPlatformsX = 2* parent.width / tw;
		int numPlatformsY = 2 * parent.height / th;
		int wbHeightWidth = 400;
		
		Tile platform;
		/*
		for (int i = 0; i < platforms; i++) {
		int x = (int) parent.random(0, numPlatformsX) * tw;
		int y = (int) parent.random(-numPlatformsY, numPlatformsY) * th;
		platform = new Tile(parent, x, y, tw, th, parent.random(0, 255),parent.random(0, 255),parent.random(0, 255),parent.random(255));
		platform.start();
		this.gameManager.addObject(platform);
		this.gameManager.addGameBoundingBoxes(platform);
		}
		*/
		
		//bottom
		for (int i = 0; i < wbHeightWidth; i++) {
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
		
		
		
		
		/*
		// right
		for (int i = 0; i < 200; i++) {
			platform = new Tile(parent, parent.width, (-parent.height) + (th) * i, tw, th, parent.random(150, 255),parent.random(150, 255),parent.random(150, 255),255);
			platform.start();
			platform.strokeColour = parent.color(0, 200, 200);
			platform.fillColour = parent.color(0, 200, 200);
			this.gameManager.addObject(platform);
			this.gameManager.addGameBoundingBoxes(platform);
			
		}
		*/

		
		
		
		this.gameManager.StartAll();
		this.started = true;
}
	
		public void UpdateAll() {
			super.UpdateAll();
		}
	
}
