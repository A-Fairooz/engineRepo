
package game_engine_2d;

import processing.core.PApplet;


public class Tile extends Sprite {

	public int width;
	public int height;
	int strokeColour;
	int fillColour;

	public Tile(PApplet p) {
		super(p);
		
	}

	/**
	 * @param p
	 * @param x
	 * @param y
	 */
	public Tile(PApplet p, int x, int y, int w, int h) {
		super(p, x, y);
		this.width = w;
		this.height = h;
		this.strokeColour = parent.color(255, 255, 255);
		this.fillColour = parent.color(0, 0, 0);

	}

	public void start() {
		super.start();
	}
	
	@Override
	public void update() {
		

	}
	public void render() {
		
		parent.pushMatrix(); // reset the translation and rotation
		parent.translate(this.transform.position.x, this.transform.position.y);
		parent.stroke(this.strokeColour);
		parent.fill(this.fillColour);
		parent.rect(0, 0, this.width, this.height);
		parent.popMatrix();
	}
	

}
