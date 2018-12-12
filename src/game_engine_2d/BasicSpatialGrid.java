package game_engine_2d;


import java.util.ArrayList;
import game_engine_2d.GameManager;
import processing.core.*;

public class BasicSpatialGrid {
	class Coord{
		int x,y;
		public Coord(int _x, int _y) {
			x= _x;
			y=_y;
		}
		public String ToString() {
			return x +"," + y;
		}
	}
	
	int outOfBounds = 0;
	private int height, gridLengthX, gridLengthY, gridSize;
	private int gridRes = 2;
	public Coord globalMin = new Coord(9999999, 9999999);
	public Coord globalMax =  new Coord(-9999999, -9999999);
	private ArrayList<SpatialGridCell> spatialGridCells;
	
	public BasicSpatialGrid(int h, int _gridRes) {
		height = h;
		gridRes = _gridRes;
		
		defineMinMax();
		makeSpatialGrid();
		populateGrid();
	}
	
	private void defineMinMax() {
		for(int i = 0; i < GameManager.gameBoundingBoxes.size(); i++) {
			BoundingBox bb = GameManager.gameBoundingBoxes.get(i);
			if(bb.left < globalMin.x) {
				globalMin.x = (int)bb.left;
				}
			
			if(bb.top<globalMin.y) {
				globalMin.y= (int)bb.top;
			}
			
			if(bb.right > globalMax.x) {
				globalMax.x = (int)bb.right;
			}
			
			if(bb.bottom > globalMax.y) {
				globalMax.y = (int)bb.bottom;
			}
		}

	}

	private void makeSpatialGrid() {
		gridSize = height/gridRes;
		gridLengthX = PApplet.floor(globalMax.x/gridSize) +100;
		gridLengthY = PApplet.floor(globalMax.y/gridSize) +100;
		spatialGridCells = new ArrayList<SpatialGridCell>();
		for(int x = 0; x <=gridLengthX; x++) {
			for(int y=0; y<=gridLengthY;y++){
				int l = x * gridSize;
				int r = (x+1)*gridSize;
				int t = y * gridSize;
				int b = (y +1)*gridSize;
				spatialGridCells.add(new SpatialGridCell(l,r,t,b));
			}
		}
		
	}
	
	private void populateGrid() {
		for(int i = 0; i < GameManager.gameBoundingBoxes.size(); i ++) {
			BoundingBox bb = GameManager.gameBoundingBoxes.get(i);
			addToGrid(bb);
		}
	}
	
	private void addToGrid(BoundingBox _bb) {
		Coord pos = gridCoordinates(_bb.right, _bb.bottom);
		int index = pos.x * gridLengthX + pos.y;
		addByIndexTo(index, _bb);
		pos = gridCoordinates(_bb.left, _bb.bottom);
		index = pos.x * gridLengthX+ pos.y;
		addByIndexTo(index,_bb);
		pos = gridCoordinates(_bb.left, _bb.top);
		index = pos.x * gridLengthX + pos.y;
		addByIndexTo(index, _bb);
		pos = gridCoordinates (_bb.right, _bb.top);
		index = pos.x * gridLengthX + pos.y;
		addByIndexTo(index, _bb);
	}
	
	private void addByIndexTo(int index, BoundingBox _bb) {
		if (index < spatialGridCells.size()) {
			SpatialGridCell b = spatialGridCells.get(index);
			boolean retval = b.subBoundingBoxes.contains(_bb);
			if(!retval) b.subBoundingBoxes.add(_bb);
		}
		else {
			outOfBounds++;
		}
	}
	private Coord gridCoordinates(float x, float y) {
		int gridPosX = PApplet.abs((int)PApplet.ceil(((globalMin.x -x)/ gridSize)));
		int gridPosY = PApplet.abs((int)PApplet.ceil(((globalMin.y -y)/ gridSize)));
		return new Coord(gridPosX, gridPosY);
	}
	public ArrayList<BoundingBox> queryGrid(BoundingBox _bb){
		Coord pos = gridCoordinates(_bb.right, _bb.bottom);
		int index = pos.x * gridLengthX + pos.y;
		if (spatialGridCells.size() < index) {
			PApplet.println( pos.x +" * " + gridLengthX + " + " + pos.y);
			PApplet.println("Player out of bounds! pos:"+ pos.ToString() + " _bb:" + _bb.toString());
			PApplet.println("index = "+index + " spatialGridCells.size()="+spatialGridCells.size());
			index = 0;
		}
		return spatialGridCells.get(index).subBoundingBoxes;
	}
	
	
	
	
	
	
}
