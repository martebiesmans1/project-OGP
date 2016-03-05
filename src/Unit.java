import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @invar The name of each unit must be a valid name for any unit.
 * 		 | isValidName(getName())
 * @invar  The position of each unit must be a valid position for any unit.
 *       | isValidPosition(getPosition())
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | isValidWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit.
 *       | isValidStrength(getStrength())
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 * @invar  The number of hitpoints of each unit must be a valid number of hitpoints for any
 *         unit.
 *       | isValidNbHitpoints(getNbHitpoints())
 * @invar  The number of stamina points of each unit must be a valid number of stamina points for any
 *         unit.
 *       | isValidNbStaminaPoints(getNbStaminaPoints())
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 * 
 * @author Ellen & Marte
 * @version 1.0
 */
public class Unit {
	
	public Unit(double x, double y, double z, String name, 
			int weight, int strength, int agility, int toughness)
			throws IllegalArgumentException {
		this.setPosition(x,y,z);
		this.setName(name);
		this.setWeight(weight);
		this.setStrength(strength);
		this.setAgility(agility);
		this.setToughness(toughness);
		this.setNbStaminaPoints(this.getMaxStaminaPoints());
		this.setNbHitpoints(this.getMaxHitpoints());
		this.setOrientation((float) (Math.PI/2));
	}

	
	/**
	 * Return the position on the x-axis of this unit.
	 */
	@Basic @Raw
	public double getPositionX() {
		return this.x;
	}
	
	/**
	 * Return the position on the y-axis of this unit.
	 */
	@Basic @Raw
	public double getPositionY() {
		return this.y;
	}
	
	/**
	 * Return the position on the z-axis of this unit.
	 */
	@Basic @Raw
	public double getPositionZ() {
		return this.z;
	}
	
	/**
	 * Return the position on the x-axis of the cube that is occupied by this unit.
	 * @return	the position on the x-axis of this unit rounded down to an integer
	 * 			|result == (int) this.getPositionX()
	 */
	public int getCubePositionX() {
		return (int) this.getPositionX();
	}
	
	
	/**
	 * Return the position on the y-axis of the cube that is occupied by this unit.
	 * @return	the position on the y-axis of this unit rounded down to an integer
	 * 			|result == (int) this.getPositionY()
	 */
	public int getCubePositionY() {
		return (int) this.getPositionY();
	}

	
	/**
	 * Return the position on the z-axis of the cube that is occupied by this unit.
	 * @return	the position on the z-axis of this unit rounded down to an integer
	 * 			|result == (int) this.getPositionZ()
	 */
	public int getCubePositionZ() {
		return (int) this.getPositionZ();
	}

	
	/**
	 * Check whether the given position is a valid position for
	 * any unit.
	 * 
	 * @param	x
	 * 			position on the x-axis
	 * @param	y
	 * 			position on the y-axis
	 * @param	z
	 * 			position on the z-axis
	 * @return	false if at least one of the coordinates is outside the boundaries of the game world.
	 * 			| result == (! ((x<0) || (x>xMax) || (y<0) || (y>yMax) || (z<0) || (z>zMax)) )
	 */
	public static boolean isValidPosition(double x, double y, double z) {
		return (! ((x<0) || (x>=X_MAX) || (y<0) || (y>=Y_MAX) || (z<0) || (z>=Z_MAX)) );
	}
	
	private static final int X_MAX = 50;
	private static final int Y_MAX = 50;
	private static final int Z_MAX = 50;
	
	
	
	/**
	 * Set the position of this unit to the given coordinates.
	 * 
	 * @param	x
	 * 			the new position on the x-axis for this unit
	 * @param	y
	 * 			the new position on the y-axis for this unit
	 * @param	z
	 * 			the new position on the z-axis for this unit
	 * @post	the new position on the x-axis of this unit is equal to the given x-coordinate.
	 * 			|new.getPositionX() = x
	 * @post	the new position on the y-axis of this unit is equal to the given y-coordinate.
	 * 			|new.getPositionY() = y
	 * @post	the new position on the z-axis of this unit is equal to the given z-coordinate.
	 * 			|new.getPositionZ() = z
	 * @throws	IllegalArgumentException
	 * 			At least one of the given coordinates is not within the boundaries of the game world.
	 * 			| (! isValidPosition(x,y,z))
	 */
	@Raw
	public void setPosition(double x,double y, double z) 
			throws IllegalArgumentException {
		if (! isValidPosition(x,y,z))
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	/**
	 * Variables registering the position of this unit.
	 */
	private double x;
	private double y;
	private double z;
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Return the name of this unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;
	}

	
	/**
	 * Check whether the given name is a valid name for any unit.
	 * 
	 * @param	name
	 * 			The name to check.
	 * @return Each name is at least two characters long and must start with an
	 *         uppercase letter. Names can only use letters (both upper- and
	 *         lowercase), quotes (both single and double) and spaces.
	 *         |if (length(name) < 2)
	 *         |	result == false
	 *         |if (! isUpperCase(name.charAt(0)))
	 *         |	return == false
	 *         |TODO moet hier de for-loop uit de implementatie komen?
	 */
	public static boolean isValidName(String name) {
		if (name.length() < 2)
			return false;
		if (! Character.isUpperCase(name.charAt(0)))
			return false;
		
		//a loop over the name which changes VALID_CHARACTERS to false if it recognizes characters
		// that are not allowed.
		boolean VALID_CHARACTERS = true;
		for(int i = 0; i < name.length(); i++) {
	        if (   (! Character.isLetter(name.charAt(i)))      //is it a letter?
	        	&& (! (name.charAt(i) == '"'))                 //is it a double quote?
	        	&& (! (name.charAt(i) == '\''))                //is it a single quote/apostrophe?
	        	&& (! (name.charAt(i) == ' '))                 //is it a space?
	        	)
	            VALID_CHARACTERS = false;
		}
		return VALID_CHARACTERS;
	}

	
	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param	name
	 * 			The new name for this unit.
	 * @post	The name of this unit is equal to the given name.
	 * 			| new.getName() == name
	 * @throws	IllegalArgumentException
	 * 			The given name is not a valid name for this unit.
	 * 			| (! isValidName(name))
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	
	/**
	 * Variable registering the name of this unit.
	 */
	private String name;
	
	
	
	
	
	
	
	
	
	/**
	 * Return the strength of this unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.givenStrength;
	}

	/**
	 * Check whether the given strength is a valid strength for any unit.
	 * 
	 * @param givenStrength
	 *            The strength to check.
	 * @return | result == TODO
	 */
	public static boolean isValidStrength(int givenStrength) {
		return false;
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param givenStrength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength. | if
	 *       (isValidStrength(givenStrength)) | then new.getStrength() ==
	 *       givenStrength
	 */
	@Raw
	public void setStrength(int givenStrength) {
		if (isValidStrength(givenStrength))
			this.givenStrength = givenStrength;
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int givenStrength;

	
	
	
	
	




	
	/**
	 * Return the agility of this unit.
	 */
	@Basic @Raw
	public int getAgility() {
		return this.givenAgility;
	}
	
	/**
	 * Check whether the given agility is a valid agility for
	 * any unit.
	 *  
	 * @param  givenAgility
	 *         The agility to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidAgility(int givenAgility) {
		return false;
	}
	
	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param  givenAgility
	 *         The new agility for this unit.
	 * @post   If the given agility is a valid agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility.
	 *       | if (isValidAgility(givenAgility))
	 *       |   then new.getAgility() == givenAgility
	 */
	@Raw
	public void setAgility(int givenAgility) {
		if (isValidAgility(givenAgility))
			this.givenAgility = givenAgility;
	}
	
	/**
	 * Variable registering the agility of this unit.
	 */
	private int givenAgility;



		

	
	
	
	



	/**
	 * Return the weight of this unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.givenWeight;
	}
	
	/**
	 * Check whether the given weight is a valid weight for
	 * any unit.
	 *  
	 * @param  givenWeight
	 *         The weight to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidWeight(int givenWeight) {
		return false;
	}
	
	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param  givenWeight
	 *         The new weight for this unit.
	 * @post   If the given weight is a valid weight for any unit,
	 *         the weight of this new unit is equal to the given
	 *         weight.
	 *       | if (isValidWeight(givenWeight))
	 *       |   then new.getWeight() == givenWeight
	 */
	@Raw
	public void setWeight(int givenWeight) {
		if (isValidWeight(givenWeight))
			this.givenWeight = givenWeight;
	}
	
	/**
	 * Variable registering the weight of this unit.
	 */
	private int givenWeight;

	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
	/**
	 * Return the toughness of this unit.
	 */
	@Basic @Raw
	public int getToughness() {
		return this.givenToughness;
	}
	
	/**
	 * Check whether the given toughness is a valid toughness for
	 * any unit.
	 *  
	 * @param  givenToughness
	 *         The toughness to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidToughness(int givenToughness) {
		return false;
	}
	
	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param  givenToughness
	 *         The new toughness for this unit.
	 * @post   If the given toughness is a valid toughness for any unit,
	 *         the toughness of this new unit is equal to the given
	 *         toughness.
	 *       | if (isValidToughness(givenToughness))
	 *       |   then new.getToughness() == givenToughness
	 */
	@Raw
	public void setToughness(int givenToughness) {
		if (isValidToughness(givenToughness))
			this.givenToughness = givenToughness;
	}
	
	/**
	 * Variable registering the toughness of this unit.
	 */
	private int givenToughness;
	private int NbHitpoints;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int getMaxHitpoints() {
		return 2*this.getWeight()*this.getToughness()/100; // TODO round up
	}
	
	private int getMaxStaminaPoints() {
		return 2*this.getWeight()*this.getToughness()/100; // TODO round up
	}
	


	public void setNbHitpoints(int hitpoints) {
		this.NbHitpoints = 	hitpoints;
	}
	
	
	/**
	 * Return the number of hitpoints of this unit.
	 */
	@Basic @Raw
	public int getNbHitpoints() {
		return 2/100*this.getWeight()*this.getToughness(); //TODO round up to next integer
	}
	
	/**
	 * Check whether the given number of hitpoints is a valid number of hitpoints for
	 * any unit.
	 *  
	 * @param  number of hitpoints
	 *         The number of hitpoints to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidNbHitpoints(int givenNbHitpoints) {
		return false;
	}
	
	
	
	/**
	 * Variable registering the number of hitpoints of this unit.
	 */
	private int givenNbHitpoints;
		

	
	
	
	
	
	
	


	/**
	 * Return the number of stamina points of this unit.
	 */
	@Basic @Raw
	public int getNbStaminaPoints() {
		return this.givenNbStaminaPoints;
	}
	
	/**
	 * Check whether the given number of stamina points is a valid number of stamina points for
	 * any unit.
	 *  
	 * @param  number of stamina points
	 *         The number of stamina points to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidNbStaminaPoints(int givenNbStaminaPoints) {
		return false;
	}
	
	/**
	 * Set the number of stamina points of this unit to the given number of stamina points.
	 * 
	 * @param  givenNbStaminaPoints
	 *         The new number of stamina points for this unit.
	 * @pre    The given number of stamina points must be a valid number of stamina points for any
	 *         unit.
	 *       | isValidNbStaminaPoints(givenNbStaminaPoints)
	 * @post   The number of stamina points of this unit is equal to the given
	 *         number of stamina points.
	 *       | new.getNbStaminaPoints() == givenNbStaminaPoints
	 */
	@Raw
	public void setNbStaminaPoints(int givenNbStaminaPoints) {
		assert isValidNbStaminaPoints(givenNbStaminaPoints);
		this.givenNbStaminaPoints = givenNbStaminaPoints;
	}
	
	/**
	 * Variable registering the number of stamina points of this unit.
	 */
	private int givenNbStaminaPoints;
	
	
	
	
	
	
	
	
	
	
	


	
	/**
	 * Return the orientation of this unit.
	 */
	@Basic @Raw
	public float getOrientation() {
		return this.orientation;
	}
	
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any unit.
	 *  
	 * @param	theta
	 * 			The orientation to check.
	 * @return	true if and only if theta lies between 0 and 2*PI
	 * 			| result == (theta>=0 && theta<(2*Math.PI))
	*/
	public static boolean isValidOrientation(float theta) {
		return (theta>=0.0 && theta<(2.0*Math.PI));
	}
	
	
	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param	theta
	 *			The new orientation for this unit.
	 * @post	If the given orientation theta is a valid orientation,
	 *			the new orientation of this unit is equal to the given orientation theta.
	 *			| if (isValidOrientation(theta))
	 *			|   then new.getOrientation() == theta
	 * @post	If the given orientation theta lies outside the interval [0, 2*PI[,
	 * 			the new orientation of this unit is equal to the equivalent
	 * 			angle in this interval with the same sinus and cosinus.
	 * 			|TODO moeten hier ook de 2 while-lussen komen?
	 */
	@Raw
	public void setOrientation(float theta) {
		while ((! isValidOrientation(theta)) && theta<0)
			theta += 2.0*Math.PI;
		while ((! isValidOrientation(theta)) && theta>0)
			theta -= 2.0*Math.PI;
		this.orientation = theta;
	}
	
	
	/**
	 * Variable registering the orientation of this unit.
	 */
	private float orientation;

	
	
	
	
	
	
	
	
	
	
	
	
	
	//************************************************************************************************//
	
	
	/**
	 * 
	 * update the position and activity status of a Unit, based on that Unit's current position,
	 * attributes and a given duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 * @throws IllegalSecondsException
	 */
	public void advanceTime(float seconds) throws IllegalSecondsException{
		
	}
	
	
	
	
}
