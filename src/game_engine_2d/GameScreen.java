package game_engine_2d;

import java.util.ArrayList;
import game_engine_2d.BoundingBox;
import processing.core.PApplet;

public abstract class GameScreen extends ProcessingEntity{
	
	
	protected int exitScreen = -1;
	protected ArrayList<GameObject> gameObjects;
	protected ArrayList<GameObject> playerGameObjects;
	protected ArrayList<GameScreen> exitScreens;
	public GameScreen swap_screen = null;
	protected ArrayList<BoundingBox> gameBoundingBoxes;
	protected GameManager gameManager;
	protected boolean ready = false;
	public boolean activated = false;
	
	public GameScreen(PApplet p) {
		super(p);
		this.name = "Game Screen";
	}
	
	
	public GameScreen(PApplet p, GameManager _gameManager) {
		super(p);
		this.name = "Game Screen";
		this.gameManager = _gameManager;
		this.exitScreens = new ArrayList<GameScreen>();
		this.playerGameObjects = new ArrayList<GameObject>();
		this.gameObjects = new ArrayList<GameObject>();
		this.gameBoundingBoxes = new ArrayList<BoundingBox>();		
	}
	
	public void start() {
		if(this.ready) {
			this.activate();
			return;
		}
	}
	
	public abstract void keyPressed(char key, int keyCode);
	public abstract void keyReleased(char key, int keyCode);
	public abstract void mousePressed();
	public abstract void mouseClicked();
	
	public void exitScreensAdd(GameScreen _screen) {
		this.exitScreens.add(_screen);
	}
	
	public void swapTo(int i) {
		if(this.exitScreen != i && this.exitScreen < this.exitScreens.size()) {
			this.exitScreen = i;
			this.swap_screen = this.exitScreens.get(this.exitScreen);
			parent.println("swapTo " + i + " => " + this.name);
		}
	}
	
	
	public void activate() {
		this.gameManager.replaceObjects(this.gameObjects);
		this.gameManager.replacePlayerObjects(this.playerGameObjects);
		this.gameManager.replaceBoundingBoxes(this.gameBoundingBoxes);
		this.activated = true;
		this.gameManager.StartAll();
		parent.println("Activated " + this.name);
	}
	
	
	
	
	
	
	
	

}
