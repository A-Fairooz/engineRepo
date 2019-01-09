package game_engine_2d;

import java.util.ArrayList;

import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import processing.core.PApplet;
import processing.core.PVector;


public class Physics2D extends GameComponent{
	
	//Variables
	int frameWait = 0;
	int frameDelay = 2;
	private PVector velocity = new PVector(0,0);
	public BoxCollider2D boxCollider2D;
	float gravityForce = 0.3f;
	public float speedF = 0.5f;
	public float maxSpeed = 5f;
	public int collisionCount = 0;
	
	public Physics2D(GameObject g) {super(g);}	
	@Override
	public void start() {this.boxCollider2D = new BoxCollider2D(this.gameObject);}
	@Override
	public void update() {		
		this.transform.prev_position.x = this.transform.position.x;
		this.transform.prev_position.y = this.transform.position.y;
		gravityOn();
		
	
		if(PApplet.abs(this.velocity.x) >= maxSpeed) 
		{
			
		 if(this.velocity.x > 0) 
			{
				this.velocity.x = maxSpeed;				
			}
			
		 else
			{
			 this.velocity.x = -maxSpeed;
			}
		}
		 this.transform.position.add(velocity);
		 checkCollisions();
	}
	
	private void checkCollisions() 
	{
		if(PApplet.abs(this.transform.prev_position.x - this.transform.position.x)<0.001f)
		{
			if(PApplet.abs(this.transform.prev_position.y - this.transform.position.y)< 0.001f) 
			{
				return;
			}
		}
		BoundingBox new_bb;
		new_bb = this.transform.NewWorldBoundingBox();	
		for(int i = 0; i<GameManager.gameBoundingBoxes.size(); i++) 
		{		
			
			HitInfo hitInfo = new HitInfo();
			hitInfo.boundingBox = GameManager.gameBoundingBoxes.get(i);
			hitInfo = this.boxCollider2D.checkCollision(hitInfo);
			
			if(hitInfo.didHit) {
				switch (hitInfo.hitSide) 
				{
				case TOP:
					collisionCount++;
					this.velocity.y = 0f;
					this.transform.position.y = hitInfo.boundingBox.bottom + this.transform.localBoundingBox.bottom;					
					break;
					
				case BOTTOM:
					collisionCount++;
					this.velocity.y = 0f;
					this.transform.position.y = hitInfo.boundingBox.top + this.transform.localBoundingBox.top;
					break;
					
				case LEFT:
					collisionCount++;
					this.velocity.x *= -1f;
					this.transform.position.x = hitInfo.boundingBox.left + this.transform.localBoundingBox.left-2;
					break;
					
				case RIGHT:
					collisionCount++;
					this.velocity.x *= -1f;
					this.transform.position.x = hitInfo.boundingBox.right + this.transform.localBoundingBox.right+2;
					break;
					
				case NONE:					
					break;
			
				}
		}
	}
		
 }

	@Override
	public void render() {}
	
	public void flappyBirdJump(float force)
	{
		this.velocity.y = -force;		
	}


	private void gravityOn() 
	{
		this.velocity.y +=gravityForce;
		this.velocity.x = speedF;
		if(velocity.y >= maxSpeed) 
		{
			this.velocity.y = maxSpeed;
		}
	}	
}
