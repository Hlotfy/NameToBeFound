package cz.kuasta.items.inventory;

import java.util.ArrayList;
import java.util.Stack;

import cz.kuasta.binaryTree.BinaryTree;
import cz.kuasta.items.Equipment;
import cz.kuasta.items.Item;
import cz.kuasta.items.crafting.Slot;

/**
 * 
 * 
 * 
*/
public class ListInventory extends InventoryTemplate {

	public BinaryTree<Item> items = new BinaryTree<Item>();
	
	public ListInventory() {
		super();
	}
	
	@Override
	public void addItem(Item item) {		
		if(item != null){
			if(items.contains(item.id)){
				Stack<Item> instances = items.getValue(item.id);
				
				if(item instanceof Equipment){
					instances.push(item);
				}else{
					if(instances.firstElement().getAmount() < instances.firstElement().getMaxAmount() && item.getAmount() <= (instances.firstElement().getMaxAmount() - instances.firstElement().getAmount())){
						instances.firstElement().setAmount(instances.firstElement().getAmount() + item.getAmount());
					}else{
						item.setAmount(item.getAmount() - (instances.firstElement().getMaxAmount() - instances.firstElement().getAmount()));
						instances.firstElement().setAmount(instances.firstElement().getMaxAmount());
						instances.push(item);
					}
				}
				
				items.setValue(item.id, instances);
			}else{
				items.addInstance(item);
			}
		}
	}
	@Override
	/**
	 * To be overwriten!!!
	*/
	public void addItems(ArrayList<Item> items) {
		
		/*for(int x = 0;x<items.size();x++){
			ArrayList<Item> instances = this.items.getValue(items.get(x).id);
			
			if(instances == null){
				this.items.addInstance(items.get(x));
			} else {
				for(int i = 0;i < instances.size() && items.get(x).getAmount() >= 0;i++){
					//for instances with amount smaller than maxAmount to fill
					//item amount < amount gap
					if (instances.get(i).getAmount() < items.get(x).getMaxAmount() && items.get(x).getAmount() < (instances.get(i).getMaxAmount() - instances.get(i).getAmount())){
						instances.get(i).setAmount(instances.get(i).getAmount() + items.get(x).getAmount());
					//item amount > amount gap
					}else if(instances.get(i).getAmount() < items.get(x).getMaxAmount() && items.get(x).getAmount() >= (instances.get(i).getMaxAmount() - instances.get(i).getAmount())){
						items.get(x).setAmount(items.get(x).getAmount() - (instances.get(i).getMaxAmount() - instances.get(i).getAmount()));
						 instances.get(i).setAmount(items.get(x).getMaxAmount());
					}else if((i+1) == instances.size() && items.get(x).getAmount() > 0){
						instances.add(items.get(x));
					} 
				}
			}		
		}*/
	}
	@Override
	public void removeItem(Slot slot){
		
	}
	@Override
	public void removeItems(ArrayList<Slot> slots) {
		
	}
	
}
