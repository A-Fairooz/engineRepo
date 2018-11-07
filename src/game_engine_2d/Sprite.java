package game_engine_2d;

import processing.core.PVector;
import gameComponents.GameComponent;
import processing.core.PApplet;

public abstract class Sprite extends GameObject {
	public PVector size = new PVector(12,12);
	public Sprite(PApplet p) {
		super(p);
	}
	public Sprite(PApplet p, float x, float y) {
		super(p);
		this.transform.position.x = x;
		this.transform.position.y = y;
		
	}
	public abstract void render();

	@Override
	public void update() {
		for(int i = this.components.size()-1; i>=0;i--) {
			GameComponent comp = this.components.get(i);
			comp.update();
			comp.render();
		}
		
	}

	@Override
	public void start() {
		for(int i = this.components.size() -1; i>=0; i--) {
			GameComponent comp = this.components.get(i);
			comp.start();
			}
		
	}
}
