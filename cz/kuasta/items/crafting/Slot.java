package cz.kuasta.items.crafting;

/**
 * This class is intended for crafting, and saving inventory purposes.
*/
public class Slot{
	
	public int id;
	public int amount;
	
	public Slot(){}
	public Slot(int id, int amount){
		this.id = id;
		this.amount = amount;
	}
	
}