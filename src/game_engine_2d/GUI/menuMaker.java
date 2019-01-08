package game_engine_2d.GUI;

import java.util.ArrayList;

import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import processing.core.PApplet;
import processing.core.PVector;
import sun.applet.Main;
import game_engine_2d.ALIGNMENT;
import game_engine_2d.DIRECTION;



public class menuMaker extends GameObject{
	
	
	
	
	
	public ArrayList<String> menu_options = new ArrayList<String>();
	public ArrayList <String> menuItems = new ArrayList<String>();
	public ArrayList<String> menuIstems = new ArrayList<String>();
	private int w = 160, h = 30, padding = 15 ;
	
	public DIRECTION direction = DIRECTION.HORIZONTAL;
	public ALIGNMENT alignment = ALIGNMENT.BOTTOM_LEFT;
	
	
	public menuMaker(PApplet p) {
		super(p);
		this.menu_options.add("Start");
		this.menu_options.add("Level Edit");
		this.menu_options.add("Quit");
		
		
	}
	
	public menuMaker(PApplet p, ArrayList<GameScreen> _screens) {
		super(p);
		for(int i = 0;i <_screens.size(); i++ ) {
			this.menu_options.add(_screens.get(i).name);
		}
	}
	public void add_menu_item(String label) {
		this.menu_options.add(label);
	}
	
	@Override
	public void start() {
		
		if(this.alignment == ALIGNMENT.CENTRE) {
			this.transform.position.x = parent.width/2 - w/2;
			this.transform.position.y = parent.height/2 - h* this.menu_options.size();
		}
		else {
			this.transform.position.x = this.padding;
			this.transform.position.y = this.padding;
		}
		
	}
	
	@Override
	public void update() {}
	
	@Override
	public void render() {
		parent.pushMatrix();
		parent.translate(this.transform.position.x, this.transform.position.y);
		for(int i = 0; i < this.menu_options.size(); i++) {
			parent.rectMode(PApplet.CORNER);
			parent.fill(0);
			parent.stroke(255);
			if(this.direction == DIRECTION.VERTICAL) {
				parent.rect(0, i * h, w, h);
			}else {
				parent.rect(i*w, 0, w, h);
			}
			parent.fill(255);
			parent.noStroke();
			PVector textPos;
			if(this.direction == DIRECTION.VERTICAL) {
				textPos = new PVector(this.padding,h/2 + 5+ i * h);
			}else {
				textPos = new PVector(i * w +this.padding,h*0.7f);
			}
			parent.text((String)this.menu_options.get(i), textPos.x, textPos.y);
		}
		parent.popMatrix();
	}
	
	
}
