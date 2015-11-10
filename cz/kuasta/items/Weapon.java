package cz.kuasta.items;

/**
 * For weapon derivation.
 * @author Martin Vostal
 * @since 1.0
*/
public class Weapon extends Equipment {
	
	public Weapon(){
		super();
	}
	public Weapon(int id, String name, String flavorText, Quality quality, int buyPrice, int sellPrice, Slot slot, int durability, int maxDurability, Stats stats, int dmgLow, int dmgHigh){
		super(id, name, flavorText, quality, buyPrice, sellPrice, slot, durability, maxDurability, stats);
		
		this.dmgLow = dmgLow;
        this.dmgHigh = dmgHigh;
	}
	
	protected int dmgLow, dmgHigh;

    public int getdmgLow(){
        return dmgLow;
    }
    public void setdmgLow(int dmgLow){
        this.dmgLow = dmgLow;
    }
    public int getdmgHigh(){
        return dmgHigh;
    }
    public void setdmgHigh(int dmgHigh){
        this.dmgHigh = dmgHigh;
    }
    public int getDmgMid(){
    	return (dmgHigh + dmgLow)/2;
    }
}