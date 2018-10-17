package game_engine_2d;
import processing.core.PApplet;

public class BaseLauncher {
	public PApplet parent;
	
	
	public BaseLauncher(PApplet p) {
		parent = p;
	}
	
	public GameManager gameManager;
	public void StartGame() {
		gameManager = new GameManager(parent);
	}
	
	public void UpdateAll() {
		gameManager.UpdateAll();
	}
	
}
