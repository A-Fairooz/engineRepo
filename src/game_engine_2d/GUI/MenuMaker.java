package game_engine_2d.GUI;

import java.util.ArrayList;

import game_engine_2d.GameObject;
import game_engine_2d.GameScreen;
import processing.core.PApplet;
import processing.core.PVector;
import sun.applet.Main;
import game_engine_2d.ALIGNMENT;
import game_engine_2d.DIRECTION;



public class MenuMaker extends GameObject{
	
	//Variables
	public ArrayList<String> menuItems = new ArrayList<String>();
	
	
	private int w = 160, h = 30, padding = 15 ;
	
	public DIRECTION direction = DIRECTION.HORIZONTAL;
	public ALIGNMENT alignment = ALIGNMENT.BOTTOM_LEFT;
	
	
	public MenuMaker(PApplet p) {
		super(p);
		this.menuItems.add("Start");
		this.menuItems.add("Level Edit");
		this.menuItems.add("Quit");
		
		
	}
	
	public MenuMaker(PApplet p, ArrayList<GameScreen> _screens) {
		super(p);
		for(int i = 0;i <_screens.size(); i++ ) {
			this.menuItems.add(_screens.get(i).name);
		}
	}
	public void add_menu_item(String label) {
		this.menuItems.add(label);
	}
	
	@Override
	public void start() {
		
		if(this.alignment == ALIGNMENT.CENTRE) 
		{
			this.transform.position.x = parent.width/2 - w/2;
			this.transform.position.y = parent.height/2 - h* this.menuItems.size();
		}
		else 
		{
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
		for(int i = 0; i < this.menuItems.size(); i++) {
			parent.rectMode(PApplet.CORNER);
			parent.fill(53,255,35);
			parent.noStroke();			
			if(this.direction == DIRECTION.VERTICAL) {
				parent.rect(0, i * h, w, h);
			}else {
				parent.rect(i*w, 0, w, h);
			}
			parent.fill(0);
			parent.noStroke();			
			PVector textPosition;
			if(this.direction == DIRECTION.VERTICAL) {
				textPosition = new PVector(this.padding,h/2 + 5+ i * h);
			}else {
				textPosition = new PVector(i * w +this.padding,h*0.7f);
			}
			parent.text((String)this.menuItems.get(i), textPosition.x, textPosition.y);
		}
		parent.popMatrix();
	}
	
	
}
