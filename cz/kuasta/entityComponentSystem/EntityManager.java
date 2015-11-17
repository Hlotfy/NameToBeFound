package cz.kuasta.entityComponentSystem;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 
 **/

/**
* EntityManager class, which manages entities and their components.
* 
* @Author Martin Vostal
*/
@SuppressWarnings("rawtypes")
public class EntityManager {
	
	public static Entity player;
	
	//POMOCNE? NA HOVNO
	private int freeId = 1;
	
	public static double
	STRENGTH_RATIO, INTELECT_RATIO, AGILITY_RATIO, STAMINA_RATIO, LUCK_RATIO,
	ATK_POWER_DIVIDE_RATIO, DODGE_DIVIDE_RATIO, HIT_CHANCE_DIVIDE_RATIO_STRENGTH, HIT_CHANCE_DIVIDE_RATIO_AGILITY;

	private static HashMap<Entity, HashMap<Class, Component>> components = new HashMap<Entity, HashMap<Class, Component>>();
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static ArrayList<Integer> updateNot = new ArrayList<Integer>();

	public EntityManager(){
		int id = newEntity();
		player = getEntity(id);
		
		STRENGTH_RATIO = 1.5;
		INTELECT_RATIO = 1.5;
		AGILITY_RATIO = 1.5;
		STAMINA_RATIO = 1.5;
		LUCK_RATIO = 1.5;
		
		ATK_POWER_DIVIDE_RATIO = 0.1;
		DODGE_DIVIDE_RATIO = 0.1;
		HIT_CHANCE_DIVIDE_RATIO_STRENGTH = 0.05;
		HIT_CHANCE_DIVIDE_RATIO_AGILITY = 0.05;
		
		/*STRENGTH_RATIO = ServerRates.STRENGTH_RATIO;
		INTELECT_RATIO = ServerRates.INTELECT_RATIO;
		AGILITY_RATIO = ServerRates.AGILITY_RATIO;
		STAMINA_RATIO = ServerRates.STAMINA_RATIO;
		LUCK_RATIO = ServerRates.LUCK_RATIO;
		
		ATK_POWER_DIVIDE_RATIO = ServerRates.ATK_POWER_DIVIDE_RATIO;
		DODGE_DIVIDE_RATIO = ServerRates.DODGE_DIVIDE_RATIO;
		HIT_CHANCE_DIVIDE_RATIO_STRENGTH = ServerRates.HIT_CHANCE_DIVIDE_RATIO_STRENGTH;
		HIT_CHANCE_DIVIDE_RATIO_AGILITY = ServerRates.HIT_CHANCE_DIVIDE_RATIO_AGILITY;*/
	}
	
	/**
	* Creates new entity with unique ID and gives it a reference to EntityManager.
	*/
	public int newEntity(){
		int id = getFreeGlobalId();
		Entity e = new Entity();
		e.id = id;
		
		entities.add(e);
		components.put(e, new HashMap<Class, Component>());
		
		return id;
	}
	
	/**
	* Kills target entity.
	* 
	* @param id entity ID
	*/
	public void killEntity(int id){
		components.get(getEntity(id)).clear();
		for(Entity e : entities){
			if(e.id == id){
				e = null;
				break;
			}
		}
	}
	
	/**
	* Adds component to an entity with the ID.
	* 
	* @param id entity ID
	* @param component added component/components
	*/
	public void addComponent(int id, Component component){
		try{
			if(getComponents().get(getEntity(id)) != null)
				getComponents().get(getEntity(id)).put(component.getClass(), component);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public void addComponents(int id, ArrayList<Component> components){
		components.forEach(
			(v) -> {
				try{
					if(getComponents().get(getEntity(id)) != null)
						getComponents().get(getEntity(id)).put(v.getClass(), v);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		);
	}
		
	/**
	* Returns next free ID for the newEntity().
	* 
	* @return ID for new entity
	*/
	private int getFreeGlobalId() {
		this.freeId++;
		return freeId;
	}
	
	public void update(){
		for(Entity e : entities){
			if(!updateNot.contains(e.id)){
				
			}
		}
	}
		
	public static Entity getEntity(int id){
		Entity result = null;
		
		for(Entity e : entities){
			if(e.id == id)
				result = e;
		}
		
		return result;
	}
	public static ArrayList<Integer> getUpdateNot() {
		return updateNot;
	}
	public static HashMap<Entity, HashMap<Class, Component>> getComponents() {
		return components;
	}
}