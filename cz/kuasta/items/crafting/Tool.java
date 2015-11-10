package cz.kuasta.items.crafting;

import cz.kuasta.items.Item;

public abstract class Tool extends Item{
	
	public Tool(){
		super();
	    this.maxAmount = 1;
        this.amount = 1;
    }
    public Tool(int id, String name, String flavorText, Quality quality, int buyPrice, int sellPrice){
        super(id, name, flavorText, quality, 1, 1, buyPrice, sellPrice);
        
    }
	
}