import processing.core.PApplet;
import Platformer.Launcher;
import processing.core.PImage;

public class Main extends PApplet{
	public static void main(String[] args) {		
		PApplet.main("Main");
	
	}
	public static PImage testImage;
	public Launcher launcher;
	public int sW = 800;
	public int sH = 800;
	
	public void setup() {
		background(0);
		frameRate = 60;
		launcher = new Launcher(this);
		
		
	}
	
	
	public void draw() {
	
		launcher.UpdateAll();
	}
	
	
	public Object GetImage(String imageName) {
		
		return testImage;
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
