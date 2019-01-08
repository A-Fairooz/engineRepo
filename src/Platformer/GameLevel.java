package Platformer;


import java.util.Random;

import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;

import game_engine_2d.GameScreen;

import game_engine_2d.GUI.menuMaker;
import game_engine_2d.Tile;
import game_engine_2d.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;



public class GameLevel extends GameScreen{

	menuMaker MenuMaker;
	boolean isReset;
	
	public GameLevel(PApplet p, GameManager _gameManager) {
		super(p, _gameManager);
		this.name = "Flappy Bird";
		
	}
	
	private DataManager dataManager;
	
	
	@Override
	public void start() {
		if(!this.ready) {
		super.start();
		MenuMaker = new menuMaker(parent,this.exitScreens);		
		MenuMaker.start();
		this.menuGameObjects.add(MenuMaker);
		isReset = false;
		Player player = new Player(parent, 20, parent.height/2, 60, 60);
		parent.println(player.transform.position);
		player.start();
		this.gameManager.addObject(player);
		this.gameManager.addPlayerGameObjects(player);
		Camera2D camera = new Camera2D(parent, player, 99);
		camera.cameraOffset.y = 90;
		this.gameManager.addObject(camera);
		
		parent.println(player.transform.position);
		if(gameManager.newGame == true) {
			
			for(int i = 0; i < 8; i++) {
				Random rnd = new Random();
				int rando = (rnd.nextInt(10));
				//pipe size, pipe x distance, flipped or not
				drawPipe(20,i * 20, false,rando,255,255,255);
				drawPipe(20,i * 20, true, rando,255,255,255);
			}
			
			
		}
		
	
		this.ready = true;
		
		}
		this.activate();
		
	}
	
	
	
	
	
	@Override
	public void keyPressed(char key, int keyCode) {
		if(key == '1') {
			this.swapTo(0);
			
			gameManager.screenOffset.x = 0;
			gameManager.screenOffset.y = 0;
		}
		if(isReset) {
			isReset = false;
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
	
	
 	
 	public void drawPipe(int pipeSize, int pipePosX, boolean isFlipped, int randomHeight, int Colour_1, int Colour_2, int Colour_3) {
 		 
		int ph = pipeSize;
		int pw = pipeSize;
		int pipeBottomW = pw;
		int pipeBottomH = pw *40;
		int xOffset = pipePosX * pw;
		int pipeDist = pw * 10;
		int heightRandom = randomHeight *pw;
		int col_1 = Colour_1;
		int col_2 = Colour_2;
		int col_3 = Colour_3;
		
		Tile pipe;
		if(isFlipped) {
			for(int i = 0; i < 7; i++) {
				pipe = new Tile(parent, i * pw + parent.width/2 + xOffset, parent.height - pipeDist +heightRandom, pw, ph, col_1,col_2,col_3);
				pipe.start();
				this.gameManager.addObject(pipe);
				this.gameManager.addGameBoundingBoxes(pipe);
			}
			for(int i = 0; i < 5; i++) {
				pipe = new Tile(parent, i * pipeBottomW + pipeBottomW + parent.width/2 + xOffset, parent.height - pipeBottomH/2 - pipeDist+heightRandom, pipeBottomW, pipeBottomH, col_1,col_2,col_3);
				pipe.start();
				this.gameManager.addObject(pipe);
				this.gameManager.addGameBoundingBoxes(pipe);
			}
		}
		
		else if (!isFlipped) {
			for(int i = 0; i < 7; i++) {
				pipe = new Tile(parent, i * pw + parent.width/2 + xOffset, parent.height+heightRandom, pw, ph, col_1,col_2,col_3);
				pipe.start();
				this.gameManager.addObject(pipe);
				this.gameManager.addGameBoundingBoxes(pipe);
			}
			for(int i = 0; i < 5; i++) {
				pipe = new Tile(parent, i * pipeBottomW + pipeBottomW + parent.width/2 + xOffset, parent.height +pipeBottomH /2 +heightRandom, pipeBottomW, pipeBottomH, col_1,col_2,col_3);
				pipe.start();
				this.gameManager.addObject(pipe);
				this.gameManager.addGameBoundingBoxes(pipe);
			}
		}
		
				
 	}
 	
 	public void preset_tiles() {
 		
 		
 		
		int th = parent.height /30;
		int tw = parent.width / 30;
		
		int ph = 10 ;//parent.height / 5;
		int pw = 10;//parent.width / 20;
		
		int wbHeightWidth = 400;
		
		Tile platform;
		Tile pipe;
		
		
				for(int i = 0; i < 7; i++) {
					pipe = new Tile(parent, i * pw + parent.width/2, parent.height, pw, ph, 0,255,0);
					pipe.start();
					this.gameManager.addObject(pipe);
 					this.gameManager.addGameBoundingBoxes(pipe);
				}
				for(int i = 0; i < 5; i++) {
					pipe = new Tile(parent, i * pw + pw + parent.width/2, parent.height + pw*3, pw, ph*5, 0,255,0);
					pipe.start();
					this.gameManager.addObject(pipe);
 					this.gameManager.addGameBoundingBoxes(pipe);
				}
		
		/*
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
 				
 				
 				
 				
 				
 				// right
 				for (int i = 0; i < wbHeightWidth; i++) {
 					platform = new Tile(parent, parent.width, parent.height - (th) * i, tw, th,255,255,255);
 					platform.start();
 					platform.strokeColour = parent.color(0, 200, 200);
 					platform.fillColour = parent.color(0, 200, 200);
 					this.gameManager.addObject(platform);
 					this.gameManager.addGameBoundingBoxes(platform);
 					
 				}
 				*/
 	}
 	
	
	}
	
	