package cz.kuasta.items.inventory;

import java.util.ArrayList;
import java.util.Stack;

import cz.kuasta.binaryTree.BinaryTree;
import cz.kuasta.items.Equipment;
import cz.kuasta.items.Item;

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
				Stack<Item> instances = this.items.getValue(item.id);
				
				if(item instanceof Equipment){
					instances.push(item);
				}else{
					if(instances.firstElement().getAmount() < instances.firstElement().getMaxAmount()
						&& item.getAmount() <= (instances.firstElement().getMaxAmount() - instances.firstElement().getAmount()))
					{
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
	 * 
	*/
	public void addItems(ArrayList<Item> items) {
		for(Item i : items){
			if(i != null){
				if(items.contains(i.id)){
					Stack<Item> instances = this.items.getValue(i.id);
					
					if(i instanceof Equipment){
						instances.push(i);
					}else{
						if(instances.firstElement().getAmount() < instances.firstElement().getMaxAmount()
							&& i.getAmount() <= (instances.firstElement().getMaxAmount() - instances.firstElement().getAmount()))
						{
							instances.firstElement().setAmount(instances.firstElement().getAmount() + i.getAmount());
						}else{
							i.setAmount(i.getAmount() - (instances.firstElement().getMaxAmount() - instances.firstElement().getAmount()));
							instances.firstElement().setAmount(instances.firstElement().getMaxAmount());
							instances.push(i);
						}
					}
					this.items.setValue(i.id, instances);
				}else{
					this.items.addInstance(i);
				}
			}
		}
	}
	@Override
	public void removeItem(Slot slot){
		
	}
	@Override
	public void removeItems(ArrayList<Slot> slots) {
		
	}
	
	/*public void removeNode(int id){
		items.removeNode(id);
	}*/
	
}
