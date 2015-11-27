package cz.kuasta.entityComponentSystem.components;

import java.util.HashMap;

import cz.kuasta.entityComponentSystem.Component;
import cz.kuasta.entityComponentSystem.EntityManager;
import cz.kuasta.items.Armor;
import cz.kuasta.items.Equipment;
import cz.kuasta.items.Equipment.Slot;
import cz.kuasta.items.Stats;
import cz.kuasta.items.Weapon;
import cz.kuasta.items.inventory.ListInventory;

public class CharacterComponent extends Component{
		
	private int charId;
	private String name;
	private int hp;
	private int level;
	private Stats baseStats;
	private Stats stats;
	private HashMap<Slot, Equipment> equip;
	private ListInventory inventory;
		
	@SuppressWarnings("null")
	public CharacterComponent(){
		this.name = null;
		this.hp = (Integer) null;
		this.level = (Integer) null;
		this.baseStats = null;
		this.equip = new HashMap<Slot, Equipment>();
		this.inventory = null;
		
		equip.put(Slot.HEAD, null);
		equip.put(Slot.CHEST, null);
		equip.put(Slot.WRIST, null);
		equip.put(Slot.HANDS, null);
		equip.put(Slot.BELT, null);
		equip.put(Slot.LEGS, null);
		equip.put(Slot.BOOTS, null);
		equip.put(Slot.MAINHAND, null);
		equip.put(Slot.OFFHAND, null);
		equip.put(Slot.RING, null);
	}
	public CharacterComponent(int charId, String name, int level, Stats baseStats, ListInventory inventory){
		this.charId = charId;
		this.name = name;
		this.level = level;
		this.stats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.baseStats = baseStats;
		this.inventory = inventory;
		this.equip = new HashMap<Slot, Equipment>();
		this.hp = getMaxHp();
		
		equip.put(Slot.HEAD, null);
		equip.put(Slot.CHEST, null);
		equip.put(Slot.WRIST, null);
		equip.put(Slot.HANDS, null);
		equip.put(Slot.BELT, null);
		equip.put(Slot.LEGS, null);
		equip.put(Slot.BOOTS, null);
		equip.put(Slot.MAINHAND, null);
		equip.put(Slot.OFFHAND, null);
		equip.put(Slot.RING, null);
	}
	public CharacterComponent(int charId, String name, int level, Stats baseStats, ListInventory inventory, HashMap<Slot, Equipment> equip){
		this.charId = charId;
		this.name = name;
		this.level = level;
		this.stats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0);
		this.baseStats = baseStats;
		this.inventory = inventory;
		this.equip = equip;
		this.hp = getMaxHp();
	}
	
	public void statsChange(){
		Stats result = new Stats(
				getStrength(),
				getIntelect(),
				getAgility(),
				getStamina(),
				getLuck(),
				0, 0, 0, 0
			);
		
		if(baseStats != null){
			result.add(this.baseStats);
		}
		
		result.add(getEquipmentStats());
		
		result.atkPower = (int) (result.strength * EntityManager.ATK_POWER_DIVIDE_RATIO);
		result.dodge = (int) (result.agility * EntityManager.DODGE_DIVIDE_RATIO);
		result.hit = (int) (result.strength * EntityManager.HIT_CHANCE_DIVIDE_RATIO_STRENGTH + result.agility * EntityManager.HIT_CHANCE_DIVIDE_RATIO_AGILITY);
		
		this.stats = result;
	}
	@SuppressWarnings("incomplete-switch")
	public void equip(Equipment item){
		Slot desiredSlot = null;
		
		if(item instanceof Armor){
			unEquip(item.getSlot());
			equip.put(item.getSlot(), item);
		}else if(item instanceof Weapon){
			switch(item.getSlot()){
				case MAINHAND:
					unEquip(Slot.MAINHAND);
					equip.put(Slot.MAINHAND, item);
					break;
				case ONEHAND:
					if(desiredSlot == Slot.OFFHAND){
						unEquip(Slot.OFFHAND);
						equip.put(Slot.OFFHAND, item);
					}else{
						unEquip(Slot.MAINHAND);
						equip.put(Slot.MAINHAND, item);
					}
					break;
				/*case TWOHAND:
					unEquip(Slot.MAINHAND);
					unEquip(Slot.OFFHAND);
					equip.put(Slot.MAINHAND, item);
					break;*/
			}
		}
		statsChange();
	}
	private void unEquip(Slot slot){
		Equipment tmp = equip.get(slot);
		equip.put(slot, null);
		inventory.addItem(tmp);
	}
	/***/
	public Stats getEquipmentStats(){
		Stats result = new Stats();
		
		equip.forEach(
			(k, v) -> {
				if(v != null && v.getStats() != null){
					result.add(v.getStats());
				}
			}
		);
		
		return result;
	}
	public int getHp() {
		return hp;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxHp() {
		return (10*level + getStamina()*3);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int getStrength() {
		return (int) (EntityManager.STRENGTH_RATIO * level);
	}
	private int getIntelect() {
		return (int) (EntityManager.INTELECT_RATIO * this.level);
	}
	private int getAgility() {
		return (int) (EntityManager.AGILITY_RATIO * this.level);
	}
	private int getStamina() {
		return (int) (EntityManager.STAMINA_RATIO * this.level);
	}
	private int getLuck() {
		return (int) (EntityManager.LUCK_RATIO * this.level);
	}
	public Stats getStats() {
		return stats;
	}
	public Stats getBaseStats() {
		return baseStats;
	}
	public int getCharId() {
		return charId;
	}
	public HashMap<Slot, Equipment> getEquip() {
		return equip;
	}
	public void setBaseStats(Stats baseStats) {
		this.baseStats = baseStats;
	}
	public ListInventory getInventory() {
		return inventory;
	}
	public void printEquip(){		
		equip.forEach(
			(k, v) -> {
				if(v != null)
					System.out.print(k.toString() + ": " + v.getName() + "\n");					
			}
		);
	}
	public void printStats(){		
		String result = "Strength: " + this.stats.strength + "\n";
		result += "Intelect: " + this.stats.intelect + "\n";
		result += "Agility: " + this.stats.agility + "\n";
		result += "Stamina: " + this.stats.stamina + "\n";
		result += "Luck: " + this.stats.luck + "\n";
		result += "Atack Power: " + this.stats.atkPower + "\n";
		result += "Dodge: " + this.stats.dodge + "\n";
		result += "Hit chance: " + this.stats.hit + "\n";
		
		System.out.println(result);
	}
}
