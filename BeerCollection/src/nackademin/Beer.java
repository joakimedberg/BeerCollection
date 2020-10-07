package nackademin;

/**
 * Representing a beer by its name, the beer type and its strength (percentage in alcohol).
 * @author Joakim Edberg
 *
 */
public class Beer  {
	
	private String name, type, strength;
	
	/**
	 * Constructs a Beer.
	 * @param name the name of the beer
	 * @param type what type of beer
	 * @param strength the alcoholic percentage
	 */
	public Beer (String name, String type, String strength) {
		this.name = name;
		this.type = type;
		this.strength = strength;
	}
	
	/**
	 * Returns the name of the beer.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the type of beer.
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Returns the strength (alcoholic percentage of the beer).
	 * @return the strength
	 */
	public String getStrength() {
		return strength;
	}
	
}
