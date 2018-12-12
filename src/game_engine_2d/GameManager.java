package game_engine_2d;
import processing.core.*;
import java.util.ArrayList;

import game_engine_2d.BoundingBox;
import game_engine_2d.BasicSpatialGrid;

public class GameManager extends ProcessingEntity{
	
	
	
	public int background = 0;
	private ArrayList<GameObject> gameObjects;
	private ArrayList<GameObject> playerGameObjects;
	//private ArrayList<GameObject> enemyGameObjects;
	public static ArrayList<BoundingBox> gameBoundingBoxes;
	public static int frameCount = 0;
	public static PVector offset = new PVector(0,0);
	public static PVector screenOffset = new PVector(0,0);
	public static BasicSpatialGrid basicSpatialGrid;
	
	
	public GameManager(PApplet p) {
	super(p);
	this.name = "GameManager";
	
	screenOffset.x = parent.width/2;
	screenOffset.y = parent.height/2;
	this.Init();
	}
	
	public void Init() {
		gameObjects = new ArrayList<GameObject>();
		playerGameObjects = new ArrayList<GameObject>();
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
		basicSpatialGrid = new BasicSpatialGrid(parent.height, 1);
		parent.println("spacial grid size min:" + basicSpatialGrid.globalMin.ToString() + ", max:" + basicSpatialGrid.globalMax.ToString());
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
	}
