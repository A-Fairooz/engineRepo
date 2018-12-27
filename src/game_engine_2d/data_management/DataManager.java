package game_engine_2d.data_management;

import game_engine_2d.ProcessingEntity;
import game_engine_2d.GameObject;
import game_engine_2d.Tile;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
public class DataManager extends ProcessingEntity {
	public JSONObject game_data;
	public String load_game_file = "game.json";
	private String dataFolder = "data/";
	public DataManager(PApplet p) {
		super(p);
		this.name = "data manager";

	}
	public DataManager(PApplet p, String _filename) {
		super(p);
		this.load_game_file = _filename;
		this.load_data();
	}
	public void load_data() {
		

		game_data = parent.loadJSONObject(dataFolder+load_game_file);

	}
	public void save_game(JSONObject _game_data) {
		this.game_data = _game_data;
		parent.println("save file " + load_game_file);
		parent.saveJSONObject(this.game_data, "data/"+load_game_file);
	}
	public void save_tiles_json(ArrayList<GameObject> saveGameObjects, String _object_name) {
		JSONArray json_list = new JSONArray();
		for (int i = 0; i < saveGameObjects.size(); i++) {
			GameObject g_obj = saveGameObjects.get(i);
			Tile tile;
			try {
				tile = (Tile)g_obj;
			}catch (Exception E)
			   {
				parent.println("object not tile " + g_obj.name);
				continue;
			   }
			JSONObject json = new JSONObject();
			json.setInt("id", i);
			json.setFloat("x", tile.transform.position.x);
			json.setFloat("y", tile.transform.position.y);
			json.setFloat("w", tile.size.x);
			json.setFloat("h", tile.size.y);
			json_list.setJSONObject(i, json);
			parent.println("save "+i+ " position:"+tile.transform.position.toString());
		}
		JSONObject data = new JSONObject();
		data.setJSONArray(_object_name, json_list);
		this.save_game(data);
		parent.println("save level! " + _object_name);
	}

}