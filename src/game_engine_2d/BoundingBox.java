package game_engine_2d;
import processing.core.PVector;

public class BoundingBox {
	
	//Variables
	public float center_x;
	public float center_y;
	public float left;
	public float right;
	public float top;
	public float bottom;	
	
	public BoundingBox() {}

	public BoundingBox(int L, int R, int T, int B) 
	{

		this.left = L;
		this.right = R;
		this.top = T;
		this.bottom = B;
		this.center_x = 0;
		this.center_y = 0;
	}
	public void fromSize(PVector size) 
	{
		this.left = -size.x/2f;
		this.right = size.x/2f;
		this.top = -size.y/2f;
		this.bottom = size.y/2f;
		this.center_x = 0;
		this.center_y = 0;
	}	
}
