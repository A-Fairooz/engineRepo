package game_engine_2d;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera2D extends GameObject{
	
	public GameObject followMe;
	public float offsetLimit = 50;
	public PVector cameraOffset = new PVector(0,0);
	public Camera2D(PApplet p, GameObject f) {
		super(p);
		this.followMe = f;
	}
	
	public Camera2D(PApplet p, GameObject f, float limit) {
		super(p);
		this.followMe = f;
		offsetLimit = limit;
	} 

	@Override
	public void start() {
		
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void render() {
		late_update_xy();
	}
	
	
	private void late_update_xy() {
		//copy screen offset centre
		PVector virtualScreenCentre = GameManager.screenOffset.copy();
		//add current global offset
		virtualScreenCentre.add(GameManager.offset);
		//setup player virtual global position
		PVector virtualPlayer = GameManager.offset.copy();
		virtualPlayer.add(this.followMe.transform.position);
		//check distance between player and virtual centre
		float d = virtualScreenCentre.dist(virtualPlayer);
		
		if(d > offsetLimit) {
			//get difference between virtual centre and player
			virtualScreenCentre.sub(virtualPlayer);
			//add custom camera offset
			virtualScreenCentre.add(this.cameraOffset);
			//transform forward to new offset
			GameManager.offset.lerp(virtualScreenCentre,1);
		}
		
	}
	
}
