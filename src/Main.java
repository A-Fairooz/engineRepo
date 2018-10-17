import processing.core.PApplet;
public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");
	
	}
	
	public void setup() {
		background(0);
		frameRate(120);
	}
	
	public void draw() {
	//background(random(255),random(255),random(255));
		ellipse(20,20,20,20);
	}
	
	
	public void settings() {
		size(640,480);
	}

}
