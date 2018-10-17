package game_engine_2d;
import processing.core.*;
import java.util.ArrayList;

public class GameManager {
	public PApplet parent;
	public int background = 0;
	public GameManager(PApplet p) {
	parent = p;
	gameObjects = new ArrayList<GameObject>();
	}

	private ArrayList<GameObject> gameObjects;
	public void addObject(GameObject g) {
		gameObjects.add(g);
	}
	public void removeObject(GameObject g) {
		gameObjects.remove(gameObjects.lastIndexOf(g));
	}
	
	public void StartAll() {
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			g.start();
		}
	}
	public void UpdateAll() {
		parent.background(background);
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			g.update();
			g.render();
		}
	}
	}
