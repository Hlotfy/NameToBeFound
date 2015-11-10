package cz.kuasta.items.crafting;

import java.util.ArrayList;

import cz.kuasta.items.Item;

/**Basic recipe class.<br>
 * 
 * Contains a description and flavor text for the recipe.
*/
public class Recipe extends Item{	
		
	private String description;
	
	/**An array of items required (itemId, amount).*/
	private ArrayList<Slot> requiredItems;
	/**An array of required tools, for example hammer.*/
	private ArrayList<Class<? extends Tool>> requiredTools;
	/**Required machine class.*/
	private Class<? extends Machine> machine;
	public boolean learnable;
	private ArrayList<Slot> products;
	
	public Recipe(){
		super();
	}
	public Recipe(int id, String name, String flavorText, Quality quality, int amount, int maxAmount, int buyPrice, int sellPrice,
			String description, ArrayList<Slot> requiredItems, ArrayList<Class<? extends Tool>> requiredTools,
			Class<? extends Machine> machine, boolean learnable, ArrayList<Slot> products){
		
		super(id, name, flavorText, quality, amount, maxAmount, buyPrice, sellPrice);
		
		this.setDescription(description);
		this.setRequiredItems(requiredItems);
		this.setRequiredTools(requiredTools);
		this.setMachine(machine);
		this.learnable = learnable;
		this.setProducts(products);
	}
		
	public ArrayList<Slot> getRequiredItems() {
		return requiredItems;
	}
	public void setRequiredItems(ArrayList<Slot> requiredItems) {
		this.requiredItems = requiredItems;
	}
	public ArrayList<Class<? extends Tool>> getRequiredTools() {
		return requiredTools;
	}
	public void setRequiredTools(ArrayList<Class<? extends Tool>> requiredTools) {
		this.requiredTools = requiredTools;
	}
	public Class<? extends Machine> getMachine() {
		return machine;
	}
	public void setMachine(Class<? extends Machine> machine) {
		this.machine = machine;
	}
	public ArrayList<Slot> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Slot> products) {
		this.products = products;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}