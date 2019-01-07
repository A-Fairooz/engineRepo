package game_engine_2d;
import processing.core.*;
import java.util.ArrayList;

import game_engine_2d.BoundingBox;
//import game_engine_2d.BasicSpatialGrid;
import game_engine_2d.data_management.*;
public class GameManager extends ProcessingEntity{
	
	
	
	public int background = 0;
	private ArrayList<GameObject> GUIGameObjects;
	private ArrayList<GameObject> gameObjects;
	private ArrayList<GameObject> playerGameObjects;
	//private ArrayList<GameObject> enemyGameObjects;
	public static ArrayList<BoundingBox> gameBoundingBoxes;
	public static int frameCount = 0;
	public static PVector offset = new PVector(0,0);
	public static PVector screenOffset = new PVector(0,0);
	public static boolean newGame;
	//public static BasicSpatialGrid basicSpatialGrid;
	public DataManager dataManager;
	
	
	public GameManager(PApplet p) {
	super(p);
	this.name = "GameManager";
	
	screenOffset.x = parent.width/2;
	screenOffset.y = parent.height/2;
	this.Init();
	}
	
	public void Init() {
		dataManager = new DataManager(parent);
		gameObjects = new ArrayList<GameObject>();
		playerGameObjects = new ArrayList<GameObject>();
		GUIGameObjects = new ArrayList<GameObject>();
		gameBoundingBoxes = new ArrayList<BoundingBox>();
		//enemyGameObjects = new ArrayList<GameObject>();
	}

	public void addGameBoundingBoxes(GameObject b) {
		gameBoundingBoxes.add(b.transform.NewWorldBoundingBox());
	}
	public void addPlayerGameObjects(GameObject b) {
		playerGameObjects.add(b);
	}
	//public void addEnemyGameObjects(GameObject b) {
	//	enemyGameObjects.add(b);
	//}
	
	public void addObject(GameObject g) {
		gameObjects.add(g);
	}
	public void removeObject(GameObject g) {
		gameObjects.remove(gameObjects.lastIndexOf(g));
	}
	
	public void replaceObjects(ArrayList<GameObject> _gameObjects) {
		gameObjects = _gameObjects;
	}
	public void replaceGUIObjects(ArrayList<GameObject> _gameObjects) {
		GUIGameObjects = _gameObjects;
	}
	public void replacePlayerObjects(ArrayList<GameObject> _gameObjects) {
		playerGameObjects = _gameObjects;
	}
	
	public void replaceBoundingBoxes(ArrayList<BoundingBox> _boundingBoxes) {
		gameBoundingBoxes = _boundingBoxes;
	}
	
	
	public void StartAll() {
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			g.start();
		}
		
	}
	public void UpdateAll() {
		parent.pushMatrix();
		parent.translate(offset.x, offset.y);
		parent.background(background);
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			g.update();
			g.render();			
		}
		frameCount++;
		parent.popMatrix();
		for (int i = 0; i < GUIGameObjects.size(); i++) {
			GameObject g = GUIGameObjects.get(i);
			g.update();
			g.render();
		}
	}
	 
	public void keyPressed(char key, int keyCode) {
		for(int i = 0; i <playerGameObjects.size(); i++) {
			GameObject g = playerGameObjects.get(i);
			g.keyPressed(key, keyCode);
		}
	}
	
	public void keyReleased(char key, int keyCode) {
		for (int i = 0; i < playerGameObjects.size(); i++) {
			GameObject g = playerGameObjects.get(i);
			g.keyReleased(key, keyCode);
			}
		}

	public void mousePressed() {
		for(int i = 0; i < playerGameObjects.size(); i++) {
			GameObject g = playerGameObjects.get(i);
			g.mousePressed();
		}
	}
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		for(int i = 0; i < playerGameObjects.size(); i++) {
			GameObject g = playerGameObjects.get(i);
			g.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
	
	
	
	
	
	
	
	
	
	
	}
