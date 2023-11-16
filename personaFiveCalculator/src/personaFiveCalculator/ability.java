package personaFiveCalculator;

public class ability {
	String name;
	String synopsis;
	String cost;
	String type;
	Integer num;
	
	public ability(Integer Num, String Name, String Synopsis, String Cost, String Type) {
	           name = Name;
	           synopsis = Synopsis;
	           cost = Cost;
	           type = Type;
	           num = Num;
	}
	
	public String toString() {
		return (num+ ":" + name + ":" + synopsis + ":" + cost + ":" + type );
	}
	

}
