package cz.kuasta.entityComponentSystem.components;

import java.util.HashMap;

import cz.kuasta.items.Equipment;
import cz.kuasta.items.Equipment.Slot;
import cz.kuasta.items.Stats;
import cz.kuasta.items.inventory.ListInventory;

public class PlayerComponent extends CharacterComponent{
	
	private String title;
	
	public PlayerComponent(){
		super();
	}
	public PlayerComponent(int charId, String title, String name, int level, Stats stats, ListInventory inventory){
		super(charId, name, level, stats, inventory);
		
		this.title = title;
	}
	public PlayerComponent(int charId, String title, String name, int level, cz.kuasta.items.Stats stats, ListInventory inventory, HashMap<Slot, Equipment> equip){
		super(charId, name, level, stats, inventory, equip);
		
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}