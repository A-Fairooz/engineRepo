package game_engine_2d.GUI;

import java.util.ArrayList;

import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import processing.core.PApplet;


public class menuMaker extends GameObject{
	
	
	
	
	
	private ArrayList<String> menu_options = new ArrayList<String>();
	
	private int w = 160, h = 30;
	public menuMaker(PApplet p) {
		super(p);
		this.menu_options.add("Start");
		this.menu_options.add("Level Edit");
		
	}
	
	public menuMaker(PApplet p, ArrayList<GameScreen> _screens) {
		super(p);
		for(int i = 0;i <_screens.size(); i++ ) {
			this.menu_options.add(_screens.get(i).name);
		}
	}
	
	
	@Override
	public void start() {
		this.transform.position.x = parent.width/2;
		this.transform.position.y = parent.height/2;
	}
	
	@Override
	public void update() {}
	
	@Override
	public void render() {
		parent.pushMatrix();
		parent.translate(this.transform.position.x, this.transform.position.y);
		for(int i = 0; i < this.menu_options.size(); i++) {
			parent.fill(0);
			parent.stroke(255);
			parent.rect(-15, i *h, w, h);
			parent.fill(255);
			parent.noStroke();
			parent.text((i+1) + ": " + this.menu_options.get(i), 0, h/2 + 5 + i *h);
		}
		parent.popMatrix();
	}
	
	
}
