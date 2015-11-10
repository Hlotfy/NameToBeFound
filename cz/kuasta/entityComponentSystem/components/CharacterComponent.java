package cz.kuasta.entityComponentSystem.components;

import cz.kuasta.entityComponentSystem.Component;
import cz.kuasta.entityComponentSystem.EntityManager;
import cz.kuasta.items.Equip;
import cz.kuasta.items.InventoryTemplate;
import cz.kuasta.items.Stats;

public class CharacterComponent extends Component{
		
	private String name;
	private int hp;
	private int level;
	private Stats stats;
	private Equip equip;
	private Class<? extends InventoryTemplate> inventory;
		
	@SuppressWarnings("null")
	public CharacterComponent(){
		this.name = null;
		this.hp = (Integer) null;
		this.level = (Integer) null;
		this.stats = null;
		this.equip = null;
		this.inventory = null;
	}
	public CharacterComponent(String name, int hp, int level, Stats stats, Equip equip){
		this.name = name;
		this.hp = hp;
		this.level = level;
		this.stats = stats;
		this.equip = equip;
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
		
		equip.getStats().add(result);
		
		result.atkPower += result.strength / EntityManager.ATK_POWER_DIVIDE_RATIO;
		result.dodge += result.agility / EntityManager.DODGE_DIVIDE_RATIO;
		result.hit += result.strength / EntityManager.HIT_CHANCE_DIVIDE_RATIO_STRENGTH + result.agility / EntityManager.HIT_CHANCE_DIVIDE_RATIO_AGILITY;
		
		this.stats = result;
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
		
}