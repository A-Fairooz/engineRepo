package Platformer;

import game_engine_2d.Physics2D;
import game_engine_2d.*;
import processing.core.PApplet;
import processing.core.PVector;
//import java.lang.*;
public class Player extends Sprite{
	
	
	private PVector size = new PVector (12,12);
	public int stroke = parent.color(120,120,255);
	public int fill = parent.color(255);	
	private Physics2D physics;
	float speedForce = 3f;
	float jumpForce = 3f;
	public PVector spawnPoint = new PVector(0,0);
	
	public Player(PApplet p) {
		super(p);
	}

	public Player(PApplet p, float x, float y, float w, float h) {
		super(p);
		
	}
	public void start() {
		this.transform.position.x = parent.width / 2;
		spawnPoint.x = parent.width / 2;
		this.transform.position.y = parent.height / 2;
		spawnPoint.y = parent.height / 2;
		//spawnPoint = this.transform.position;
				
		this.transform.localBoundingBox.fromSize(size);
		this.physics = new Physics2D(this);
		this.physics.start();
		this.physics.speed = speedForce;
	}
	
	@Override
	public void update() {
		super.update();
		//System.out.println("test");
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
	public void keyPressed(char key, int keyCode) {
		// mapped key pressed
		super.keyPressed(key, keyCode);
		   if (keyCode == PApplet.UP) 
		   {
			   
			this.physics.jump(jumpForce);					
		   }
		   
		   if (keyCode == PApplet.LEFT) 
		   {
			this.physics.move(-speedForce);
			System.out.println("test");
		   }
		   
		   if (keyCode == PApplet.RIGHT) 
		   {
			this.physics.move(speedForce);
		   }
		   
		   if(keyCode == PApplet.BACKSPACE) 
		   {
			   this.transform.position = spawnPoint;
		   }
		   
		   
	}

	public void keyReleased(char key, int keyCode) {
		this.physics.keyUp();
	}
}
