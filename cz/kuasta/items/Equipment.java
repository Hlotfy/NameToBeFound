package cz.kuasta.items;

/**
 * Any item, that can be equiped, must derive from this class.<p>
 * 
 * Every item automatically contains a Slot, where it belongs and is recognized by it.
 * Durability indicates the state of the armor. The lower durability, the lower protection.
*/
public class Equipment extends Item {

	public Equipment(){
		super();
		this.amount = 1;
		this.maxAmount = 1;
	}
	public Equipment(int id, String name, String flavorText, Quality quality, int buyPrice, int sellPrice, Slot slot, int durability, int maxDurability, Stats stats){
		super(id, name, flavorText, quality, 1, 1, buyPrice, sellPrice);
		
		this.durability = durability;
		this.maxDurability = maxDurability;
		this.slot = slot;
		this.stats = stats;
	}
	
	public enum Slot{
		HEAD(1), SHOULDERS(2), CHEST(3), WRIST(4), HANDS(5), BELT(6), LEGS(7), BOOTS(8), MAINHAND(9), OFFHAND(10), ONEHAND(11), /*TWOHAND(12),*/ RING(13);
		
		int value;
		private Slot(int i){
			value = i;
		}
	}
		
	private int durability, maxDurability;
	private Slot slot;
	private Stats stats;
	
	public int getMaxDurability() {
		return maxDurability;
	}
	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	public Slot getSlot() {
		return slot;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
}