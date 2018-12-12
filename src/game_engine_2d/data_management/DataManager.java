package game_engine_2d.data_management;

import game_engine_2d.ProcessingEntity;
import processing.core.PApplet;
import processing.data.JSONObject;

public class DataManager extends ProcessingEntity{
	public JSONObject game_data;
	public String load_game_file = "game.json";
	public DataManager(PApplet p) {
		super(p);
	}
	
	public DataManager(PApplet p, String _filename) {
		super(p);
		this.load_game_file = _filename;
	}
	
	
	public void load_data() {
		game_data = parent.loadJSONObject(load_game_file);
		
		int id = game_data.getInt("id");
		
		String name = game_data.getString("name");
		
		PApplet.println(id + ", " + name);
	}
	
	public void save_game(JSONObject _game_data) {
		this.game_data = _game_data;
		parent.saveJSONObject(this.game_data, load_game_file);
	}
		
}
