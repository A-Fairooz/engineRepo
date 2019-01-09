
package game_engine_2d;

import game_engine_2d.BoundingBox;
import processing.core.PVector;


public class Transform {

	public Transform() {
		
	}

	public PVector prev_position = new PVector(0,0);
	public PVector position = new PVector(0,0);
	public PVector rotation = new PVector(0,0);
	public PVector scale = new PVector(0,0);
	public BoundingBox localBoundingBox = new BoundingBox(-1,1,-1,1);
	public BoundingBox worldBoundingBox = new BoundingBox(-1,1,-1,1);
	
	
	public BoundingBox PreviousWorldBoundingBox() {
		BoundingBox b_b = new BoundingBox();
		b_b.center_x = prev_position.x;
		b_b.center_y = prev_position.y;
		b_b.left = prev_position.x + localBoundingBox.left;
		b_b.right = prev_position.x + localBoundingBox.right;
		b_b.top = prev_position.y + localBoundingBox.top;
		b_b.bottom = prev_position.y + localBoundingBox.bottom;
		return b_b;
	}

	
	public BoundingBox NewWorldBoundingBox() {
		this.worldBoundingBox = new BoundingBox();
		this.worldBoundingBox.center_x = position.x;
		this.worldBoundingBox.center_y = position.y;
		this.worldBoundingBox.left = position.x + localBoundingBox.left;
		this.worldBoundingBox.right = position.x + localBoundingBox.right;
		this.worldBoundingBox.top = position.y + localBoundingBox.top;
		this.worldBoundingBox.bottom = position.y + localBoundingBox.bottom;
		return this.worldBoundingBox;
	}
}
