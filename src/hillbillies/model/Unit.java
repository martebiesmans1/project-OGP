package hillbillies.model;

import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * repository
 * https://github.com/MarteBiesmans/project-OGP.git
 * 
 * names
 * Ellen Anthonissen
 * Marte Biesmans
 * 
 * course of studies
 * 2Bbi, objectgericht programmeren
 */

/**
 * 
 * A class of units involving a position, name, sttrength, agility, toughness
 * and default behaviour.
 * 
 * @invar The name of each unit must be a valid name for any unit. |
 *        isValidName(getName())
 * @invar The position of each unit must be a valid position for any unit. |
 *        isValidPosition(getPosition())
 * @invar The weight of each unit must be a valid weight for any unit. |
 *        this.canHaveAsWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit. |
 *        isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any unit. |
 *        isValidAgility(getAgility())
 * @invar The toughness of each unit must be a valid toughness for any unit. |
 *        isValidToughness(getToughness())
 * @invar The number of hitpoints of each unit must be a valid number of
 *        hitpoints for any unit. | canHaveAsHitpoints(getNbHitpoints())
 * @invar The number of stamina points of each unit must be a valid number of
 *        stamina points for any unit. |
 *        canHaveAsStaminaPoints(getNbStaminaPoints())
 * @invar The orientation of each unit must be a valid orientation for any unit.
 *        | isValidOrientation(getOrientation())
 * @invar Each unit must have proper materials. | hasProperMaterials()
 * 
 * 
 * @author Ellen & Marte
 * @version 1.0
 */
public class Unit extends TimeVariableObject {

	private static final Random RANDOM_GEN = new Random();
	/**
	 * constants for the minimum and maximum values of primary attributes
	 * (weight, strength, agility and toughness)
	 */
	private static final int MIN_INIT_VAL_PRIMARY_ATTRIBUTE = 25;
	private static final int MAX_INIT_VAL_PRIMARY_ATTRIBUTE = 100;
	private static final int MIN_VAL_PRIMARY_ATTRIBUTE = 1;
	private static final int MAX_VAL_PRIMARY_ATTRIBUTE = 200;

	/**
	 * @post The name of this new unit is equal to the given name. |
	 *       new.getName() == name
	 * @post The strength of this new unit is equal to the given strength. |
	 *       new.getStrength() == weight
	 * @post The agility of this new unit is equal to the given agility. |
	 *       new.getAgility() == Agility
	 * @post The toughness of this new unit is equal to the given thoughness. |
	 *       new.getThoughness() == thoughness
	 * @post The weight of this new unit is equal to the given weight. |
	 *       new.getWeight() == weight
	 * @post The default behaviour is enabled if this is necessary |
	 *       new.defaultBehaviour == enableDefaultBehaviour
	 * @post The time that the unit is busy is 0 seconds. | new.getBusyTime == 0
	 * @post The position of the unit is equal to the given position. |
	 *       new.getPosition == Position(x, y, z).toCube()
	 * @post The hitpoints of this new unit are equal to the maximum possible
	 *       hitpoints. | new.getHitpoints() == hitpoints
	 * @post The stamina points of this new unit are equal to the maximum
	 *       possible stamina points. | new.getStaminaPoints() == staminaPoints
	 * @post This new unit has no materials yet. | new.getNbMaterials() == 0
	 * 
	 */
	public Unit(double x, double y, double z, String name, int strength, int agility, int toughness, int weight,
			boolean enableDefaultBehaviour) throws IllegalArgumentException {

		// name
		this.setName(name);

		// position and orientation
		this.setPosition(x, y, z);
		this.setOrientation((float) (Math.PI / 2.0));

		// primary attributes
		if (strength < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setStrength(MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
		else if (strength > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setStrength(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setStrength(strength);

		if (agility < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setAgility(MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
		else if (agility > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setAgility(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setAgility(agility);

		if (weight < this.getMinimumInitValWeight())
			this.setWeight(this.getMinimumInitValWeight());
		else if (weight > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setWeight(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setWeight(weight);

		if (toughness < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setToughness(MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
		else if (toughness > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setToughness(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setToughness(toughness);

		// hitpoints and stamina points
		this.setStaminaPoints(this.getMaxStaminaPoints());
		this.setHitpoints(this.getMaxHitpoints());

		// activity and busy time
		this.setActivity(Activity.NONE);
		this.setBusyTime(0);

		// behaviour
		this.defaultBehaviour = enableDefaultBehaviour;

		// moveTo and moveToAdjacent
		this.setMoveToCube(null);
		this.setMoveToAdjacent(null);

		// world, faction, experience points and level
		this.world = null;
		this.faction = null;
		this.setExperiencePoints(0);
		this.setLevel(0);
	}

	public boolean isValidUnit() {
		return (canHaveAsPosition(this.getPosition()) && isValidName(this.getName())
				&& isValidStrength(this.getStrength()) && isValidAgility(this.getAgility())
				&& canHaveAsWeight(this.getWeight()) && isValidToughness(this.getToughness())
				&& isValidOrientation(this.getOrientation()) && this.getFaction() != null && this.getWorld() != null);
	}

	/**
	 * Return the position of this unit.
	 */
	@Basic
	@Raw
	public Position getPosition() {
		return this.position;
	}

	/**
	 * check wether the given position is valid for this unit
	 * 
	 * @param position
	 *            the position to check
	 * @return false if the given position equals null
	 * @return false if the given position is not legal in the world of this
	 *         unit
	 * @return true if this unit doesn't have a world (position cannot be
	 *         invalid)
	 */
	public boolean canHaveAsPosition(Position position) {
		if (position == null)
			return false;
		if (world != null)
			return position.isValidForObjectIn(this.getWorld());
		return true;
	}

	/**
	 * Set the position of this unit to the given coordinates.
	 * 
	 * @param x
	 *            the new position on the x-axis for this unit
	 * @param y
	 *            the new position on the y-axis for this unit
	 * @param z
	 *            the new position on the z-axis for this unit
	 * @post the new position on the x-axis of this unit is equal to the given
	 *       x-coordinate. |new.getPosition().getXValue = x
	 * @post the new position on the y-axis of this unit is equal to the given
	 *       y-coordinate. |new.getPosition().getYValue() = y
	 * @post the new position on the z-axis of this unit is equal to the given
	 *       z-coordinate. |new.getPosition().getZValue() = z
	 * @throws IllegalArgumentException
	 *             At least one of the given coordinates is not within the
	 *             boundaries of the game world. | (! isValidPosition(x,y,z))
	 */
	@Raw
	public void setPosition(double x, double y, double z) throws IllegalArgumentException {
		Position position = new Position(x, y, z);
		if (canHaveAsPosition(position))
			this.position = position;
	}

	@Raw
	public void setPosition(double x, double y, double z, Cube cube) throws IllegalArgumentException {
		Position position = new Position(x, y, z, cube);
		if (canHaveAsPosition(position))
			this.position = position;
	}

	@Raw
	public void setPosition(Position position) throws IllegalArgumentException {
		if (canHaveAsPosition(position))
			if (this.getActivity() != Activity.FALLING && this.shouldStartFallingAt(position.getCube()))
				this.setActivity(Activity.FALLING);
		this.position = position;
	}

	private Position position;

	public Cube getCube() {
		return this.getPosition().getCube();
	}

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
	 * @param name
	 *            The name to check.
	 * @return false if given name points to null | if (name==null) | result ==
	 *         false
	 * @return Each name is at least two characters long and must start with an
	 *         uppercase letter. Names can only use letters (both upper- and
	 *         lowercase), quotes (both single and double) and spaces. |if
	 *         (length(name) < 2) | result == false |if
	 *         (!isUpperCase(name.charAt(0))) | return == false |
	 */
	public static boolean isValidName(String name) {
		if (name == null)
			return false;
		if (name.length() < 2)
			return false;
		if (!Character.isUpperCase(name.charAt(0)))
			return false;

		// a loop over the name which changes VALID_CHARACTERS to false if it
		// recognizes characters
		// that are not allowed.
		boolean VALID_CHARACTERS = true;
		for (int i = 0; i < name.length(); i++) {
			if ((!Character.isLetter(name.charAt(i))) // is it a letter?
					&& (!(name.charAt(i) == '"')) // is it a double quote?
					&& (!(name.charAt(i) == '\'')) // is it a single
													// quote/apostrophe?
					&& (!(name.charAt(i) == ' ')) // is it a space?
			)
				VALID_CHARACTERS = false;
		}
		return VALID_CHARACTERS;
	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param name
	 *            The new name for this unit.
	 * @post The name of this unit is equal to the given name. | new.getName()
	 *       == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for this unit. |
	 *             (!isValidName(name))
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
		return this.strength;
	}

	/**
	 * Check whether the given strength is a valid strength for any unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return true if and only if strength lies between the minimum and maximum
	 *         value for primary attributes. |result == ((strength >=
	 *         MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidStrength(int strength) {
		return ((strength >= MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for this unit, the new
	 *       strength of this unit is equal to the given strength. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength
	 * @post If the given strength lies beyond the limits of the specified
	 *       minimum and maximum value, the new strength will be this limit
	 *       value. | if (strength < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getStrength() == MIN_VAL_PRIMARY_ATTRIBUTE | if (strength >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getStrength() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
		if (strength < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.strength = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (strength > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.strength = MAX_VAL_PRIMARY_ATTRIBUTE;
		this.setWeight(this.getWeight());
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength;

	/**
	 * Return the agility of this unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given agility is a valid agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return true if and only if agility lies between the minimum and maximum
	 *         value for primary attributes. | result == ((agility >=
	 *         MIN_VAL_PRIMARY_ATTRIBUTE) && (agility <= |
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidAgility(int agility) {
		return ((agility >= MIN_VAL_PRIMARY_ATTRIBUTE) && (agility <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post If the given agility is a valid agility for this unit, the new
	 *       agility of this unit is equal to the given agility. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility
	 * @post If the given agility lies beyond the limits of the specified
	 *       minimum and maximum value, the new agility will be this limit
	 *       value. | if (agility < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getAgility() == MIN_VAL_PRIMARY_ATTRIBUTE | if (agility >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getAgility() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		if (agility < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.agility = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (agility > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.agility = MAX_VAL_PRIMARY_ATTRIBUTE;
		this.setWeight(this.getWeight());
	}

	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;

	/**
	 * Return the weight of this unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	public int getTotalWeight() {
		int weightSoFar = this.getWeight();
		for (Material material : this.materials)
			weightSoFar += material.getWeight();
		return weightSoFar;
	}

	/**
	 * Return the minimum value for the primary attribute weight.
	 * 
	 * @return the minimum of (strength + agility) / 2 and the minimum value for
	 *         primary attribute
	 */
	public int getMinimumWeight() {
		int minimumWeight = (int) Math.ceil((double) ((this.strength + this.agility) / 2.0));
		if (minimumWeight < MIN_VAL_PRIMARY_ATTRIBUTE)
			return MIN_VAL_PRIMARY_ATTRIBUTE;
		return minimumWeight;
	}

	/**
	 * Return the minimum initial value for the primary attribute weight.
	 * 
	 * @return the minimum of (strength + agility) / 2 and the minimum initial
	 *         value for primary attributes
	 */
	public int getMinimumInitValWeight() {
		int minimumInitValWeight = getMinimumWeight();
		if (minimumInitValWeight < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			return MIN_INIT_VAL_PRIMARY_ATTRIBUTE;
		return minimumInitValWeight;
	}

	/**
	 * Check whether the given weight is a valid weight for any unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return true if and only if weight lies between the minimum and maximum
	 *         value for primary attributes. |result == ((weight >=
	 *         this.getMinimumWeight()) && (weight <= |
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public boolean canHaveAsWeight(int weight) {
		return ((weight >= this.getMinimumWeight()) && (weight <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post If the given weight is a valid weight for this unit, the new weight
	 *       of this unit is equal to the given weight. | if
	 *       (this.canHaveAsWeight(weight)) | then new.getWeight() == weight
	 * @post If the given weight lies beyond the limits of the specified minimum
	 *       and maximum value, the new weight will be this limit value. | if
	 *       (weight < this.getMinimumWeight()) | then new.getWeight() ==
	 *       this.getMinimumWeight() | if (weight > MAX_VAL_PRIMARY_ATTRIBUTE) |
	 *       then new.getWeight() == MAX_VAL_PRIMARY_ATTRIBUTE
	 * @post If after the change of weight, hitpoints and stamina points aren't
	 *       legal anymore, they will be changed to a legal value. | if
	 *       (!new.canHaveAsHitpoints(this.getHitpoints()) ) | then
	 *       new.getHitpoints() == new.getMaxHitpoints() | if
	 *       (!new.canHaveAsStaminaPoints(this.getStaminaPoints()) ) | then
	 *       new.getStaminaPoints() == new.getMaxStaminaPoints()
	 */
	@Raw
	public void setWeight(int weight) {
		if (this.canHaveAsWeight(weight))
			this.weight = weight;
		if (weight < this.getMinimumWeight())
			this.weight = this.getMinimumWeight();
		if (weight > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.weight = MAX_VAL_PRIMARY_ATTRIBUTE;

		if (!this.canHaveAsHitpoints(this.getHitpoints()))
			this.setHitpoints(this.getMaxHitpoints());
		if (!this.canHaveAsStaminaPoints(this.getStaminaPoints()))
			this.setStaminaPoints(this.getMaxStaminaPoints());
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int weight;

	/**
	 * Return the toughness of this unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given toughness is a valid toughness for any unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return true if and only if toughness lies between the minimum and
	 *         maximum value for primary attributes. |result == ((toughness >=
	 *         MIN_VAL_PRIMARY_ATTRIBUTE) && (toughness <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidToughness(int toughness) {
		return ((toughness >= MIN_VAL_PRIMARY_ATTRIBUTE) && (toughness <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post If the given toughness is a valid toughness for this unit, the new
	 *       toughness of this unit is equal to the given toughness. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness
	 * @post If the given toughness lies beyond the limits of the specified
	 *       minimum and maximum value, the new toughness will be this
	 *       limitvalue. | if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getToughness() == MIN_VAL_PRIMARY_ATTRIBUTE | if (toughness >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getToughness() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE
	 * @post If after the change of toughness, hitpoints and stamina points
	 *       aren't legal anymore, they will be changed to a legal value. | if
	 *       (!new.canHaveAsHitpoints(this.getHitpoints()) ) | then
	 *       new.getHitpoints() == new.getMaxHitpoints() | if
	 *       (!new.canHaveAsStaminaPoints(this.getStaminaPoints()) ) | then
	 *       new.getStaminaPoints() == new.getMaxStaminaPoints()
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
		if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.toughness = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (toughness > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.toughness = MAX_VAL_PRIMARY_ATTRIBUTE;

		if (!this.canHaveAsHitpoints(this.getHitpoints()))
			this.setHitpoints(this.getMaxHitpoints());
		if (!this.canHaveAsStaminaPoints(this.getStaminaPoints()))
			this.setStaminaPoints(this.getMaxStaminaPoints());
	}

	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;

	/**
	 * Return the hitpoints of this unit.
	 */
	@Basic
	@Raw
	public double getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * Return the maximum number of hitpoints of this unit.
	 * 
	 * @return the maximum hitpoints based on the weight and thoughness of this
	 *         unit
	 */
	public int getMaxHitpoints() {
		return (int) Math.ceil((2 * this.getWeight() * this.getToughness()) / 100.0);
	}

	/**
	 * Check whether the given hitpoints are valid hitpoints for this unit.
	 * 
	 * @param hitpoints
	 *            The hitpoints to check.
	 * @return true if and only if hitpoints lie between zero and the maximum
	 *         value | result == ( (hitpoints >= 0) && (hitpoints <=
	 *         this.getMaxHitpoints()) )
	 */
	public boolean canHaveAsHitpoints(double hitpoints) {
		return ((hitpoints >= 0) && (hitpoints <= this.getMaxHitpoints()));
	}

	/**
	 * Set the hitpoints of this unit to the given hitpoints.
	 * 
	 * @param hitpoints
	 *            The new hitpoints for this unit.
	 * @pre The given hitpoints must be valid hitpoints for this unit. |
	 *      this.canHaveAsHitpoints(hitpoints)
	 * @post The hitpoints of this unit is equal to the given hitpoints. |
	 *       new.getHitpoints() == hitpoints
	 */
	@Raw
	public void setHitpoints(double hitpoints) {
		assert this.canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
	}

	/**
	 * Variable registering the hitpoints of this unit.
	 */
	private double hitpoints;

	/**
	 * Return the stamina points of this unit.
	 */
	@Basic
	@Raw
	public double getStaminaPoints() {
		return this.staminaPoints;
	}

	/**
	 * Return the maximum number of stamina points of this unit.
	 * 
	 * @return the maximum stamina points based on the weight and thoughness of
	 *         this unit
	 */
	public int getMaxStaminaPoints() {
		return (int) Math.ceil((2 * this.getWeight() * this.getToughness()) / 100.0);
	}

	/**
	 * Check whether the given stamina points are a valid value for this unit.
	 * 
	 * @param staminaPoints
	 *            The staminaPoints to check.
	 * @return true if and only if stamina points lie between zero and the
	 *         maximum value | result == ( (staminaPoints >= 0) &&
	 *         (staminaPoints <= this.getMaxHitpoints()) )
	 */
	public boolean canHaveAsStaminaPoints(double staminaPoints) {
		return ((staminaPoints >= 0) && (staminaPoints <= this.getMaxStaminaPoints()));
	}

	/**
	 * Set the stamina points of this unit to the given stamina points.
	 * 
	 * @param staminaPoints
	 *            The new stamina points for this unit.
	 * @pre The given stamina points must be valid stamina points for this unit.
	 *      | this.canHaveAsStaminaPoints(staminaPoints)
	 * @post The stamina points of this unit are equal to the given stamina
	 *       points. | new.getStaminaPoints() == staminaPoints
	 */
	@Raw
	public void setStaminaPoints(double staminaPoints) {
		assert this.canHaveAsStaminaPoints(staminaPoints);
		this.staminaPoints = staminaPoints;
	}

	/**
	 * Variable registering the stamina points of this unit.
	 */
	private double staminaPoints;

	/**
	 * Return the orientation of this unit.
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * Check whether the given orientation is a valid orientation for any unit.
	 * 
	 * @param theta
	 *            The orientation to check.
	 * @return true if and only if theta lies between 0 and 2*PI | result ==
	 *         (theta>=0 && theta<(2*Math.PI))
	 */
	public static boolean isValidOrientation(double theta) {
		return (theta >= 0.0 && theta < (2.0 * Math.PI));
	}

	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param theta
	 *            The new orientation for this unit.
	 * @post If the given orientation theta is a valid orientation, the new
	 *       orientation of this unit is equal to the given orientation theta. |
	 *       if (isValidOrientation(theta)) | then new.getOrientation() == theta
	 * @post If the given orientation theta lies outside the interval [0, 2*PI[,
	 *       the new orientation of this unit is equal to the equivalent angle
	 *       in this interval with the same sinus and cosinus.
	 */
	@Raw
	public void setOrientation(double theta) {
		while ((!isValidOrientation(theta)) && theta < 0)
			theta += 2.0 * Math.PI;
		while ((!isValidOrientation(theta)) && theta > 0)
			theta -= 2.0 * Math.PI;
		this.orientation = theta;
	}

	/**
	 * Variable registering the orientation of this unit.
	 */
	private double orientation;

	public int getExperiencePoints() {
		return this.experiencePoints;
	}

	public boolean isValidExperiencePoints(int points) {
		return (points >= 0);
	}

	public void setExperiencePoints(int points) {
		if (isValidExperiencePoints(points))
			this.experiencePoints = points;
	}

	private int experiencePoints;

	private int getLevel() {
		return this.level;
	}

	private boolean isValidLevel(int level) {
		return level >= 0;
	}

	private void setLevel(int level) {
		if (isValidLevel(level))
			this.level = level;
	}

	private int level;

	/**
	 * 
	 * update the position, activity status, hitpoints and stamina points of a
	 * Unit, based on that Unit's current position, attributes and a given
	 * duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 * @throws IllegalArgumentException
	 *             the seconds are not in the interval [0,0.2[
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException {
		// if (seconds < 0 || seconds >= 0.2)
		// throw new IllegalArgumentException();

		this.busyTimeMin(seconds);

		if (this.isAttacking())
			attacking(seconds);

		if (this.isMoving() && (this.getMoveToAdjacent() != null || isFalling()))
			moving(seconds);

		else if (this.isWorking())
			working(seconds);

		else if (this.isResting())
			resting(seconds);

		if (this.isBeingUseless() && this.canStartDefaultBehaviour())
			beingUseless(seconds);

		while (this.getExperiencePoints() - (10 * this.getLevel()) > 10)
			this.levelUp();

		// TODO: of doen bij setHitpoints(hitpoints) -> if hitpoints == 0 ->
		// this.die()?
		if (this.getHitpoints() == 0)
			this.die();
	}

	private void attacking(float seconds) {
		if (this.getBusyTime() == 0)
			this.setActivity(Activity.NONE);
	}

	private void moving(float seconds) {
		if (this.isFalling()) {
			Position next = new Position(this.getPosition().getRealX(), this.getPosition().getRealY(),
					this.getPosition().getRealZ() - 3.0 * seconds);
			if (!this.getCube().equals(next.getCube()))
				this.setHitpoints(Math.min(0, this.getHitpoints() - 10));
			this.setPosition(next);
			if (this.canStopFalling())
				this.setActivity(Activity.NONE);

		} else {

			if (this.isSprinting()) {
				double stamina = this.getStaminaPoints() - seconds * 10;
				if (stamina > 0)
					this.setStaminaPoints(stamina);
				else {
					this.setStaminaPoints(0);
					this.stopSprinting();
				}

				Position moveDiff = this.getMoveToAdjacent().getCenter().min(this.getPosition());

				double moveDistance = Math.sqrt(moveDiff.getRealX() * moveDiff.getRealX()
						+ moveDiff.getRealY() * moveDiff.getRealY() + moveDiff.getRealZ() * moveDiff.getRealZ());

				double xVelocity = this.getMovementSpeed() * moveDiff.getRealX() / moveDistance;
				double yVelocity = this.getMovementSpeed() * moveDiff.getRealY() / moveDistance;
				double zVelocity = this.getMovementSpeed() * moveDiff.getRealZ() / moveDistance;

				Position next = new Position(this.getPosition().getRealX() + xVelocity * seconds,
						this.getPosition().getRealY() + yVelocity * seconds,
						this.getPosition().getRealZ() + zVelocity * seconds);

				this.setOrientation(Math.atan2(yVelocity, xVelocity));

				Position diffNext = this.getMoveToAdjacent().getCenter().min(next);
				if ((moveDiff.getRealX() == 0 || Math.signum(moveDiff.getRealX()) != Math.signum(diffNext.getRealX()))
						&& (moveDiff.getRealY() == 0
								|| Math.signum(moveDiff.getRealY()) != Math.signum(diffNext.getRealY()))
						&& (moveDiff.getRealZ() == 0
								|| Math.signum(moveDiff.getRealZ()) != Math.signum(diffNext.getRealZ()))) {
					this.setPosition(this.getMoveToAdjacent().getCenter());
					this.setMoveToAdjacent(null);
					this.setExperiencePoints(this.getExperiencePoints() + 1);

					// Check whether the unit is moving to a cube far away (not
					// an
					// adjacent cube)
					if (this.getMoveToCube() != null) {
						findNextCubeInPath();
					}

					// Check whether the unit is not pathfinding. If this is
					// true,
					// it stops moving.
					if (this.getMoveToCube() == null) {
						this.setActivity(Activity.NONE);
					}
				} else
					this.setPosition(next);
			}
		}
	}

	private void working(float seconds) {
		if (this.getBusyTime() == 0) {

			if (this.getNbMaterials() > 0) {
				Iterator<Material> iter = this.materials.iterator();
				Material material = (Material) iter.next();
				this.removeMaterial(material, this.getCube().getCenter());

			} else if (this.getWorld().getTerrainType(this.getCube()) == TerrainType.WORKSHOP
					&& this.getWorld().getBouldersIn(this.getCube()).size() > 0
					&& this.getWorld().getLogsIn(this.getCube()).size() > 0) {
				Iterator<Boulder> iterBoulder = this.getWorld().getBouldersIn(this.getCube()).iterator();
				Boulder boulder = (Boulder) iterBoulder.next();
				Iterator<Log> iterLog = this.getWorld().getLogsIn(this.getCube()).iterator();
				Log log = (Log) iterLog.next();
				this.addMaterial(boulder);
				this.addMaterial(log);

			} else if (this.getWorld().getBouldersIn(this.getCube()).size() > 0) {
				Iterator<Boulder> iterBoulder = this.getWorld().getBouldersIn(this.getCube()).iterator();
				Boulder boulder = (Boulder) iterBoulder.next();
				this.addMaterial(boulder);

			} else if (this.getWorld().getLogsIn(this.getCube()).size() > 0) {
				Iterator<Log> iterLog = this.getWorld().getLogsIn(this.getCube()).iterator();
				Log log = (Log) iterLog.next();
				this.addMaterial(log);

			} else if (this.getWorld().getTerrainType(this.getCube()) == TerrainType.WOOD) {
				this.getWorld().setTerrainType(this.getCube(), TerrainType.AIR);
				if (RANDOM_GEN.nextDouble() < 0.25) 
					this.getWorld().addMaterial(new Log(this.getWorld(), this.getCube().getCenter()));
				//TODO: check in elke cube rondom of er geen units moeten beginnen vallen
				
			} else if (this.getWorld().getTerrainType(this.getCube()) == TerrainType.ROCK) {
				this.getWorld().setTerrainType(this.getCube(), TerrainType.AIR);
				if (RANDOM_GEN.nextDouble() < 0.25) 
					this.getWorld().addMaterial(new Boulder(this.getWorld(), this.getCube().getCenter()));
				//TODO: check in elke cube rondom of er geen units moeten beginnen vallen
			}
			this.setActivity(Activity.NONE);
			this.setExperiencePoints(this.getExperiencePoints() + 10);
		}
	}

	private void resting(float seconds) {
		if (this.getHitpoints() != this.getMaxHitpoints()) {
			double hitpoints = this.getHitpoints() + seconds * this.getToughness() / (200 * 0.2);
			this.setHitpoints(Math.min(hitpoints, this.getMaxHitpoints()));
		} else if (this.getStaminaPoints() != this.getMaxStaminaPoints()) {
			double stamina = this.getStaminaPoints() + seconds * this.getToughness() / (100 * 0.2);
			this.setStaminaPoints(Math.min(stamina, this.getMaxStaminaPoints()));
		} else {
			this.setActivity(Activity.NONE);
		}

		if (this.getBusyTime() == 0) {
			this.canStopResting = true;
		}
	}

	private void beingUseless(float seconds) {
		int randomGetal = RANDOM_GEN.nextInt(4);
		if (randomGetal == 0 && this.getPotentialEnemies().size() != 0) {
			Set<Unit> potentialEnemies = this.getPotentialEnemies();
			Iterator<Unit> iter = potentialEnemies.iterator();
			Unit enemie = (Unit) iter.next();
			this.attack(enemie);
		} else {
			randomGetal = RANDOM_GEN.nextInt(3);
			if (randomGetal == 0) {
				int x = RANDOM_GEN.nextInt(this.getWorld().getNbCubesX());
				int y = RANDOM_GEN.nextInt(this.getWorld().getNbCubesY());
				int z = RANDOM_GEN.nextInt(this.getWorld().getNbCubesZ());
				moveTo(x, y, z);

			} else if (randomGetal == 1) {
				this.work();

			} else {
				this.rest();
				double hitpointsTime = (this.getMaxHitpoints() - this.getHitpoints()) * 200 * 0.2 / this.getToughness();
				double staminaTime = (this.getMaxStaminaPoints() - this.getStaminaPoints()) * 100 * 0.1
						/ this.getToughness();
				this.setBusyTime(hitpointsTime + staminaTime);
			}
		}
	}

	private Set<Unit> getPotentialEnemies() {
		Set<Cube> sameOrAdjacentCubes = this.getCube().getAllAdjacentCubes(this.getWorld());
		sameOrAdjacentCubes.add(this.getCube());
		Set<Unit> enemiesSoFar = new HashSet<Unit>();
		for (Cube cube : sameOrAdjacentCubes) {
			Set<Unit> unitsInCube = this.getWorld().getUnitsInCube(cube);
			for (Unit unit : unitsInCube) {
				if (this.getFaction() != unit.getFaction() && !unit.isFalling())
					enemiesSoFar.add(unit);
			}
		}
		return enemiesSoFar;
	}

	private void levelUp() {
		if (this.getAgility() != MAX_VAL_PRIMARY_ATTRIBUTE || this.getStrength() != MAX_VAL_PRIMARY_ATTRIBUTE
				|| this.getToughness() != MAX_VAL_PRIMARY_ATTRIBUTE) {
			boolean leveledUp = false;
			while (!leveledUp) {
				int randomGetal = RANDOM_GEN.nextInt(3);
				if (randomGetal == 0) {
					if (this.getAgility() != MAX_VAL_PRIMARY_ATTRIBUTE) {
						this.setAgility(this.getAgility() + 1);
						leveledUp = true;
					}
				} else if (randomGetal == 1) {
					if (this.getStrength() != MAX_VAL_PRIMARY_ATTRIBUTE) {
						this.setStrength(this.getStrength() + 1);
						leveledUp = true;
					}
				} else {
					if (this.getToughness() != MAX_VAL_PRIMARY_ATTRIBUTE) {
						this.setToughness(this.getToughness() + 1);
						leveledUp = true;
					}
				}
			}
		}
		this.setLevel(this.getLevel() + 1);
	}

	/**
	 * Moves this unit to an adjacent cube.
	 * 
	 * @param x
	 *            the relative position on de x-axis
	 * @param y
	 *            the relative position on de y-axis
	 * @param z
	 *            the relative position on de z-axis
	 * @throws IllegalArgumentException
	 *             when the x, y and z are not referring to an adjacent cube.
	 */
	public void moveToAdjacent(int x, int y, int z) throws IllegalArgumentException {
		if (this.getMoveToAdjacent() != null) {
			return;
		}

		if (!(((x == -1) || (x == 0) || (x == 1)) && ((y == -1) || (y == 0) || (y == 1))
				&& ((z == -1) || (z == 0) || (z == 1))))
			throw new IllegalArgumentException();

		Cube moveToAdjacent = new Cube(this.getPosition().getCube().getX() + x, this.getPosition().getCube().getY() + y,
				this.getPosition().getCube().getZ() + z);

		if (!(this.getActivity() == Activity.WALKING || this.getActivity() == Activity.SPRINTING))
			this.setActivity(Activity.WALKING);

		this.setMoveToAdjacent(moveToAdjacent);
	}

	public Cube getMoveToAdjacent() {
		return this.moveToAdjacent;
	}

	public void setMoveToAdjacent(Cube cube) {
		if (cube == null || cube.isValidIn(this.getWorld()))
			this.moveToAdjacent = cube;
	}

	private Cube moveToAdjacent;

	/**
	 * returns the movement speed in accordance with the units activity status
	 * and primary attributes.
	 */
	public double getMovementSpeed() {
		if (this.isFalling())
			return 3.0;
		double speed = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getTotalWeight());
		if (this.isSprinting())
			speed *= 2;
		if (this.getMoveToAdjacent() != null) {
			int zDiff = this.getCube().getZ() - this.getMoveToAdjacent().getZ();
			if (zDiff == -1)
				speed *= 0.5;
			else if (zDiff == 1)
				speed *= 1.2;
		}
		return speed;
	}

	/**
	 * Moves this unit to a cube further away.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void moveTo(int x, int y, int z) {
		this.setMoveToCube(new Cube(x, y, z));
		findNextCubeInPath();
	}

	/**
	 * Moves this unit to the next cube when it's pathfinding to a cube far
	 * away.
	 */
	private void findNextCubeInPath() {
		Set<Cube> allCubes = this.getWorld().getAllCubes();
		for (Cube cube : new HashSet<Cube>(allCubes)) {
			if (!cube.isPassableIn(this.getWorld()))
				allCubes.remove(cube);
			else if (!cube.getCenter().isStableForUnitIn(this.getWorld()))
				allCubes.remove(cube);
		}

		Cube current = this.getCube();
		double currentDistance = 0;
		Map<Cube, Double> unvisited = new HashMap<Cube, Double>();
		for (Cube cube : allCubes) {
			if (cube.equals(this.getCube())) {
				unvisited.put(cube, currentDistance);
				current = cube;
			} else
				unvisited.put(cube, Double.MAX_VALUE);
		}
		Map<Cube, Double> visited = new HashMap<Cube, Double>();
		Map<Cube, Cube> shortestPath = new HashMap<Cube, Cube>();
		Queue<CubeDistPair> queue = new PriorityQueue<CubeDistPair>();

		while (currentDistance != Double.MAX_VALUE) {
			if (current.equals(this.getMoveToCube())) {
				Cube previous = shortestPath.get(current);
				while (!shortestPath.get(previous).equals(this.getCube()))
					previous = shortestPath.get(previous);
				this.setMoveToAdjacent(previous);
			}

			for (Cube cube : unvisited.keySet()) {
				if (cube.isSameOrAdjacentCube(current) && !cube.equals(current)) {
					double weight = 1; // TODO: (priority low) bereken gewicht
										// als afstand tss
										// midden + omhoog/omlaag + etc
					double newDistance = currentDistance + weight;
					if (newDistance < unvisited.get(cube)) {
						unvisited.put(cube, newDistance);
						shortestPath.put(cube, current);
						queue.offer(new CubeDistPair(cube, newDistance));
					}
				}
			}

			visited.put(current, currentDistance);
			unvisited.remove(current);

			while (!queue.isEmpty() && visited.containsKey(queue.peek().getCube()))
				queue.poll();

			// No next node -> no path
			if (queue.isEmpty())
				return;
			CubeDistPair next = queue.poll();
			current = next.getCube();
			currentDistance = next.getDistance();
		}

		return;
	}

	private class CubeDistPair implements Comparable<CubeDistPair> {

		private final Double distance;
		private final Cube cube;

		public CubeDistPair(Cube cube, Double distance) {
			this.cube = cube;
			this.distance = distance;
		}

		public Double getDistance() {
			return distance;
		}

		public Cube getCube() {
			return cube;
		}

		@Override
		public int compareTo(CubeDistPair other) {
			return this.getDistance().compareTo(other.getDistance());
		}

	}

	/**
	 * returns the cube far away where this unit is going.
	 * 
	 * @return
	 */
	public Cube getMoveToCube() {
		return this.moveToCube;
	}

	public void setMoveToCube(Cube cube) {
		if (cube == null || cube.isValidIn(this.getWorld()))
			this.moveToCube = cube;
	}

	private Cube moveToCube;

	/**
	 * returns the current activity of this unit.
	 */
	public Activity getActivity() {
		return this.activity;
	}

	/**
	 * sets the activity of this unit to the given activity. Returns true if it
	 * succeeded.
	 * 
	 * @param activity
	 * @param busyTime
	 * @return
	 */
	public void setActivity(Activity activity, double busyTime) {
		this.setActivity(activity);
		this.setBusyTime(busyTime);
	}

	/**
	 * sets the activity of this unit to the given activity.
	 * 
	 * @param activity
	 * @return
	 */
	public void setActivity(Activity activity) {
		if (this.isResting() && !this.canStopResting)
			return;
		else if (this.isAttacking() && this.getBusyTime() > 0)
			return;
		if (this.isFalling() && !this.canStopFalling())
			return;
		// else if (this.isSprinting() && this.getStaminaPoints() == 0)
		// return;
		this.activity = activity;
	}

	/**
	 * returns whether this unit is defending or not.
	 */
	public boolean isDefending() {
		return (this.getActivity() == Activity.DEFENDING);
	}

	/**
	 * returns wheter this unit is defending or not.
	 */
	public boolean isAttacking() {
		return (this.getActivity() == Activity.ATTACKING);
	}

	/**
	 * returns wheter this unit is moving or not.
	 */
	public boolean isMoving() {
		return (this.getActivity() == Activity.WALKING)
				|| (this.getActivity() == Activity.SPRINTING || (this.getActivity() == Activity.FALLING));
	}

	public boolean isWalking() {
		return (this.getActivity() == Activity.WALKING);
	}

	/**
	 * returns wheter this unit is sprinting or not.
	 */
	public boolean isSprinting() {
		return (this.getActivity() == Activity.SPRINTING);
	}

	/**
	 * if the unit is sprinting, it will start walking. If the unit is walking,
	 * it wills tart sprinting. if the unit is not moving, this method will do
	 * noting.
	 */
	public void startSprinting() {
		if (this.isWalking() && this.getStaminaPoints() > 0)
			this.setActivity(Activity.SPRINTING);
	}

	public void stopSprinting() {
		if (this.isSprinting())
			this.setActivity(Activity.WALKING);
	}

	/**
	 * returns whether this unit is working or not.
	 */
	public boolean isWorking() {
		return (this.getActivity() == Activity.WORKING);
	}

	/**
	 * returns whether this unit is resting or not.
	 */
	public boolean isResting() {
		return (this.getActivity() == Activity.RESTING);
	}

	private boolean canStopResting;

	/**
	 * returns if this unit is doing anything at all.
	 */
	public boolean isBeingUseless() {
		return (this.getActivity() == Activity.NONE);
	}

	public boolean isFalling() {
		return (this.getActivity() == Activity.FALLING);
	}

	public boolean shouldFall() {
		return !this.getPosition().isStableForUnitIn(this.getWorld());
	}

	public boolean shouldStartFallingAt(Cube cube) {
		return !cube.getCenter().isStableForUnitIn(this.getWorld());
	}

	// /**
	// * Check whether this unit should fall.
	// *
	// * @return true if the cube of the position is not directly above a solid
	// cube
	// * @return false if the z-coordinate is 0
	// * @return false if the material is carried by a unit, thus position
	// equals null
	// */
	// public boolean shouldFall() {
	// if (this.getPosition()==null)
	// return false;
	//
	// if (this.getPosition().getCube().getZ() == 0)
	// return false;
	// Cube cubeBelow = new Cube(this.getPosition().getCube().getX(),
	// this.getPosition().getCube().getY(),
	// this.getPosition().getCube().getZ()-1);
	// if ( this.getWorld().getTerrainType(cubeBelow).isPassable() )
	// return true;
	//
	// return false;
	// }

	public boolean canStopFalling() {
		// return (this.isFalling() &&
		// !this.world.isPassableCube(this.getCube().min(new Cube(0, 0, 1))));
		return (this.isFalling() && !(this.getCube().min(new Cube(0, 0, 1))).isPassableIn(this.getWorld()));

	}

	private Activity activity;

	/**
	 * starts the default behaviour of this unit
	 */
	public void startDefaultBehaviour() {
		this.defaultBehaviour = true;
	}

	/**
	 * stops the default behaviour of this unit
	 */
	public void stopDefaultBehaviour() {
		this.defaultBehaviour = false;
	}

	/**
	 * returns whether the default behaviour can be started
	 */
	public boolean canStartDefaultBehaviour() {
		return (!this.defaultBehaviour);
	}

	private boolean defaultBehaviour;

	/**
	 * this unit starts working.
	 */
	public void work() {
		this.setActivity(Activity.WORKING);
		this.setBusyTime(500 / this.getStrength());
	}

	public void rest() {
		double busytime = Math.max(this.getBusyTime(), 200 * 0.2 / this.getToughness());
		this.setActivity(Activity.RESTING, busytime);
		if (this.isResting())
			this.canStopResting = false;
	}

	public void attack(Unit defender) {
		if (this.getCube().isSameOrAdjacentCube(defender.getCube()) && !defender.isFalling()
				&& this.getFaction() != defender.getFaction()) {
			this.setActivity(Activity.ATTACKING, 1.0);
			if (this.isAttacking())
				defender.defend(this);
			this.setOrientation(Math.atan2(defender.getPosition().getRealY() - this.getPosition().getRealY(),
					defender.getPosition().getRealX() - this.getPosition().getRealX()));
		}
	}

	/**
	 * this unit starts defending.
	 * 
	 * @param attacker
	 */
	public void defend(Unit attacker) {
		boolean succeeded = true;
		// dodging
		if (RANDOM_GEN.nextDouble() < 0.2 * this.getAgility() / attacker.getAgility()) {
			Position nextPosition = null;
			while (nextPosition == this.getPosition() || (!canHaveAsPosition(nextPosition))) {
				Position minPosition = new Position(RANDOM_GEN.nextInt(3) - 1, RANDOM_GEN.nextInt(3) - 1, 0);
				nextPosition = this.getPosition().min(minPosition);
			}
			this.setPosition(nextPosition);
			// blocking
		} else if (RANDOM_GEN.nextDouble() < 0.25 * (this.getStrength() + this.getAgility())
				/ (attacker.getStrength() + attacker.getAgility())) {
			// taking damage
		} else {
			this.setHitpoints(attacker.getStrength() / 10);
			succeeded = false;
		}
		this.setOrientation(Math.atan2(attacker.getPosition().getRealY() - this.getPosition().getRealY(),
				attacker.getPosition().getRealX() - this.getPosition().getRealX()));
		if (succeeded)
			this.setExperiencePoints(this.getExperiencePoints() + 20);
		else
			attacker.setExperiencePoints(attacker.getExperiencePoints() + 20);
	}

	public World getWorld() {
		return this.world;
	}

	public boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsUnit(this) && this.getWorld() == null);
	}

	public void setWorld(World world) {
		if (world != null) {
			if (!canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsUnit(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	private World world;

	public Faction getFaction() {
		return this.faction;
	}

	public boolean canHaveAsFaction(Faction faction) {
		return (faction != null && faction.hasAsUnit(this) && this.getFaction() == null);
	}

	public void setFaction(Faction faction) throws IllegalArgumentException {
		if (faction != null) {
			if (!canHaveAsFaction(faction))
				throw new IllegalArgumentException();
		} else if ((this.getFaction() != null) && (this.getFaction().hasAsUnit(this)))
			throw new IllegalArgumentException();
		this.faction = faction;
	}

	private Faction faction;

	/**
	 * Check whether this unit has the given material as one of its materials.
	 * 
	 * @param material
	 *            The material to check.
	 */
	@Basic
	@Raw
	public boolean hasAsMaterial(@Raw Material material) {
		return materials.contains(material);
	}

	/**
	 * Check whether this unit can have the given material as one of its
	 * materials.
	 * 
	 * @param material
	 *            The material to check.
	 * @return True if and only if the given material is effective and that
	 *         material is a valid material for a unit. | result == | (material
	 *         != null) && | Material.isValidUnit(this)
	 */
	@Raw
	public boolean canHaveAsMaterial(Material material) {
		return (material != null) && (material.canHaveAsOwner(this));
	}

	/**
	 * Check whether this unit has proper materials attached to it.
	 * 
	 * @return True if and only if this unit can have each of the materials
	 *         attached to it as one of its materials, and if each of these
	 *         materials references this unit as the unit to which they are
	 *         attached. | for each material in Material: | if
	 *         (hasAsMaterial(material)) | then canHaveAsMaterial(material) && |
	 *         (material.getUnit() == this)
	 */
	public boolean hasProperMaterials() {
		for (Material material : materials) {
			if (!canHaveAsMaterial(material))
				return false;
			if (material.getOwner() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of materials associated with this unit.
	 *
	 * @return The total number of materials collected in this unit. | result ==
	 *         | card({material:Material | hasAsMaterial({material)})
	 */
	public int getNbMaterials() {
		return materials.size();
	}

	/**
	 * Add the given material to the set of materials of this unit.
	 * 
	 * @param material
	 *            The material to be added.
	 * @pre The given material is effective and already references this unit. |
	 *      (material != null) && (material.getUnit() == this)
	 * @post This unit has the given material as one of its materials. |
	 *       new.hasAsMaterial(material)
	 */
	public void addMaterial(@Raw Material material) {
		assert (material != null);
		materials.add(material);
		material.setOwner(this);
	}

	/**
	 * Remove the given material from the set of materials of this unit.
	 * 
	 * @param material
	 *            The material to be removed.
	 * @pre This unit has the given material as one of its materials, and the
	 *      given material does not reference any unit. |
	 *      this.hasAsMaterial(material) && | (material.getUnit() == null)
	 * @post This unit no longer has the given material as one of its materials.
	 *       | ! new.hasAsMaterial(material)
	 */
	@Raw
	public void removeMaterial(Material material, Position position) {
		assert this.hasAsMaterial(material) && (material.getOwner() == null);
		materials.remove(material);
		material.setPosition(position, this.getWorld());
	}

	/**
	 * Variable referencing a set collecting all the materials of this unit.
	 * 
	 * @invar The referenced set is effective. | materials != null
	 * @invar Each material registered in the referenced list is effective and
	 *        not yet terminated. | for each material in materials: | (
	 *        (material != null) && | (! material.isTerminated()) )
	 */
	private final Set<Material> materials = new HashSet<Material>();

	/**
	 * Terminate this unit.
	 *
	 * @post This unit is terminated. | new.isDead()
	 * @post The unit doesn't have a relation with the world and faction |
	 * @post The materials the unit was carrying are left behind at the exact
	 *       same position the unit has died. |
	 */
	public void die() {
		for (Material material : this.materials)
			material.setPosition(this.getPosition(), this.getWorld());
		this.getWorld().removeUnit(this);
		this.getFaction().removeUnit(this);
		this.isDead = true;
	}

	/**
	 * Return a boolean indicating whether or not this unit is terminated.
	 */
	@Basic
	@Raw
	public boolean isDead() {
		return this.isDead;
	}

	/**
	 * Variable registering whether this person is terminated.
	 */
	private boolean isDead = false;

}
