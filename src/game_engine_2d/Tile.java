
package game_engine_2d;

import processing.core.PApplet;


public class Tile extends Sprite {

	
	public int strokeColour;
	public int fillColour;

	

	 
	public Tile(PApplet p, float x, float y, float w, float h, float fColour1,float fColour2,float fColour3, float strokeColour) {
		super(p, x, y);
		this.size.x = w;
		this.size.y = h;
		this.strokeColour = parent.color(strokeColour);
		this.fillColour = parent.color(fColour1, fColour2, fColour3);
		
	}

	public void start() {
		super.start();
		this.transform.localBoundingBox.fromSize(size);
	}
	
	@Override
	public void update() {
		super.update();

	}
	public void render() {
		
		parent.pushMatrix(); // reset the translation and rotation
		parent.translate(this.transform.position.x, this.transform.position.y);
		parent.stroke(this.strokeColour);
		parent.fill(this.fillColour);
		parent.rectMode(PApplet.CENTER);
		parent.rect(0, 0, this.size.x, this.size.y);
		parent.popMatrix();
	}
	

}
