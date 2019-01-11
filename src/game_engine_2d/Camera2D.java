package game_engine_2d;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera2D extends GameObject{
	
	
	//Variables
	public float offsetLimit = 50;
	public PVector cameraOffset = new PVector(0,0);
	public GameObject followMe;		
	
	public Camera2D(PApplet p, GameObject f)
	{
		super(p);
		this.followMe = f;
	}
	
	public Camera2D(PApplet p, GameObject f, float limit) 
	{
		super(p);
		this.followMe = f;
		offsetLimit = limit;
	} 

	@Override
	public void start() {}
	
	@Override
	public void update() {}
	
	@Override
	public void render() 
	{
		late_update_xy();
	}
	
	
	private void late_update_xy() {
		//Copy the virtual screen's centre offset
		//Setup the player's virtual global position
		//check the distance between the player and the virtual screen's centre
		PVector virtualScreenCentre = GameManager.screenOffset.copy();		
		virtualScreenCentre.add(GameManager.offset);
		
		PVector virtualPlayer = GameManager.offset.copy();
		virtualPlayer.add(this.followMe.transform.position);
		
		float d = virtualScreenCentre.dist(virtualPlayer);
		//compare the global difference between the screen virtual centre and the player virtual player's position
		//add custom camera offset
		//transform players position forward to the new offset
		if(d > offsetLimit) {
			
			virtualScreenCentre.sub(virtualPlayer);			
			virtualScreenCentre.add(this.cameraOffset);			
			GameManager.offset.lerp(virtualScreenCentre,1);
		}
		
	}
	public void reset() {
		cameraOffset = new PVector(0,0);
		GameManager.offset = new PVector(0,0);
	}
}
