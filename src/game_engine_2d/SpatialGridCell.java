package game_engine_2d;
import java.util.ArrayList;

public class SpatialGridCell {

	public BoundingBox boundingBox;
	public ArrayList<BoundingBox> subBoundingBoxes;
	public SpatialGridCell(int _l, int _r, int _t, int _b) {
		boundingBox = new BoundingBox(_l,_r,_t,_b);
		subBoundingBoxes = new ArrayList<BoundingBox>();
	}
	
}
