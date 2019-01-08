import processing.core.PApplet;
import Platformer.Launcher;

public class Main extends PApplet{
	public static void main(String[] args) {		
		PApplet.main("Main");
	
	}
	
	public Launcher launcher;
	public int sW = 800;
	public int sH = 800;
	public int bg = 0;
	
	public void setup() {
		background(bg);
		frameRate = 60;
		launcher = new Launcher(this);
		
		
	}
	
	public void draw() {
	
		launcher.UpdateAll();
	}
	
	
	public void settings() {
		size(sW, sH);
	}

	public void keyPressed() {
		launcher.keyPressed(key, keyCode);
	}
	
	public void keyReleased() {
		launcher.keyReleased(key, keyCode);
	}
	public void mousePressed()   { 
		launcher.mousePressed(); 
		}
	public void mouseClicked() {launcher.mouseClicked(mouseX, mouseY, mouseButton);}
}
