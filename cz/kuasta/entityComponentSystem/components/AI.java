package cz.kuasta.entityComponentSystem.components;

public class AI {
	
	public enum Event{
		EnterCombat(1), LeaveCombat(2), DamageTaken(3), TargetCastSpell(4), TargetDodged(5), TargetCriticalHit(6), TargetDied(7), UnitParried(8), UnitDodged(9), UnitCritHit(10), UnitDied(11);
		
		int value;
		Event(int i){
			this.value = i;
		}
	}
		
	public AI(){
		
	}
		
	public void procEvent(Event event, int entityId){
		switch(event){
			case DamageTaken:
				
				break;
			case EnterCombat:
				
				break;
			case LeaveCombat:
				
				break;
			case TargetCastSpell:
				
				break;
			case TargetCriticalHit:
				
				break;
			case TargetDied:
				
				break;
			case TargetDodged:
				
				break;
			case UnitCritHit:
				
				break;
			case UnitDied:
				
				break;
			case UnitDodged:
				
				break;
			case UnitParried:
				
				break;
		}
	}
}