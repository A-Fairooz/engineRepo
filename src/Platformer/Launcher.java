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
		int platforms = 20;
		Tile platform;
		for(int i = 0; i < platforms; i ++) {
			platform = new Tile(parent, 70+ i * 55, parent.height-50,50,20);
			platform.start();
			this.gameManager.addObject(platform);
			this.gameManager.addGameBoundingBoxes(platform);
		}
		
		this.gameManager.StartAll();
		this.started = true;
}
	
		public void UpdateAll() {
			super.UpdateAll();
		}
	
}
