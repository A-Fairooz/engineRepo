package game_engine_2d;

import java.util.ArrayList;

import game_engine_2d.GameManager;
import game_engine_2d.GameObject;
import processing.core.PApplet;
import processing.core.PVector;


public class Physics2D extends GameComponent{
	
	int frameWait = 0;
	int frameDelay = 2;
	private PVector velocity = new PVector(0,0);
	public BoxCollider2D boxCollider2D;
	float gravity = 0.1f;
	float friction = 0.9f;
	float frictionOverride = 1;
	float frictionNormal = 0.9f;
	boolean isGrounded =false;
	public float speed = 5f;
	public float maxSpeed = 5f;
	public int collisionCount = 0;
	
	public Physics2D(GameObject g) {
		super(g);
	}
	
	@Override
	public void start() {
		this.boxCollider2D = new BoxCollider2D(this.gameObject);
	}
	
	@Override
	public void update() {
		
		
		
		this.transform.prev_position.x = this.transform.position.x;
		this.transform.prev_position.y = this.transform.position.y;
		gravityEffect();
		
		if(this.isGrounded) {
			this.velocity.x *= friction;
		}
		if(PApplet.abs(this.velocity.x) >= maxSpeed) 
		{
			
		 if(this.velocity.x > 0) 
			{
				this.velocity.x = maxSpeed;
				parent.println("MAX SPEED");
			}
			
		 else
			{
			 this.velocity.x = -maxSpeed;
			}
		}
		
		 if(isGrounded && PApplet.abs(velocity.x) <=0.01) {
			 this.velocity.x = 0f;
		}
		 this.transform.position.add(velocity);
		 checkCollisions();
	}
	
	private void checkCollisions() 
	{
		if(PApplet.abs(this.transform.prev_position.x - this.transform.position.x)<0.001f){
			if(PApplet.abs(this.transform.prev_position.y - this.transform.position.y)< 0.001f) {
				return;
			}
		}
		
		BoundingBox new_bb;
		new_bb = this.transform.NewWorldBoundingBox();
		ArrayList<BoundingBox> mySpatialLoc = GameManager.basicSpatialGrid.queryGrid(new_bb);
		collisionCount = mySpatialLoc.size();
		for(int i = 0; i<collisionCount; i++) {
			
			
			
			HitInfo hitInfo = new HitInfo();
			hitInfo.boundingBox = mySpatialLoc.get(i);
			hitInfo = this.boxCollider2D.checkCollision(hitInfo);
			
			if(hitInfo.didHit) {
				switch (hitInfo.hitSide) {
				case TOP:
					this.velocity.y = 0f;
					this.transform.position.y = hitInfo.boundingBox.bottom + this.transform.localBoundingBox.bottom;
									
					break;
					
					
				case BOTTOM:
					this.velocity.y = 0f;
					this.transform.position.y = hitInfo.boundingBox.top + this.transform.localBoundingBox.top;
					if(!this.isGrounded) {
						this.isGrounded = true;
						this.velocity.x *= 0.5f;
				}
					
					break;
					
				case LEFT:
					this.velocity.x *= -1f;
					this.transform.position.x = hitInfo.boundingBox.left + this.transform.localBoundingBox.left-2;
					this.isGrounded = false;
					break;
					
				case RIGHT:
					this.velocity.x *= -1f;
					this.transform.position.x = hitInfo.boundingBox.right + this.transform.localBoundingBox.right+2;
					this.isGrounded = false;

					break;
					
				case NONE:
					
					isGrounded = false;
					break;
			
				}


	
		}
	}
		
 }

	@Override
	public void render() {
		
	}
	public void jump (float force) {
		if (isGrounded) {
			this.velocity.y = -force;
			isGrounded = false;
		}
	}

	private void gravityEffect() {
		this.velocity.y +=gravity;
		if(velocity.y >= maxSpeed) {
			this.velocity.y = maxSpeed;
		}
	}
	
	public void move(float force) {
		//if (isGrounded) {
		    this.velocity.x += force;
			this.friction = this.frictionOverride;
			
		//}
	}
	
	public void keyUp() {
		this.friction =this.frictionNormal;
		if(isGrounded) {			
			this.velocity.x = 0;
		}
	}

}
