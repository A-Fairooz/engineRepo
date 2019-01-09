package Platformer;

import game_engine_2d.Physics2D;
import game_engine_2d.*;
import processing.core.PApplet;
import processing.core.PVector;
//import java.lang.*;
public class EmptyGameObject extends Sprite{
	
	//Variables
	private PVector size = new PVector (12,12);	
	private Physics2D physics;

	
	public EmptyGameObject(PApplet p) {super(p);}
	public EmptyGameObject(PApplet p, float x, float y, float w, float h) 
	{
		super(p);
		this.transform.position.x = x;		
		this.transform.position.y = y;		
	}
	public void start() {}	
	@Override
	public void update() {super.update();}	
	@Override
	public void render () {}	
	public void keyPressed(char key, int keyCode) {super.keyPressed(key, keyCode);}
	public void keyReleased(char key, int keyCode){}
}
