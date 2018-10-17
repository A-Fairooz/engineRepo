package Platformer;

import game_engine_2d.Sprite;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Sprite{

	float speed = 3f;
	float gravity = 0.1f;
	
	private PVector velocity = new PVector(0,0);
	private PVector size = new PVector (12,12);
	public int stroke = parent.color(120,120,255);
	public int fill = parent.color(255);
	
	public Player(PApplet p) {
		super(p);
	}

	public Player(PApplet p, float x, float y, float w, float h) {
		super(p);
		//speed = 3.0f;
	}
	public void start() {
		this.transform.position.x = parent.width / 2;
		this.transform.position.y = parent.height / 2;
	}
	
	@Override
	public void update() {
		velocity.y += this.gravity;
		this.transform.position.y += velocity.y;
	}
	
	@Override
	public void render () {
		parent.fill(this.fill);
		parent.stroke(this.stroke);
		parent.rect(this.transform.position.x,
					this.transform.position.y,
					this.size.x,
					this.size.z);
		
	}
	
}
