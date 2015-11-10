package cz.kuasta.items;

import java.util.ArrayList;

import cz.kuasta.items.crafting.Slot;

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
	public abstract void addItem(Class<? extends Item> item);
	public abstract void addItem(ArrayList<Class<? extends Item>> items);
	/**Removes/moves an items out of the inventory*/
	public abstract void removeItem(Slot slot);
	public abstract void removeItems(ArrayList<Slot> slots);
	public abstract void sortItems();
}
