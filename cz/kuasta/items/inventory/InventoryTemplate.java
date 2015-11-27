package cz.kuasta.items.inventory;

import java.util.ArrayList;

import cz.kuasta.items.Item;

/**
 * Iventory derivation class.
 * <p>
 * It realy doesn't matter, how you make your inventory,<br>
 * but it should contain functions of this interface. 
*/
public abstract class InventoryTemplate {

	/**Inputs items/item stacks
	 * 
	 * */
	public abstract void addItem(Item item);
	public abstract void addItems(ArrayList<Item> items);
	/**Removes/moves an items out of the inventory*/
	public abstract void removeItem(Slot slot);
	public abstract void removeItems(ArrayList<Slot> slots);
}
