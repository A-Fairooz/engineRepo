package game_engine_2d;
import processing.core.PApplet;
import java.util.ArrayList;

import game_engine_2d.BoundingBox;
import game_engine_2d.GameComponent;

public abstract class GameObject extends ProcessingEntity {
	public GameObject(PApplet p) {
		super(p);
		this.components = new ArrayList<GameComponent>();
	}
	
	//Variables
	public String layer;
	public String tag;
	public String name;
	
	
	
	public ArrayList<GameComponent> components;
	public Transform transform = new Transform();
	public abstract void start();
	public abstract void update();
	public abstract void render();
	public void checkCollisions( BoundingBox bc) {}
	public void keyPressed(char key, int keyCode) {}
	public void keyReleased(char key, int keyCode) {}
	public void mousePressed() {}
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}
	public String ToString() {return this.name;}
}
