package Platformer;

import game_engine_2d.Physics2D;
import game_engine_2d.*;
import processing.core.PApplet;
import processing.core.PVector;
//import java.lang.*;
public class Player extends Sprite{
	
	
	private PVector size = new PVector (12,12);
	public int stroke = parent.color(120,120,255);
	public int fill = parent.color(244, 232, 66);	
	public PVector spawnPoint = new PVector(0,0);	
	float speedForce = 1.5f;
	float jumpForce = 12f;	
	public boolean isReset;
	
	private Physics2D physics;
	
	public Player(PApplet p) {
		super(p);
	} 

	public Player(PApplet p, float x, float y, float w, float h) {
		super(p);
		this.transform.position.x = x;		
		this.transform.position.y = y;
		this.spawnPoint.x = x;
		this.spawnPoint.y = y;
		this.size.x =w;
		this.size.y = h;
		this.tag = "Player";		
	}
	
	public void start() 
	{
		this.transform.localBoundingBox.fromSize(size);
		this.physics = new Physics2D(this);
		this.physics.start();		
		this.physics.speedF = speedForce;
		
	}
	
	@Override
	public void update()
	{
		super.update();		
		if(this.transform.position.y > parent.height*2 - parent.height/2)
		{
			this.transform.position.x = this.spawnPoint.x;
			this.transform.position.y = this.spawnPoint.y;
		}
		
		if(this.physics.collisionCount > 0) {
			this.physics.collisionCount = 0;
			this.transform.position.x = this.spawnPoint.x;
			this.transform.position.y = this.spawnPoint.y;
		}
		
		
	}
	
	@Override
	public void render () {
		parent.fill(this.fill);
		parent.stroke(this.stroke);
		parent.ellipse(this.transform.position.x,
					this.transform.position.y,
					this.size.x,
					this.size.y);
		
	}
	public void keyPressed(char key, int keyCode)
	{
		super.keyPressed(key, keyCode);	
		
		   if (keyCode == PApplet.UP) 
		   {			  
			this.physics.flappyBirdJump(jumpForce);					
		   }
		   
		   if(keyCode == PApplet.BACKSPACE) 
		   {
			   this.transform.position = spawnPoint;
		   }
		   
		   
	}
}
