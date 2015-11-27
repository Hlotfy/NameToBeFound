package cz.kuasta.items;

/**
 * Stores information about bonus stats of respective items(equipment).
 * 
 * @author Martin Vostal
 * @since 1.0
*/
public class Stats{
	
	public Stats(){
		this.strength = 0;
		this.intelect = 0;
		this.agility = 0;
		this.stamina = 0;
		this.luck = 0;
		this.atkPower = 0;
		this.dodge = 0;
		this.hit = 0;
		this.critChance = 0;
	}
	public Stats(int strength, int intelect, int agility, int stamina, int luck, int atkPower, float dodge, float hit, float critChance){
		this.strength = strength;
		this.intelect = intelect;
		this.agility = agility;
		this.stamina = stamina;
		this.luck = luck;
		this.atkPower = atkPower;
		this.dodge = dodge;
		this.hit = hit;
		this.critChance = critChance;
	}
	
	public void add(Stats add){
		this.strength += add.strength;
		this.intelect += add.intelect;
		this.agility += add.agility;
		this.stamina += add.stamina;
		this.luck += add.luck;
		this.atkPower += add.atkPower;
		this.dodge += add.dodge;
		this.hit += add.hit;
		this.critChance += add.critChance;
	}
	
	public int strength, intelect, agility, stamina, luck, atkPower;
	public float dodge, hit, critChance;
}
