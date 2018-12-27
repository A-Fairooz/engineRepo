package Platformer;

import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;
import game_engine_2d.GameScreen;
import game_engine_2d.Tile;
import game_engine_2d.GUI.menuMaker;
import game_engine_2d.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;

public class LevelEditor extends GameScreen {
	menuMaker menuMaker;
	int platform_width = 50;
	int platform_height = 20;
	int levelID = 1;

	public LevelEditor(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Level Editor";
	}

	@Override
	public void start() {
		super.start();
		menuMaker = new menuMaker(parent, this.exitScreens);
		menuMaker.add_menu_item("A - add platform");
		menuMaker.add_menu_item("S - save level");
		menuMaker.add_menu_item("L - load level");
		menuMaker.start();
		this.menuGameObjects.add(menuMaker);
		this.ready = true;
		this.activate();

	}

	@Override
	public void keyPressed(char key, int keyCode) {
		if (key == '1') {
			// load start screen
			this.swapTo(0);
		} else if (Character.toUpperCase(key) == 'S') {
			// save tiles as json array
			// the levelID is 1, todo - make list of levels to save/load
			this.gameManager.dataManager.save_tiles_json(this.gameObjects, "level" + levelID);

		} else if (Character.toUpperCase(key) == 'L') {
			this.load_tile_json();
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
		// add platform
		parent.println("level edit click");
		add_platform(mouseX, mouseY);
	}

	void add_platform(int x, int y) {
		
		int gridX = (x / platform_width) * platform_width;
		int gridY = (y / platform_height) * platform_height;
		Tile platform = new Tile(parent, gridX, gridY, platform_width, platform_height,255);
		platform.start();
		this.gameObjects.add(platform);
		this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
	}
}