package cz.kuasta.items.advanced;

import cz.kuasta.items.Item;

public class CrystalCore extends Item {

	public CrystalCore() {
		super();
		
		this.amount = 1;
		this.maxAmount = 1;
	}

	public CrystalCore(int id, String name, String flavorText, Quality quality, int amount, int maxAmount, int buyPrice, int sellPrice) {
		super(id, name, flavorText, quality, 1, 1, buyPrice, sellPrice);
		
	}

}
