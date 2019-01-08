package Platformer;

import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import game_engine_2d.Tile;
import game_engine_2d.GUI.menuMaker;
import game_engine_2d.data_management.DataManager;
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONArray;
import java.util.LinkedList;
import java.util.Random;
import java.io.*; 

public class LevelEditor extends GameScreen {
	menuMaker menuMaker;
	int platform_width = 20;
	int platform_height = 20;
	int levelID = 1;
	GameObject emptyGo;
	
	
	
	
	public int[] pipeColour = new int[3];
	boolean inEditor;
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
		menuMaker.add_menu_item("Quit");
		menuMaker.start();
		this.menuGameObjects.add(menuMaker);
		this.ready = true;
		this.activate();
		inEditor = true;
		pipeColour[0] = 255; pipeColour[1] = 255; pipeColour[2] = 255; 
		EmptyGameObject emptyGo = new EmptyGameObject(parent, parent.width/2, parent.height/2, platform_width, platform_height);
		
		emptyGo.start();
		this.gameManager.addObject(emptyGo);
		this.gameManager.addPlayerGameObjects(emptyGo);
		Camera2D camera = new Camera2D(parent, emptyGo, 99);
		camera.cameraOffset.y = 90;
		this.gameManager.addObject(camera);
	}

	@Override
	public void keyPressed(char key, int keyCode) {
		if (key == '1') {
			// load start screen
			this.swapTo(0);
			inEditor = false;
		}  

		else if(key == PApplet.RIGHT && inEditor) {
			emptyGo.transform.position.x += platform_width;
		}
		else if(key == '2' && inEditor) {
			setColour(255,0,0);
			
		}
		else if(key == '3' && inEditor) {
			 
			setColour(0,255,0);
		}
		else if(key == '4' && inEditor) {
			 
			setColour(0,0,255);
		}
		
		else if (Character.toUpperCase(key) == 'S') {
			
			this.gameManager.dataManager.save_tiles_json(this.gameObjects, "level" + levelID);

		} else if (Character.toUpperCase(key) == 'L') {
			this.load_tile_json();
		}

	}

	public void setColour(int c1, int c2, int c3) {
		pipeColour[0] = c1; pipeColour[1] = c2; pipeColour[2] = c3;
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
		
		parent.println(mouseButton);
		if(mouseButton == 37) {
			parent.println("level edit click");
			
		}
		else if (mouseButton == 39) {
			undoLastPlatform();
		}
		
	}

	
		

	void undoLastPlatform() {
		int lastIndexPos = this.gameObjects.size();
		int lastIndexPos_2 = this.gameBoundingBoxes.size();
		this.gameObjects.remove(lastIndexPos-1);		
		this.gameBoundingBoxes.remove(lastIndexPos_2-1);
	}
	void add_platform(int x, int y) {
		
		
		int gridX = ((x / platform_width) * platform_width) + platform_width/2;
		int gridY = ((y / platform_height) * platform_height) + platform_height/2;
		parent.println("Grid X = " + gridX + ", Grid Y = " + gridY);
		Tile platform = new Tile(parent, gridX, gridY, platform_width, platform_height, pipeColour[0],pipeColour[1],pipeColour[2]);
		platform.start();
		platform.fillColour = parent.color(pipeColour[0], pipeColour[1], pipeColour[2] );
		this.gameObjects.add(platform);
		this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
	}
}