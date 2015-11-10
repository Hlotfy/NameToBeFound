package cz.kuasta.items;

/**
 * Basic class for item derivation.
 * <p>
 * Includes basic attributes for an item.
 * 
 * @author Martin Vostal
 * @since 1.0
*/
public class Item {
	
	public Item(){
		
	}
	public Item(int id, String name, String flavorText, Quality quality, int amount, int maxAmount, int buyPrice, int sellPrice){
		this.id = id;
		this.name = name;
		this.flavorText = flavorText;
		this.quality = quality;
		this.amount = amount;
		this.maxAmount = maxAmount;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}
	
	/**
	 * Describes item quality and every item has it defined.<br>
	 * Can be modified at will.
	*/
	public enum Quality{	
		POOR(1), COMMON(2), UNCOMMON(3), RARE(4), EPIC(5), LEGENDARY(6), MYTHIC(7);
		
		int value;
		Quality(int i){
			value = i;
		}		
	}
	
	/**Defined item display ID, by which is it recognized by recipes, crafting, quests and etc.*/
	public int id;
	/**Name displayed in inventories, crafting and looting.*/
	protected String name;
	/**A story addition to an item.<br>
	 * It should be displayed in the bottom of item description.*/
	protected String flavorText;
	protected Quality quality;
	/**Represents the exact amount that is present in a slot.*/
	protected int amount;
	/**Its the max amount of the same items a stack can hold.*/
	protected int maxAmount;
	/**Defines the basic price of an item.<br>
	 * If the item is stackable, this variable represents a price per one item in the stack.<p>
	 * This variable can be internally adjusted during the program, for example shop and others.*/
	protected int buyPrice, sellPrice;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlavorText() {
		return flavorText;
	}
	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}
	public Quality getQuality() {
		return quality;
	}
	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	public void setMaxAmount(int amount){
		this.maxAmount = amount;
	}
	public int getMaxAmount() {
		return maxAmount;
	}
	public int getAmount(){
		return amount;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	public int getSellPrice(){
		return sellPrice;
	}
	public void setSellPrice(int amount){
		this.sellPrice = amount;
	}
	public int getBuyPrice(){
		return buyPrice;
	}
	public void setBuyPrice(int amount){
		this.buyPrice = amount;
	}
}