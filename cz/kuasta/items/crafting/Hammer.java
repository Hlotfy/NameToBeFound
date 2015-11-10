package cz.kuasta.items.crafting;

/**
 * Basic hammer tool class.<br>
 * Other items, that are to have hammer properties, and are intended<p>
 * to be used as a tool, should derive from this class. 
*/
public class Hammer extends Tool {
	
	/**Should be used for derived constructors*/
	public Hammer(){
		super();
	}
	public Hammer(int id, String name, String flavorText, Quality quality, int buyPrice, int sellPrice){
		super(id, name, flavorText, quality, buyPrice, sellPrice);

	}
	
}