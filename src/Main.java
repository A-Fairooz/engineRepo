import processing.core.PApplet;
import Platformer.Launcher;
public class Main extends PApplet{

	public static void main(String[] args) {
		
		PApplet.main("Main");
	
	}
	public Launcher launcher;
	
	public void setup() {
		background(0);
		frameRate = 60;
		launcher = new Launcher(this);
		
		
		
	}
	
	public void draw() {
		launcher.StartGame();
		if(launcher.started) {
			launcher.UpdateAll();
		}
	
	}
	
	
	public void settings() {
		size(800,600);
	}

	public void keyPressed() {
		launcher.keyPressed(key, keyCode);
	}
	
	public void keyReleased() {
		launcher.keyReleased(key, keyCode);
	}
	
	
}
