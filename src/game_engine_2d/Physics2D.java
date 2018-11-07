package game_engine_2d;

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
	public float speed = 3f;
	public float maxSpeed = 5f;
	
	public Physics2D(GameObject g) {
		super(g);
	}
	
	@Override
	public void start() {
		this.boxCollider2D = new BoxCollider2D(this.gameObject);
	}
	
	@Override
	public void update() {
		frameWait++;
		
		if(frameWait >frameDelay) {
			frameWait = 0;
			checkCollisions();
		}
		
		this.transform.prev_position.x = this.transform.position.x;
		this.transform.prev_position.y = this.transform.position.y;
		gravityEffect();
		
		if(isGrounded) {
			this.velocity.x *= friction;
		}
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
		
		 if(isGrounded && PApplet.abs(this.velocity.x) <=0.1) {
			 this.velocity.x = 0f;
		}
		 this.transform.position.add(this.velocity);
	}
	
	private void checkCollisions() 
	{
		if(PApplet.abs(this.transform.prev_position.x - this.transform.position.x)<0.1f){
			if(PApplet.abs(this.transform.prev_position.y - this.transform.position.y)< 0.1f) {
				return;
			}
		}
	

	for (int i = 0; i < GameManager.gameBoundingBoxes.size(); i++) {
		HitInfo hitInfo = new HitInfo();
		hitInfo.boundingBox = GameManager.gameBoundingBoxes.get(i);
		hitInfo = this.boxCollider2D.checkCollision(hitInfo);
		if(hitInfo.didHit) {
			switch (hitInfo.hitSide) {
			case TOP:
				this.velocity.y = 0f;
				this.transform.position.y = hitInfo.boundingBox.bottom + this.transform.boundingBox.bottom;
								
				break;
				
				
			case BOTTOM:
				this.velocity.y = 0f;
				this.transform.position.y = hitInfo.boundingBox.top + this.transform.boundingBox.top;
				if(!isGrounded) {
					isGrounded = true;
					this.velocity.x = 0f;
			}
				
				break;
				
			case LEFT:
				this.velocity.x *= -1f;
				this.transform.position.x = hitInfo.boundingBox.left + this.transform.boundingBox.left;
				
				break;
				
			case RIGHT:
				this.velocity.x *= -1f;
				this.transform.position.x = hitInfo.boundingBox.right + this.transform.boundingBox.right;

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
		if(PApplet.abs(this.velocity.y) >= maxSpeed) {
			this.velocity.y = maxSpeed;
		}
	}
	
	public void move(float force) {
		//if (isGrounded) {
			this.friction = this.frictionOverride;
			this.velocity.x += force;
		//}
	}
	
	public void keyUp() {
		if(isGrounded) {
			this.friction =this.frictionNormal;
		}
	}

}
