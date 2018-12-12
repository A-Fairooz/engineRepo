package game_engine_2d;
import processing.core.PApplet;

public abstract class BaseLauncher {
	public PApplet parent;
	
	
	public BaseLauncher(PApplet p) {
		parent = p;
		gameManager = new GameManager(parent);
	}
	
	public GameManager gameManager;
	public abstract void StartGame();
	
	public void UpdateAll() {
		gameManager.UpdateAll();
	}
	public void keyPressed(char key, int keyCode) {
    	gameManager.keyPressed(key, keyCode);
    }
    
    public void keyReleased(char key, int keyCode) {
    	gameManager.keyReleased(key, keyCode);
    }
    
    public void mousePressed() {
    	gameManager.mousePressed();
    }
    public void mouseClicked() {
    	gameManager.mouseClicked();
    }
}
