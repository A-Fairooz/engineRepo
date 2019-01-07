package game_engine_2d;

import java.util.ArrayList;
import game_engine_2d.BoundingBox;
import game_engine_2d.data_management.DataManager;
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
		parent.println("From " + this.name + " swapTo " + i + " => ");
		if(this.exitScreen < this.exitScreens.size()) {
			this.exitScreen = i;
			this.swap_screen = this.exitScreens.get(this.exitScreen);

		}else {
			parent.println("Error: exitScreen out of range");

		}
	
	}
	
	
	public void activate() {
		this.gameManager.replaceObjects(this.gameObjects);
		this.gameManager.replaceGUIObjects(this.menuGameObjects);
		this.gameManager.replacePlayerObjects(this.playerGameObjects);
		this.gameManager.replaceBoundingBoxes(this.gameBoundingBoxes);
		this.activated = true;
		this.gameManager.StartAll();
		parent.println("activated " + this.name);
	}
	
	protected boolean load_tile_json() {
	
		try {
			this.gameManager.dataManager = new DataManager(parent);
			this.gameManager.dataManager.load_data();
		}
		catch(Exception E) {
			return false;
		}
		
			JSONArray tiles;
			try {
				tiles = this.gameManager.dataManager.game_data.getJSONArray("level1");
			}
			catch(Exception E) {
				return false;
			}
			
			for(int i = 0; i < tiles.size(); i ++) {
				JSONObject tile;
				try {
					tile = tiles.getJSONObject(i);
				}
				catch(Exception E) {
					continue;
				}
			int x = tile.getInt("x");
			int y = tile.getInt("y");
			int tw = tile.getInt("w");
			int th = tile.getInt("h");
			int tc_0 = tile.getInt("tColour_0");
			int tc_1= tile.getInt("tColour_1");
			int tc_2 = tile.getInt("tColour_2");
			
			Tile platform = new Tile(parent, x, y, tw, th, tc_0,tc_1,tc_2);
			platform.start();
			this.gameObjects.add(platform);
			this.gameBoundingBoxes.add(platform.transform.NewWorldBoundingBox());
		}
			return true;
	}
	
	
	
	
	
	
	

}
