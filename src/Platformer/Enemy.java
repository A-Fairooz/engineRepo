package Platformer;

import gameComponents.Physics2D;
import game_engine_2d.*;
import processing.core.PApplet;
import processing.core.PVector;

public class Enemy extends Sprite {
	
	
	private PVector size = new PVector (12,12);
	public int stroke = parent.color(120,120,255);
	public int fill = parent.color(255,0,0);	
	private Physics2D physics;
	float speedForce = 3f;
	float jumpForce = 2f;
	public PVector spawnPoint = new PVector(0,0);
	
	public Enemy(PApplet p) {
		super(p);
	}

	public Enemy(PApplet p, float x, float y, float w, float h) {
		super(p);
	}
		
		public void start() {
			this.transform.position.x = 400;//(parent.width / 2) + 24;
			
			this.transform.position.y = 500;//parent.height / 2;
			
			//spawnPoint = this.transform.position;
					
			this.transform.boundingBox.fromSize(size);			
			this.physics = new Physics2D(this);
			this.physics.start();
			this.physics.speed = speedForce;
		}
		
		@Override
		public void update() {
			super.update();
			
		}
		
		@Override
		public void render () {
			parent.fill(this.fill);
			parent.stroke(this.stroke);
			parent.rect(this.transform.position.x,
						this.transform.position.y,
						this.size.x,
						this.size.y);
			
		}
	}
