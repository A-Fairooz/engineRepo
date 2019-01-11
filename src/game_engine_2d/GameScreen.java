package game_engine_2d;

import java.util.ArrayList;
import game_engine_2d.BoundingBox;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public abstract class GameScreen extends ProcessingEntity{
	
	
	protected int exitScreen = -1;
	protected ArrayList<GameObject> gameObjects;
	protected ArrayList<GameObject> playerGameObjects;
	protected ArrayList<GameScreen> exitScreens;
	protected ArrayList<GameObject> menuGameObjects;
	public GameScreen swap_screen = null;
	protected ArrayList<BoundingBox> gameBoundingBoxes;
	protected GameManager gm;
	protected boolean ready = false;
	public boolean activated = false;
	
	public GameScreen(PApplet p) {
		super(p);
		this.name = "Game Screen";
	}
	
	
	public GameScreen(PApplet p, GameManager _gameManager) {
		super(p);
		this.name = "Game Screen";
		this.gm = _gameManager;
		this.exitScreens = new ArrayList<GameScreen>();
		this.playerGameObjects = new ArrayList<GameObject>();
		this.menuGameObjects = new ArrayList<GameObject>();
		this.gameObjects = new ArrayList<GameObject>();
		this.gameBoundingBoxes = new ArrayList<BoundingBox>();	
	}
	
	public void start() {
		if(!this.ready) {
			this.ready = true;
			
		}
		this.activate();
	}
	
	public abstract void keyPressed(char key, int keyCode);
	public abstract void keyReleased(char key, int keyCode);
	public abstract void mousePressed();
	public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);
	
	public void exitScreensAdd(GameScreen _screen) {
		this.exitScreens.add(_screen);
	}
	
	public void swapTo(int i) {
		
		if(this.exitScreen < this.exitScreens.size()) {
			this.exitScreen = i;
			this.swap_screen = this.exitScreens.get(this.exitScreen);
			//this.swap_screen.activate();
		}else {
			parent.println("SCREEN OUT OF RANGE");

		}
	
	}
	
	
	public void activate() {
		this.gm.replaceObjects(this.gameObjects);
		this.gm.replaceUIObjects(this.menuGameObjects);
		this.gm.replacePlayerObjects(this.playerGameObjects);
		this.gm.replaceBoundingBoxes(this.gameBoundingBoxes);
		this.activated = true;
		this.gm.StartAll();
		
	}


}
