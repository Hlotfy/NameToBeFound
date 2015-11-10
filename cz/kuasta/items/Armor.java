package cz.kuasta.items;

/**
 * Contains 
*/
public class Armor extends Equipment {
	
	public Armor(){
		super();
	}
	public Armor(int id, String name, String flavorText, Quality quality, int buyPrice, int sellPrice, Slot slot, int durability, int maxDurability, int armor, Stats stats){
		super(id, name, flavorText, quality, buyPrice, sellPrice, slot, durability, maxDurability, stats);
		
		this.armor = armor;
	}
	
	protected int armor;
				
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
}