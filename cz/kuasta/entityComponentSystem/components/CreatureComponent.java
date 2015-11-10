package cz.kuasta.entityComponentSystem.components;

import cz.kuasta.entityComponentSystem.Component;

public class CreatureComponent extends Component {
	
	public AI ai;
	protected CreatureType creatureType;
	protected CreatureFamily creatureFamily;
	protected Rank rank;
	
	public enum CreatureType{
		BEAST(1), DEMON(2), ELEMENTAL(3), UNDEAD(4), HUMANOID(5), MECHANICAL(6);
		
		int value;
		CreatureType(int i){
			this.value = i;
		}
	}
	public enum CreatureFamily{
		WOLF(1), SPIDER(2), BEAR(3), CAT(4);
		
		int value;
		CreatureFamily(int i){
			this.value = i;
		}
	}
	public enum Rank{
		Normal(1), Elite(2), Rare(3), Boss(4);
		
		int value;
		Rank(int i){
			this.value = i;
		}
	}
	
	public CreatureComponent(){
		
	}
	public CreatureComponent(AI ai, CreatureFamily creatureFamily, CreatureType creatureType, Rank rank){
		this.ai = ai;
		this.creatureFamily = creatureFamily;
		this.creatureType = creatureType;
		this.rank = rank;
	}
	
	public CreatureType getCreatureType(){
		return creatureType;
	}
	public CreatureFamily getCreatureFamily(){
		return creatureFamily;
	}
	public Rank getRank(){
		return rank;
	}
}
