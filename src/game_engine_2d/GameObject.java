package game_engine_2d;
import processing.core.PApplet;
import java.util.ArrayList;

public abstract class GameObject extends ProcessingEntity {
	public GameObject(PApplet p) {
		super(p);
		this.components = new ArrayList<GameComponent>();
	}

	public String name;
	public String tag;
	
	public ArrayList<GameComponent> components;
	public Transform transform = new Transform();
	public abstract void start();
	public abstract void update();
	public abstract void render();
	//public void checkCollisions( BoundingBox bb) {}
	public void keyPressed(char key, int keyCode) {}
	public String ToString() {
		return this.name;
	}
}
