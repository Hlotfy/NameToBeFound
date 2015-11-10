package cz.kuasta.items.crafting;

import java.sql.SQLException;
import java.util.ArrayList;

import cz.kuasta.db.DB;
import cz.kuasta.items.Item;

/**Basic machine class.<p>
 * 
 * Intended as and derivation class for smelteries, anvils, alchemy tables and such.
*/
public abstract class Machine {
	
	private DB db;
	
	public Machine(DB db){
		this.db = db;
	}
	
	
	/**Main method to get the products from crafting.<br>
	 * 
	 * @param recipe  the recipe thats going to be used
	 * @param items  an array of items, mostly an inventory
	 * @return result  and array of crafted items
	 * @throws SQLException 
	*/
	public ArrayList<Item> craft (Recipe recipe, ArrayList<Item> items) throws SQLException{
		ArrayList<Item> result = new ArrayList<Item>(); 
		boolean craftable = true;
		int availableRepeats = 0, finalRepeats = 0;
		ArrayList<Slot> reqItems = recipe.getRequiredItems();
				
		
		for(Class<? extends Tool> t : recipe.getRequiredTools()){
			if(!items.contains(t))
				craftable = false;				
		}
		
		if(!Machine.class.isAssignableFrom(recipe.getMachine()))
				craftable = false;	
		
		int arrayId = 0, tmpRepeats = 0;
			
		for(int i = 0; i<reqItems.size() & craftable == true;i++){
			arrayId = getArrayIdById(reqItems.get(i).id, items);
			tmpRepeats = items.get(arrayId).getAmount() / reqItems.get(i).amount;
								
			if(tmpRepeats < 0 || availableRepeats < 0){
				craftable = false;
			}else{
				if(availableRepeats > tmpRepeats)
					availableRepeats = tmpRepeats;
			}
		}
		finalRepeats = availableRepeats;			
		
		if(craftable){
			arrayId = 0;
			
			for(int i = 0; i<reqItems.size() & craftable == true;i++){
				arrayId = getArrayIdById(reqItems.get(i).id, items);
				items.get(arrayId).setAmount(items.get(arrayId).getAmount() - (reqItems.get(i).amount * finalRepeats));
			}
		}
		
		if(craftable){
			for(Slot s : recipe.getProducts()){
				Item tmp = db.getItem(s.id, 0);
				int amount = s.amount * finalRepeats;
				
				if(amount > tmp.getMaxAmount()){
					do{
						tmp.setAmount(0);
						
						if(amount > tmp.getMaxAmount()){
							tmp.setAmount(tmp.getMaxAmount());
							amount -= tmp.getMaxAmount();
						}else{
							tmp.setAmount(amount);
							amount = 0;
						}
						
						result.add(tmp);					
					}while(amount > 0);
				}else{
					tmp.setAmount(amount);
					result.add(tmp);
				}
			}
		}
		
		return result;
	}	
	
	public static int getArrayIdById(int id, ArrayList<? extends Item> items){
		int result = 0;
		int i = 0;
		
		do{
			if(items.get(i).id == id)
				result = i;
			i++;
		}while(result == 0);
		
		return result;
	}
		
}