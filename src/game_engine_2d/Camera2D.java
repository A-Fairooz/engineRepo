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
		PVector virtualScreenCentre = GameManager.screenOffset.copy();		
		virtualScreenCentre.add(GameManager.offset);
		//Setup the player's virtual global position
		PVector virtualPlayer = GameManager.offset.copy();
		virtualPlayer.add(this.followMe.transform.position);
		//check the distance between the player and the virtual screen's centre
		float d = virtualScreenCentre.dist(virtualPlayer);
		
		if(d > offsetLimit) {
			//compare the global difference between the screen virtual centre and the player virtual player's position
			virtualScreenCentre.sub(virtualPlayer);
			//add custom camera offset
			virtualScreenCentre.add(this.cameraOffset);
			//transform players position forward to the new offset
			GameManager.offset.lerp(virtualScreenCentre,1);
		}
		
	}
	
}
