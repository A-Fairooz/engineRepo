import Game_FlappyBird.Launcher;
import processing.core.PApplet;

public class Main extends PApplet{
	public static void main(String[] args) {		
		PApplet.main("Main");
	
	}
	
	public Launcher launcher;
	public int sW = 800;
	public int sH = 800;
	public int bg_1 = 96;
	public int bg_2 = 218 ;
	public int bg_3 = 255;
	
	public void setup() {
		background(bg_1,bg_2,bg_3);
		frameRate = 60;
		launcher = new Launcher(this);	
		
	}
	
	public void draw() {
	
		launcher.UpdateAll();
	}
	
	
	public void settings() {
		size(sW, sH);
	}
	
	public void mouseClicked() {launcher.mouseClicked(mouseX, mouseY, mouseButton);}
	public void keyPressed() {launcher.keyPressed(key, keyCode);}
	public void keyReleased() {launcher.keyReleased(key, keyCode);}
	
}
