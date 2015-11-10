package cz.kuasta.items;

import java.util.HashMap;

/***/
public class Equip {
	
	private enum Slot{
		HEAD, CHEST, WRIST, GLOVES, BELT, LEGS, BOOTS, WEAPON, RING;
	}
	
	private HashMap<Slot,Equipment> equip;
	
	public Equip(){
		equip.put(Slot.HEAD, null);
		equip.put(Slot.CHEST, null);
		equip.put(Slot.WRIST, null);
		equip.put(Slot.GLOVES, null);
		equip.put(Slot.BELT, null);
		equip.put(Slot.LEGS, null);
		equip.put(Slot.BOOTS, null);
		equip.put(Slot.WEAPON, null);
		equip.put(Slot.RING, null);
	}
	
	/***/
	public Stats getStats(){
		Stats result = new Stats();
		
		equip.forEach(
			(k, v) -> {
				if(v != null && v.getStats() != null){
					v.getStats().add(result);
				}
			}
		);
		
		return result;
	}
	
}
