package Game_FlappyBird;
import java.util.Random;
import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;
import game_engine_2d.GameScreen;
import game_engine_2d.GUI.MenuMaker;
import game_engine_2d.Tile;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class FlappyBird extends GameScreen{

	//Variables
	MenuMaker MenuMaker;
	boolean isReset;
	Player player;
	Camera2D cam;
	public FlappyBird(PApplet p, GameManager _gameManager) 
	{
		super(p, _gameManager);
		this.name = "Flappy Bird";		
	}

	@Override
	public void start() 
	{
		if(this.ready) 
		{
			cam.reset();
			player.reset();
		}else {
		super.start();
		MenuMaker = new MenuMaker(parent,this.exitScreens);		
		MenuMaker.start();
		this.menuGameObjects.add(MenuMaker);		
		isReset = false;
		player = new Player(parent, 20, parent.height, 60, 60);		
		player.start();
		this.gm.addObject(player);
		this.gm.addPlayerGameObjects(player);
		
		cam = new Camera2D(parent, player, 0);
		cam.cameraOffset.y = 0;
		this.gm.addObject(cam);			
		if(GameManager.newGame == true) 
			{			
			for(int i = 0; i < 100; i++) 
				{
				//Randomiser randomises the height of the pipes
				Random rnd = new Random();
				int rando = (rnd.nextInt(10));
				//pipe size, pipe x distance, flipped or not, Colour RGB
				drawPipe(20,i * 20, false,rando,0,255,0);
				drawPipe(20,i * 20, true, rando,0,255,0);
				}			
			}	
		this.ready = true;		
		}
		this.activate();		
	}
	
	
	
	@Override
	public void keyPressed(char key, int keyCode) 
	{
		//check for users Key Inputs
		if(key == '1') 
		{
			this.swapTo(0);
			
		}
		if(isReset) {
			isReset = false;
			this.swapTo(0);
		}
	}
 	@Override
 	public void activate() {
 		super.activate();
 		if(player != null) {
 			player.start();
 		}
 	}
 	@Override
	public void keyReleased(char key, int keyCode) {}
 	@Override
	public void mousePressed() {}
 	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}
	
	
 	
 	public void drawPipe(int pipeSize, int pipePosX, boolean isFlipped, int randomHeight, int Colour_1, int Colour_2, int Colour_3) 
 	{
 		//Local variables
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
			
				pipe = new Tile(parent, pw *3 + parent.width/2 + xOffset, parent.height - pipeDist +heightRandom, pw* 7, ph, col_1,col_2,col_3);
				pipe.start();
				this.gm.addObject(pipe);
				this.gm.addGameBoundingBoxes(pipe);
				
			
			for(int i = 0; i < 5; i++) {
				pipe = new Tile(parent, i * pipeBottomW + pipeBottomW + parent.width/2 + xOffset, parent.height - pipeBottomH/2 - pipeDist+heightRandom, pipeBottomW, pipeBottomH, col_1,col_2,col_3);
				pipe.start();
				this.gm.addObject(pipe);
				this.gm.addGameBoundingBoxes(pipe);
			}
		}
		
		else if (!isFlipped) {
			
				pipe = new Tile(parent, pw *3+ parent.width/2 + xOffset, parent.height+heightRandom, pw* 7, ph, col_1,col_2,col_3);
				pipe.start();
				this.gm.addObject(pipe);
				this.gm.addGameBoundingBoxes(pipe);
			
			for(int i = 0; i < 5; i++) {
				pipe = new Tile(parent, i * pipeBottomW + pipeBottomW + parent.width/2 + xOffset, parent.height +pipeBottomH /2 +heightRandom, pipeBottomW, pipeBottomH, col_1,col_2,col_3);
				pipe.start();
				this.gm.addObject(pipe);
				this.gm.addGameBoundingBoxes(pipe);
			}
		}
		
				
 	} 	
	}
	
	