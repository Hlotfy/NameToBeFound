import java.util.ArrayList;

import cz.kuasta.binaryTree.BinaryTree;
import cz.kuasta.db.DB;
import cz.kuasta.entityComponentSystem.Component;
import cz.kuasta.entityComponentSystem.EntityManager;
import cz.kuasta.entityComponentSystem.components.CharacterComponent;
import cz.kuasta.entityComponentSystem.components.PlayerComponent;
import cz.kuasta.items.Equipment;
import cz.kuasta.items.Equipment.Slot;
import cz.kuasta.items.Item;
import cz.kuasta.items.Item.Quality;
import cz.kuasta.items.Stats;
import cz.kuasta.items.inventory.ListInventory;

public class NameToBeFound {
	
	public static DB db;
	private static EntityManager entityManager;
	
	public static void main(String[] args){
		db = new DB();
		entityManager = new EntityManager();
		
		
		
		int id = 0;
		ArrayList<Component> comps;
		
		//player
		id = getEntityManager().newEntity();
		comps = new ArrayList<Component>();
		
			
		comps.add(new PlayerComponent(
			"", "Kuasta", 100, 1,
			new Stats(5,5,5,5,0,0,0,0,0),
			new ListInventory())
		);
		getEntityManager().addComponents(id, comps);
				
		getEntityManager();
		Item item = new Equipment(1, "Cap", "Ordinary cap", Quality.COMMON, 5, 2, Slot.HEAD, 10, 10, new Stats(1,1,1,1,0,0,0,0,0));
		((PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(id)).get(PlayerComponent.class)).getInventory().addItem(item);
		
		item = new Equipment(1, "Cap", "Ordinary cap", Quality.COMMON, 5, 2, Slot.HEAD, 10, 10, new Stats(1,1,1,1,0,0,0,0,0));
		((PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(id)).get(PlayerComponent.class)).getInventory().addItem(item);
		
		
		/*((PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(id)).get(PlayerComponent.class)).equip(
			(Equipment) ((PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(id)).get(PlayerComponent.class)).getInventory().items.getValue(1).pop()
		);*/
		
		
		((PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(id)).get(PlayerComponent.class)).printEquip();
		((PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(id)).get(PlayerComponent.class)).printStats();
		
	}

	private static EntityManager getEntityManager() {
		return entityManager;
	}

	
}


