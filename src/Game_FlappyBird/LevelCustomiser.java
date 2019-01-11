package Game_FlappyBird;

import game_engine_2d.Camera2D;
import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import game_engine_2d.Tile;
import game_engine_2d.GUI.MenuMaker;
//import game_engine_2d.data_management.DataManager;
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONArray;
import java.util.LinkedList;
import java.util.Random;
import java.io.*; 

public class LevelCustomiser extends GameScreen {
	
	//Variables
	MenuMaker menuMaker;
	GameObject emptyGo;
	int platform_width = 20;
	int platform_height = 20;
	int levelID = 1;	
	public int[] pipeColour = new int[3];
	public int[] bgC = new int[3];
	boolean inEditor;
	
	
	public LevelCustomiser(PApplet p, GameManager _gameManager) 
	{
		super(p, _gameManager);
		this.name = "Level Customiser";
	}

	@Override
	public void start() 
	{
		super.start();
		menuMaker = new MenuMaker(parent, this.exitScreens);
		menuMaker.add_menu_item("Change Colour to Red");
		menuMaker.add_menu_item("Change Colour to Green");
		menuMaker.add_menu_item("Change Colour to Blue");
		menuMaker.add_menu_item("Quit");
		menuMaker.start();
		this.menuGameObjects.add(menuMaker);
		this.ready = true;
		this.activate();
		inEditor = true;
		pipeColour[0] = 255; pipeColour[1] = 255; pipeColour[2] = 255; 
		EmptyGameObject emptyGo = new EmptyGameObject(parent, parent.width/2, parent.height/2, platform_width, platform_height);
		
		emptyGo.start();
		this.gm.addObject(emptyGo);
		this.gm.addPlayerGameObjects(emptyGo);
		Camera2D camera = new Camera2D(parent, emptyGo, 99);
		camera.cameraOffset.y = 0;
		this.gm.addObject(camera);
	}

	@Override
	public void keyPressed(char key, int keyCode)
	{
		if (key == '1') {
			//Swap to the starting screen
			this.swapTo(0);
			inEditor = false;
		}  

		else if(key == PApplet.RIGHT && inEditor)
		{
			emptyGo.transform.position.x += platform_width;
		}
		else if(key == '2' && inEditor) 
		{
			//change the platform colour to red
			
			this.gm.background_1 = 255;
			this.gm.background_2 = 104;
			this.gm.background_3 = 96;
			
		}
		else if(key == '3' && inEditor) 
		{
			//change the platform colour to green
			setColour(0,255,0,false);
			this.gm.background_1 = 28;
			this.gm.background_2 = 188;
			this.gm.background_3 = 36;
		}
		else if(key == '4' && inEditor) 
		{
			//change the platform colour to blue
			//parent.image(gm.skyImage, parent.height/2, 0);
			setColour(0,0,255,false);
			this.gm.background_1 = 96;
			this.gm.background_2 = 218;
			this.gm.background_3 = 255;
			
		}
		else if(key == '5' && inEditor) 
		{
		parent.exit();
		}
	}

	public void setColour(int c1, int c2, int c3, boolean isPipe) 
	{
		if(isPipe) 
		{
			pipeColour[0] = c1; pipeColour[1] = c2; pipeColour[2] = c3;
		}
		else if(!isPipe)
		{
			bgC[0] = c1; bgC[1] = c2; bgC[2] = c3;
		}
			}

	@Override
	public void keyReleased(char key, int keyCode){}
	@Override
	public void mousePressed() {}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) 
	{
		//Add a platform on click at the position of the mouse			
		if(mouseButton == 37) 
		{
			add_platform(mouseX, mouseY);			
		}
		//Undo the last platform placed
		else if (mouseButton == 39) 
		{
			undoLastPlatform();
		}		
	}		

	void undoLastPlatform() 
	{
		int lastIndexPos = this.gameObjects.size();
		int lastIndexPos_2 = this.gameBoundingBoxes.size();
		this.gameObjects.remove(lastIndexPos-1);		
		this.gameBoundingBoxes.remove(lastIndexPos_2-1);
	}
	void add_platform(int x, int y) 
	{				
		int gridX = ((x / platform_width) * platform_width) + platform_width/2;
		int gridY = ((y / platform_height) * platform_height) + platform_height/2;
		Tile platform = new Tile(parent, gridX, gridY, platform_width, platform_height, pipeColour[0],pipeColour[1],pipeColour[2]);
		platform.start();
		platform.fillColour = parent.color(pipeColour[0], pipeColour[1], pipeColour[2]);
		this.gameObjects.add(platform);
		this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
	}
}