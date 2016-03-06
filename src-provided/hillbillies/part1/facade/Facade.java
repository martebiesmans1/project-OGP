package part1;

import model.Unit;

public class Facade implements IFacade {
	
	//TODO geeft geen error-messages -> toch in srs-folder zetten?
	
	/* Unit creation */
	/**
	 * Create a new unit with the given attributes.
	 * 
	 * @param name
	 *            The name of the unit.
	 * @param initialPosition
	 *            The initial position of the unit, as an array with 3 elements
	 *            {x, y, z}.
	 * @param weight
	 *            The initial weight of the unit
	 * @param agility
	 *            The initial agility of the unit
	 * @param strength
	 *            The initial strength of the unit
	 * @param toughness
	 *            The initial toughness of the unit
	 * @param enableDefaultBehavior
	 *            Whether the default behavior of the unit is enabled
	 * 
	 * @return The generated unit
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try{
			//TODO default behavior
			//TODO is initialPosition.get(0) een juiste notatie?

			return new Unit((double) initialPosition.get(0), (double) initialPosition.get(1), (double) initialPosition.get(2),
				name, strength, agility, toughness, weight);
		}
		
		catch (IllegalArgumentException){
			throw new ModelException
		}	
	}

	/* Position */
	/**
	 * Get the precise coordinate of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the position.
	 * @return The coordinate of the center of the unit, as an array with 3
	 *         doubles {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public double[] getPosition(Unit unit) throws ModelException {
		try{
			//TODO
		}
		
		catch (IllegalArgumentException){
			throw new ModelException
		}
	}

	/**
	 * Get the coordinate of the cube occupied by the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the cube coordinate.
	 * @return The coordinate of the cube in which the center of the unit lies,
	 *         as an array with 3 integers {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		try{
		//TODO
		}
		
		catch (IllegalArgumentException){
			throw new ModelException
		}	
	}

	/* Name */
	/**
	 * Get the current name of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the name.
	 * @return The current name of the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public String getName(Unit unit) throws ModelException {
		try{
			return unit.getName();
		}
		
		catch (IllegalArgumentException){
			throw new ModelException
		}	
	}

	/**
	 * Set the name of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit whose name to change.
	 * @param newName
	 *            The new name for the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setName(Unit unit, String newName) throws ModelException {
		try{
			unit.setName(newName);
		}
		
		catch (IllegalArgumentException){
			throw new ModelException
		}	
	}

	/* Attributes */

	/**
	 * Return the weight attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current weight of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getWeight(Unit unit) throws ModelException {
		try{
			return unit.getWeight();
		}
		
		catch (){ //TODO is deze catch nodig? want weight is niet-defensief geprogrammeerd, maar zo kunnen we mss eender welke andere fout catchen
			throw new ModelException
		}	
	}

	/**
	 * Sets the weight attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new weight
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setWeight(Unit unit, int newValue) throws ModelException {
		try{
			unit.setWeight(newValue);
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the strength attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current strength of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getStrength(Unit unit) throws ModelException {
		try{
			return unit.getStrength();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Sets the strength attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new strength
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setStrength(Unit unit, int newValue) throws ModelException {
		try{
			unit.setStrength(newValue);
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the agility attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current agility of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getAgility(Unit unit) throws ModelException {
		try{
			return unit.getAgility();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Sets the agility attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new agility
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setAgility(Unit unit, int newValue) throws ModelException {
		try{
			unit.setAgility(newValue);
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the toughness attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current toughness of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getToughness(Unit unit) throws ModelException {
		try{
			return unit.getToughness();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Sets the toughness attribute's value of the given unit to the given
	 * value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new toughness
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setToughness(Unit unit, int newValue) throws ModelException {
		try{
			unit.setToughness(newValue);
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Points */

	/**
	 * Return the maximum number of hitpoints for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the maximum number of hitpoints
	 * @return The maximum number of hitpoints for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getMaxHitPoints(Unit unit) throws ModelException {
		try{
			return unit.getMaxHitpoints(); //TODO deze methode staat nog op private -> public maken of package available?
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the current number of hitpoints for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the current number of hitpoints
	 * @return The current number of hitpoints for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		try{
			return (int) unit.getHitpoints();
			//TODO waarom is hitpoints type double en niet int?
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the maximum number of stamina points for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the maximum number of stamina
	 *            points
	 * @return The maximum number of stamina points for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		try{
			return unit.getMaxStaminaPoints();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the current number of stamina points for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the current number of stamina
	 *            points
	 * @return The current number of stamina points for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		try{
			return (double) unit.getStaminaPoints();
			//TODO waarom is staminaPoints double en niet int?
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Time */

	/**
	 * Advance the state of the given unit by the given time period.
	 * 
	 * @param unit
	 *            The unit for which to advance the time
	 * @param dt
	 *            The time period, in seconds, by which to advance the unit's
	 *            state.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try{
			unit.advanceTime((float) dt);
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Basic movement */

	/**
	 * Move the given unit to an adjacent cube.
	 * 
	 * @param unit
	 *            The unit to move
	 * @param dx
	 *            The amount of cubes to move in the x-direction; should be -1,
	 *            0 or 1.
	 * @param dy
	 *            The amount of cubes to move in the y-direction; should be -1,
	 *            0 or 1.
	 * @param dz
	 *            The amount of cubes to move in the z-direction; should be -1,
	 *            0 or 1.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try{
			unit.moveToAdjacent(dx, dy, dz);
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return the current speed of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the speed.
	 * @return The speed of the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public double getCurrentSpeed(Unit unit) throws ModelException {
		try{
			return unit.getMovementSpeed();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return whether the given unit is currently moving.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state.
	 * @return true if the unit is currently moving; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isMoving(Unit unit) throws ModelException {
		try{
			return unit.isMoving();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Enable sprinting mode for the given unit.
	 * 
	 * @param unit
	 *            The unit which should start sprinting.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void startSprinting(Unit unit) throws ModelException {
		try{
			unit.toggleSprinting();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Disable sprinting mode for the given unit.
	 * 
	 * @param unit
	 *            The unit which should stop sprinting.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void stopSprinting(Unit unit) throws ModelException {
		try{
			unit.toggleSprinting();
			//TODO aparte case voor als men al aan het sprinten is?
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return whether the given unit is currently sprinting.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state.
	 * @return true if the unit is currently sprinting; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isSprinting(Unit unit) throws ModelException {
		try{
			return unit.isSprinting();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Orientation */

	/**
	 * Return the current orientation of the unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the orientation
	 * @return The orientation of the unit, in radians.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public double getOrientation(Unit unit) throws ModelException {
		try{
			return unit.getOrientation();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Extended movement */

	/**
	 * Start moving the given unit to the given cube.
	 * 
	 * @param unit
	 *            The unit that should start moving
	 * @param cube
	 *            The coordinate of the cube to move to, as an array of integers
	 *            {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try{
			unit.moveTo(cube.get(0), cube.get(1), cube.get(2));
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Working */

	/**
	 * Make the given unit start working.
	 * 
	 * @param unit
	 *            The unit that should start working
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void work(Unit unit) throws ModelException {
		try{
			//TODO
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return whether the given unit is currently working.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently working; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isWorking(Unit unit) throws ModelException {
		try{
			return unit.isWorking();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Attacking */

	/**
	 * Make the given unit fight with another unit.
	 * 
	 * @param attacker
	 *            The unit that initiates the fight by attacking another unit
	 * @param defender
	 *            The unit that gets attacked and should defend itself
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try{
			//TODO
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return whether the given unit is currently attacking another unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently attacking another unit; false
	 *         otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isAttacking(Unit unit) throws ModelException {
		try{
			return unit.isAttacking();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Resting */

	/**
	 * Make the given unit rest.
	 * 
	 * @param unit
	 *            The unit that should start resting
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void rest(Unit unit) throws ModelException {
		try{
			//TODO
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/**
	 * Return whether the given unit is currently resting.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the atate
	 * @return true if the unit is currently resting; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isResting(Unit unit) throws ModelException {
		try{
			return unit.isResting();
		}
		
		catch (){
			throw new ModelException
		}	
	}

	/* Default behavior */

	/**
	 * Enable or disable the default behavior of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to enable or disable the default behavior
	 * @param value
	 *            true if the default behavior should be enabled; false
	 *            otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		try{
			//TODO
		}
		
		catch (){
			throw new ModelException
		}
	}

	/**
	 * Returns whether the default behavior of the given unit is enabled.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the default behavior state.
	 * @return true if the default behavior is enabled; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		try{
			//TODO
		}
		
		catch (){
			throw new ModelException
		}	
	}
}
