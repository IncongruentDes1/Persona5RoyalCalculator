package personaFiveCalculator;

import java.util.ArrayList;

//Race.Lvl.Name.Inherits.St.Ma.En.Ag.Lu.Phys.Gun.Fire.Ice.Electric.Wind.Psychokinesis.Nuclear.Bless.Curse

public class persona {
	// Base traits
	String Race;
	Integer Level;
	String Name;
	Integer Number;
	
	// Inherits / Stats
	String Inherits;
	Integer St;
	Integer Ma;
	Integer En;
	Integer Ag;
	Integer Lu;
	
	//-Resistances
	String Phys;
	String Gun;
	String Fire;
	String Ice;
	String Electric;
	String Wind;
	String Psychokinesis;
	String Nuclear;
	String Bless;
	String Curse;
	
	//groupings
	public ArrayList<ArrayList<persona>> combos = new ArrayList<ArrayList<persona>>();
	
	public ArrayList<ability> allowedAbilities = new ArrayList<ability>();
	
	public ArrayList<ability> containedAbilities = new ArrayList<ability>();
	
	// - creation
	// Race|Lvl|Name|Inherits|St|Ma|En|Ag|Lu|Phys|Gun|Fire|Ice|Electric|Wind|Psychokinesis|Nuclear|Bless|Curse
	public persona(String race, Integer lvl, String name, String inherits, Integer st, Integer ma, Integer en, Integer ag, Integer lu,
			String phys, String gun, String fire, String ice, String electric, String wind, String psycho, String nuke, String bless, String curse, Integer number) {
		
		// - assigning section
		Race = race;
		Level = lvl;
		Name = name;
		Inherits = inherits;
		St = st;
		Ma = ma;
		En = en;
		Ag = ag;
		Lu = lu;
		Phys = phys;
		Gun = gun;
		Fire = fire;
		Ice = ice;
		Electric = electric;
		Wind = wind;
		Psychokinesis = psycho;
		Nuclear = nuke;
		Bless = bless;
		Curse = curse;
		Number = number;
		
	}
	
	public ArrayList<ArrayList<persona>> groupings() {
		return combos;
	}
	public String StatReader() {
		String stats = "";
		stats += (Name + "\n\n");
		stats += ("Level: " + Level +", Type: " + Race + "\n");
		stats += ("Inherits: " + Inherits + "\n");
		stats += ("Strength: " + St + ", Magic: " + Ma + ", Endurance: " + En + ", Agility: " + Ag + ", Luck: " + Lu);
		stats += ("Weaknesses and Buffs \n");
		stats += ("Physical: " +  Phys + ", Gun: " + Gun + ", Fire: " + Fire + ", Ice: " + Ice +  ", Electric: " + Electric + ", Wind:  " + Wind + ", Psychokinesis: "+ Psychokinesis + ", Nuclear: "+ Nuclear 
				+ ", Bless: " + Bless + ", Curse: " + Curse + "\n");
		return stats;
	}
	
	
	@Override
    public String toString() {
		String str = "";
		str += Number + ":" + Name + ", " + Race + ", " + Level;
		return Name;
	}

	
	//TODO
	public void abilityReading(ArrayList<ability> lst) {
		
		// - PHYS // - CHECKED
		if (Inherits.equals("Phys")) {
			for (int x = 0; x < lst.size(); x++) {
				if (lst.get(x).type.equals("Gun") || (lst.get(x).type.equals("Physical"))){
					   allowedAbilities.add(lst.get(x));
				}
			}
		}
		
		
		//-FIRE  //-CHECKED
		else if(Inherits.equals("Fire")) {
			for (int x = 0; x<lst.size(); x++) {
				if (!lst.get(x).type.equals("Ice")){
					allowedAbilities.add(lst.get(x));
				}
			}
			
		}
		
		// - ICE  // - CHECKED
		else if(Inherits.equals("Ice")) {
			for (int x = 0; x<lst.size(); x++) {
				if (!lst.get(x).type.equals("Fire")){
					allowedAbilities.add(lst.get(x));
				}
			}
			
		}
		//Electric  // - CHECKED
		else if(Inherits.equals("Electric")) {
			for (int x = 0; x<lst.size(); x++) {
				if (!lst.get(x).type.equals("Wind")){
					allowedAbilities.add(lst.get(x));
				}
			}
					
				}
		
		// - WIND  //-CHECKED
		else if(Inherits.equals("Wind")) {
			for (int x = 0; x<lst.size(); x++) {
				if (!lst.get(x).type.equals("Electric")){
					allowedAbilities.add(lst.get(x));
				}
			}
			
		}
		
		// - PSY  //-CHECKED
		else if(Inherits.equals("Psychokinesis")) {
			for (int x = 0; x<lst.size(); x++) {
				if (!lst.get(x).type.equals("Nuclear")){
					allowedAbilities.add(lst.get(x));
				}
			}
			
		}
		
		// - NUKE  //-CHECKED
		else if(Inherits.equals("Nuclear")) {
			for (int x = 0; x<lst.size(); x++) {
				if (!lst.get(x).type.equals("Psychokinesis")){
					allowedAbilities.add(lst.get(x));
				}
			}
			
		}
		
		// BLESS - no Phys, Gun, Curse, Ailment // - CHECKED
		else if(Inherits.equals("Bless")) {
			for (int x = 0; x<lst.size(); x++) {
				if ((!lst.get(x).type.equals("Physical"))&& (!lst.get(x).type.equals("Gun")) && (!lst.get(x).type.equals("Curse")) && (!lst.get(x).type.equals("Ailment"))){
					allowedAbilities.add(lst.get(x));
				}
			}
			
		}
		
		// - CURSE - no phy, Gun, bless , recovery // - CHECKED
		else if(Inherits.equals("Curse")) {
			for (int x = 0; x<lst.size(); x++) {
				if ((!lst.get(x).type.equals("Physical"))&& (!lst.get(x).type.equals("Gun")) && (!lst.get(x).type.equals("Bless")) && (!lst.get(x).type.equals("Healing"))){
					allowedAbilities.add(lst.get(x));
			}
			}
		}
		
	 // - No bless or recovery //-CHECKED
		else if(Inherits.equals("Ailment")) {
			for (int x = 0; x<lst.size(); x++) {
					if ( (!lst.get(x).type.equals("Bless")) && (!lst.get(x).type.equals("Healing"))){
						allowedAbilities.add(lst.get(x));
				}

			}
			
		}
		
		// - RECOVERY , no phys gun or curse  // - CHECKED
		else if(Inherits.equals("Recovery") ||(Inherits.equals("Healing"))) {
			for (int x = 0; x<lst.size(); x++) {
				if ((!lst.get(x).type.equals("Physical"))&& (!lst.get(x).type.equals("Gun")) && (!lst.get(x).type.equals("Curse"))){
					allowedAbilities.add(lst.get(x));
			}	
		}
		}
		
		// - CHECKED
		else if(Inherits.equals("Almighty")) {
			for (int x = 0; x<lst.size(); x++) {
				allowedAbilities.add(lst.get(x));
			}
			
		}
}
			
		
		
	
	
	//TODO
    public void addAbility() {
    	
    }

	
	
	

}
