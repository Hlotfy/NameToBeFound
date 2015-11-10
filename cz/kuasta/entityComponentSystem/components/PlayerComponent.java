package cz.kuasta.entityComponentSystem.components;

import cz.kuasta.items.Equip;
import cz.kuasta.items.Stats;

public class PlayerComponent extends CharacterComponent{
	
	private String title;
	
	public PlayerComponent(){
		super();
	}
	public PlayerComponent(String name, int hp, int level, String title, Stats stats, Equip equip){
		super(name, hp, level, stats, equip);
		
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}