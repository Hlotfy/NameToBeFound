package cz.kuasta.db;

import java.util.HashMap;

import cz.kuasta.items.Equipment;
import cz.kuasta.items.Equipment.Slot;
import cz.kuasta.items.Stats;
import cz.kuasta.items.inventory.ListInventory;

public class CharacterData extends TableData{
	public int charId, level;
	public String title, name;
	public Stats stats;
	public ListInventory inventory;
	public HashMap<Slot, Equipment> equip;
}
