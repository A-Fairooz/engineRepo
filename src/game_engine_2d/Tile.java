
package game_engine_2d;

import processing.core.PApplet; 


public class Tile extends Sprite {

	//Variables
	public int strokeColour;
	public int fillColour;
	public int[] tileColour = new int[3];
	
	 
	public Tile(PApplet p, float x, float y, float w, float h, int tColour_0, int tColour_1, int tColour_2) {
		super(p, x, y);
		this.size.x = w;
		this.size.y = h;
		this.strokeColour = parent.color(tColour_0, tColour_1, tColour_2);
		this.fillColour = parent.color(tColour_0, tColour_1, tColour_2);
		this.tileColour[0] = tColour_0;
		this.tileColour[1] = tColour_1;
		this.tileColour[2] = tColour_2;
		
		
	}

	public void start() 
	{
		super.start();
		this.transform.localBoundingBox.fromSize(size);
		this.tag = "Tile";
	}
	
	@Override
	public void update() {super.update();}
	
	public void render() 
	{		
		parent.pushMatrix();
		parent.translate(this.transform.position.x, this.transform.position.y);
		parent.stroke(this.strokeColour);
		parent.fill(this.fillColour);
		parent.rectMode(PApplet.CENTER);
		parent.rect(0, 0, this.size.x, this.size.y);
		parent.popMatrix();
	}

}
