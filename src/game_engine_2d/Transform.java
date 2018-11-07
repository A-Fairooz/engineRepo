
package game_engine_2d;

import game_engine_2d.GameComponents.BoundingBox;
import processing.core.PVector;


public class Transform {

	public Transform() {
		
	}

	public PVector prev_position = new PVector(0,0);
	public PVector position = new PVector(0,0);
	public PVector rotation = new PVector(0,0);
	public PVector scale = new PVector(0,0);
	public BoundingBox boundingBox = new BoundingBox(-1,1,-1,1);
	
	public BoundingBox PreviousWorldBoundingBox() {
		BoundingBox bb = new BoundingBox();
		bb.center_x = prev_position.x;
		bb.center_y = prev_position.y;
		bb.left = prev_position.x + boundingBox.left;
		bb.right = prev_position.x + boundingBox.right;
		bb.top = prev_position.y + boundingBox.top;
		bb.bottom = prev_position.y + boundingBox.bottom;
		return bb;
	}

	
	public BoundingBox NewWorldBoundingBox() {
		BoundingBox bb = new BoundingBox();
		bb.center_x = position.x;
		bb.center_y = position.y;
		bb.left = position.x + boundingBox.left;
		bb.right = position.x + boundingBox.right;
		bb.top = position.y + boundingBox.top;
		bb.bottom = position.y + boundingBox.bottom;
		return bb;
	}
}
