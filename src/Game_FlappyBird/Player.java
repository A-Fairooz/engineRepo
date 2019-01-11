package Game_FlappyBird;

import java.util.ArrayList;

import game_engine_2d.*;
import processing.core.PApplet;
import processing.core.PVector;
//import java.lang.*;
public class Player extends Sprite{
	
	
	private PVector size = new PVector (12,12);
	public int stroke = parent.color(120,120,255);
	public int fill = parent.color(244, 232, 66);	
	public PVector spawnPoint = new PVector(0,0);	
	float speedForce =3f;
	float jumpForce = 8f;	
	int scoreInt;
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
		this.name = this.tag;
	}
	
	public void start() 
	{
		this.transform.localBoundingBox.fromSize(size);
		this.components = new ArrayList<GameComponent>();
		this.physics = new Physics2D(this);
		this.physics.start();		
		this.physics.speedF = speedForce;
		scoreInt = 0;
	
	}
	public void reset() {
		this.transform.position.x = 20;
		this.transform.position.y = parent.height;
		
		physics.reset();
	}
	@Override
	public void update()
	{
		super.update();		
		scoreInt +=1;
		parent.fill(255);
		parent.text(scoreInt, this.transform.position.x, this.transform.position.y - this.size.y);
				
		
		
		if(this.transform.position.y > parent.height*2 - parent.height/2)
		{
			this.transform.position.x = this.spawnPoint.x;
			this.transform.position.y = this.spawnPoint.y;
			scoreInt = 0;
		}
		
		if(this.physics.collisionCount > 0) {
			this.physics.collisionCount = 0;
			this.transform.position.x = this.spawnPoint.x;
			this.transform.position.y = this.spawnPoint.y;
			scoreInt = 0;
		}
		
		
	}
	
	@Override
	public void render () {
		
		parent.fill(255);
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
		
		   if (keyCode == PApplet.UP ||key == ' ' || key == 'W'|| key == 'w') 
		   {			  
			this.physics.flappyBirdJump(jumpForce);					
		   }
		   
		   if(keyCode == PApplet.BACKSPACE) 
		   {
			   this.transform.position = spawnPoint;
		   }
		   if(key == 'u') {
			   scoreInt +=1;
				
				this.parent.fill(this.fill);
				this.parent.noStroke();
		   }
		   
		   
	}
}
