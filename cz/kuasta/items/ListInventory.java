package cz.kuasta.items;

import java.util.ArrayList;

import cz.kuasta.items.InventoryTemplate;
import cz.kuasta.items.crafting.Slot;

public class ListInventory extends InventoryTemplate {

	public ListInventory() {
		super();
	}
	
	@Override
	public void addItem(Class<? extends Item> item) {
		
	}
	@Override
	public void addItem(ArrayList<Class<? extends Item>> items) {

	}
	@Override
	public void removeItem(Slot slot) {

	}
	@Override
	public void removeItems(ArrayList<Slot> slots) {
		
	}
	@Override
	public void sortItems() {
		
	}
	
}
