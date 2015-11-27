package cz.kuasta.items.crafting;

/**
 * Basic quill tool class.<br>
 * Other items, that are to have quill properties, and are intended<p>
 * to be used as a tool, should derive from this class. 
*/
public class Quill extends Tool {

	/**Should be used for derived constructors*/
	public Quill(){
		super();
	}
	public Quill(int id, String name, String flavorText, Quality quality, int buyPrice, int sellPrice){
		super(id, name, flavorText, quality, buyPrice, sellPrice);

	}
	
}
